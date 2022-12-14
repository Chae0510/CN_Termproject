package client.GUI;

import javax.swing.*;
import Database.Database;
import etc.CloseFrame;

@SuppressWarnings("serial")
public class ChatroomFrame extends JFrame {
  private String frameName;

  public ChatroomFrame(JPanel panel, String frameName) {
    Database database = Database.getInstance();
    this.frameName = frameName;
    setTitle(database.username + "'s chatroom");
    setBounds(1200, 250, 405, 605);
    getContentPane().add(panel);
    setVisible(true);
    addWindowListener(new CloseFrame(getFrameName()));
  }

  public String getFrameName() {

    return frameName;
  }
}
