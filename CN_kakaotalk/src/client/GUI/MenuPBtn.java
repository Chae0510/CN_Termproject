package client.GUI;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MenuPBtn extends JButton {
	public MenuPBtn(String buttonTitle) {
		setName(buttonTitle);
		setText(buttonTitle);
		setFocusPainted(false);
		setFont(new Font("굴림", Font.BOLD, 10));

		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
	}

}

