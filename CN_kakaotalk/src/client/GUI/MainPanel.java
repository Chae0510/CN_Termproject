package client.GUI;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import etc.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
  private Image img = UsingImage.getImage("src/Image/kakao.png");
  private JLabel logoImgLabel;
  private MainPBtn signUpButton;
  private MainPBtn loginButton;
  public static MainFrame frame;

  public MainPanel(MainFrame frame) {
    MainPanel.frame = frame;
    setLayout(null);
    kakaotalkimg();
    SignupBtn();
    SigninBtn();
  }

  /*로고 아이콘*/
  private void kakaotalkimg() {

    logoImgLabel = new JLabel(new ImageIcon(img));
    logoImgLabel.setBounds(95, 15, 200, 200);
    add(logoImgLabel);
  }

  private void SignupBtn() {

    signUpButton = new MainPBtn("Sign Up");
    signUpButton.setBounds(30, 300, 150, 35);
    signUpButton.setOpaque(true);
    add(signUpButton);
    signUpButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        SignUp signUp = new SignUp();
        MainPanel.frame.resetData(signUp);
      }
    });
  }

  private void SigninBtn() {

    loginButton = new MainPBtn("Sign In");
    loginButton.setBounds(230, 300, 150, 35);
    loginButton.setOpaque(true);
    add(loginButton);
    loginButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        SignIn signIn = new SignIn();
        MainPanel.frame.resetData(signIn);
      }
    });
  }
}
