package client.GUI;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

  private MainPanel mainPanel;

  public MainFrame() {
    setTitle("Messenger");
    setBounds(400, 250, 400, 600);

    mainPanel = new MainPanel(this);
    getContentPane().add(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  public void resetData(JPanel panelName) {
    getContentPane().removeAll();
    getContentPane().add(panelName);
    revalidate();
    repaint();
  }
}
