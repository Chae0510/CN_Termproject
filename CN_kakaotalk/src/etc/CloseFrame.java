package etc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import client.GUI.FriendVector;
import client.GUI.profileGUI;
import Database.Database;

public class CloseFrame extends WindowAdapter {

  private String frameName;

  public CloseFrame(String frameName) {

    this.frameName = frameName;
  }

  public void windowClosing(WindowEvent e) {

    JFrame frame = (JFrame) e.getWindow();
    frame.dispose();

    Database database = Database.getInstance();

    if (database.username.equals(frameName)) {
      profileGUI.userBtn.setText(frameName);
    }

    for (JButton j : FriendVector.fBtn) {
      if (j.getText().contains(frameName)) {
        j.setText(frameName);
      }
    }
  }
}

