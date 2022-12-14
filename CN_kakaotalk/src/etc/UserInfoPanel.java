package etc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class UserInfoPanel extends CommonPanel {
  protected JLabel idLabel;
  protected JTextField signIntext;
  protected JPasswordField userInfoPasswordField;
  protected JButton formButton;

  protected abstract void SignIninputData();

  protected void showFormTitle(String text) {

    idLabel = new JLabel(text);
    idLabel.setFont(new Font("굴림", Font.BOLD, 18));
    idLabel.setBounds(30, 70, 200, 50);
    add(idLabel);
  };


  protected JButton showFormButton(String text) {

    formButton = new JButton(text);
    formButton.setFont(new Font("굴림", Font.BOLD, 14));
    formButton.setForeground(Color.WHITE);
    formButton.setBounds(100, 480, 180, 40);
    add(formButton);

    return formButton;
  };


}
