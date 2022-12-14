package server;

import client.ChatHandlerObject;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

class ServerChat
{
    private ServerSocket serverSocket;
    private List <ChatHandlerObject> list;
    public ServerChat(){
        try{
            serverSocket= new ServerSocket (5858);
            System.out.println("Server is Ready");
            list = new  ArrayList<ChatHandlerObject>();
            while(true){
                Socket socket = serverSocket.accept();
                ChatHandlerObject handler = new  ChatHandlerObject(socket,list);
                handler.start();
                list.add(handler);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args)
    {
        new ServerChat();
    }
}