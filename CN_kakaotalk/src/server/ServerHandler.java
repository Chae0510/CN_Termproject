package server;

import server.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerHandler {
  ExecutorService executorService;
  ServerSocket serverSocket;
  List<Client> clientVector = new Vector<Client>();

  public void startServer() {

    ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 120L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    executorService = threadPool;
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress(5858));
      System.out.println("Waiting...");
    } catch (Exception e) {
      e.printStackTrace();
    }
    Runnable runnable = new Runnable() {
      @Override
      public void run() {

        while (true) {
          try {
            Socket socket = serverSocket.accept();

            System.out.println("IP: " + socket.getRemoteSocketAddress() + ": "
                    + Thread.currentThread().getName());
            Client client = new Client(socket); // 클라이언트 객체에 저장.
            clientVector.add(client);
          } catch (IOException e) {
            if (!serverSocket.isClosed()) {
              stopServer();
            }
            break;
          }
        }
      }
    };
    executorService.submit(runnable);
  }

  public void stopServer() {

    try {
      Iterator<Client> iterator = clientVector.iterator();
      while (iterator.hasNext()) {
        Client client = iterator.next();
        client.socket.close();
        iterator.remove();
      }
      if (serverSocket != null && !serverSocket.isClosed()) {
        serverSocket.close();
      }
      if (executorService != null && !executorService.isShutdown()) {
        executorService.shutdown();
      }
    } catch (Exception e) {
    }
  }

  class Client {
    Socket socket;
    String userName;
    Client(Socket socket) {

      this.socket = socket;
      receive();
    }
    void receive() {

      Runnable runnable = new Runnable() {

        @Override
        public void run() {

          byte[] byteArr = new byte[1024];
          try {
            while (true) {
              InputStream inputStream = socket.getInputStream();
              int readByteCount = inputStream.read(byteArr);
              if (readByteCount == -1) {
                throw new IOException();
              }
              Message ms = toObject(byteArr, Message.class);
              System.out.println("요청처리: " + socket.getRemoteSocketAddress() + ": "
                      + Thread.currentThread().getName());
              userName = ms.getSendUserName();
              System.out.println(userName + "qq");
              send(byteArr);
              for (Client client : clientVector) {
                System.out.println(client.userName + "ss" + ms.getReceiveFriendName());
                if (client.userName != null) {
                  if (client.userName.equals(ms.getReceiveFriendName())
                          && !ms.getSendUserName().equals(ms.getReceiveFriendName())) {
                    client.send(byteArr);
                  }
                }
              }
            }
          } catch (Exception e) {
            try {
              clientVector.remove(Client.this);
              socket.close();
            } catch (IOException e2) {
            }
          }
        }
      };
      executorService.submit(runnable);
    }

    private Message toObject(byte[] byteArr, Class<Message> class1) {

      Object obj = null;
      try {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
        ObjectInputStream ois = new ObjectInputStream(bis);
        obj = ois.readObject();
      } catch (Exception e) {
      }
      return class1.cast(obj);
    }

    void send(byte[] bytes) {

      Runnable runnable = new Runnable() {

        @Override
        public void run() {

          try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
      executorService.submit(runnable);
    }
  }
}
