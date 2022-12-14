package client.GUI;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MainPBtn extends JButton {

  public MainPBtn(String buttonTitle) {
    setName(buttonTitle);
    setText(buttonTitle);
    setFocusPainted(false);
    setFont(new Font("굴림", Font.BOLD, 18));

    Border emptyBorder = BorderFactory.createEmptyBorder();
    setBorder(emptyBorder);

  }

}

