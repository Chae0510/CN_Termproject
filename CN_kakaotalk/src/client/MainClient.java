package client;

import client.GUI.MainFrame;

public class MainClient {

  public static void main(String[] args) {

    try {
      MainFrame mainFrame = new MainFrame();
      mainFrame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
