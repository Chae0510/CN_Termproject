package client.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

// Call Class
import Database.Database;
import server.Message;
import etc.SelectFile;
import etc.UsingImage;

@SuppressWarnings("serial")
public class ChatFrame extends JPanel {
  private String panelName;
  private JTextArea textArea;
  private JButton sendTextBtn;
  private JButton sendFileBtn;
  private JButton imgFileButton;
  private JTextPane jtp;
  private StyledDocument document;
  Database database;

  private static String userName;

  public ChatFrame(ImageIcon imageIcon, String friendName) {

    database = Database.getInstance();
    userName = database.username;

    panelName = friendName;
    setLayout(null);
    friendInfo(imageIcon, friendName);
    sendMessageText();
    messageFrame();

    sendFileBtn = FileBtn();
    add(sendFileBtn);

    sendFileBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        File file = SelectFile.showFile();
        textArea.setText(file.toString());
      }
    });


    sendTextBtn = sendBtn();
    add(sendTextBtn);

    sendTextBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        Database database = Database.getInstance();

        String messageType = null;
        if (textArea.getText().contains(".jpg") || textArea.getText().contains(".png")
                || textArea.getText().contains(".JPG") || textArea.getText().contains(".PNG")) {
          messageType = "file";
        } else {
          messageType = "text";
        }
        Message message = null;
        if (messageType.equals("file")) {
          message = new Message(database.username, textArea.getText(), LocalTime.now(),
                  messageType, friendName);
        } else {
          message = new Message(database.username, textArea.getText(), LocalTime.now(),
                  messageType, friendName);
        }

        database.clientSocket.send(message);
        textArea.setText("");
      }
    });
  }




  private void friendInfo(ImageIcon imageIcon, String friendName) {
    JLabel friendInfolabel = new JLabel(imageIcon);
    friendInfolabel.setOpaque(true);
    friendInfolabel.setText(friendName);
    friendInfolabel.setFont(new Font("굴림", Font.BOLD, 14));
    friendInfolabel.setBounds(0, 0, 400, 80);
    add(friendInfolabel);
  }

  private JButton sendBtn() {

    JButton sendButton = new JButton("Send");
    sendButton.setFont(new Font("굴림", Font.BOLD, 14));
    sendButton.setFocusPainted(false);
    sendButton.setBounds(345, 500, 58, 48);
    return sendButton;
  }

  private JButton FileBtn() {

    JButton sendButton = new JButton("File");
    sendButton.setFont(new Font("굴림", Font.BOLD, 14));
    sendButton.setFocusPainted(false);
    sendButton.setBounds(280, 500, 58, 48);
    return sendButton;
  }

  private void sendMessageText() {

    textArea = new JTextArea(20, 20);
    JScrollPane scroller = new JScrollPane(textArea);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setBounds(10, 500, 270, 65);
    add(scroller);
  }

  private void messageFrame() {

    StyleContext context = new StyleContext();
    document = new DefaultStyledDocument(context);
    jtp = new JTextPane(document);
    jtp.setEditable(false);
    JScrollPane scroller2 = new JScrollPane(jtp);
    scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller2.setBounds(10, 80, 389, 380);
    add(scroller2);
  }

  public static void showMessage(Message message) {
    for (ChatFrame chatName : profileGUI.chatPanelName) {
      if (userName.equals(message.getSendUserName())
              && chatName.panelName.equals(message.getReceiveFriendName())) {
        chatName.textPrint(message.getSendTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                +  message.getSendUserName() , talkroomAlign2.RIGHT);
        if (message.getMessageType().equals("file")) {
          chatName.imgPrint(message.getSendComment());
        } else {
          chatName.textPrint(message.getSendComment(), talkroomAlign2.RIGHT);
        }
      }

      if (chatName.panelName.equals(message.getSendUserName()) && !chatName.panelName.equals(message.getReceiveFriendName())) {
        chatName.textPrint(message.getSendTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                + message.getSendUserName() , talkroomAlign2.LEFT);
        if (message.getMessageType().equals("file")) {
          chatName.imgPrint(message.getSendComment());
        } else {
          chatName.textPrint(message.getSendComment(), talkroomAlign2.LEFT);
        }
      }
    }


  }


  private void imgPrint(String sendComment) {

    Image imgFile = UsingImage.getImage(sendComment);
    Image imgResize = imgFile.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    StyledDocument doc2 = (StyledDocument) jtp.getDocument();
    Style style2 = doc2.addStyle("StyleName", null);
    StyleConstants.setIcon(style2, new ImageIcon(imgResize));
    try {
      doc2.insertString(doc2.getLength(), "invisible text" + "\n", style2);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

  private void textPrint(String string, talkroomAlign2 talkroomAlign2) {

    try {
      document = jtp.getStyledDocument();
      SimpleAttributeSet sortMethod = new SimpleAttributeSet();

      if(talkroomAlign2 == talkroomAlign2.RIGHT) {
        StyleConstants.setAlignment(sortMethod, StyleConstants.ALIGN_RIGHT);
      }else if (talkroomAlign2 == talkroomAlign2.LEFT) {
        StyleConstants.setAlignment(sortMethod, StyleConstants.ALIGN_LEFT);
      }
      document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sortMethod, true);
      document.insertString(document.getLength(), string + "\n", sortMethod);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

}
