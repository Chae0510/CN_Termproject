package client.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import etc.CommonWord;
import Database.Database;
import etc.UsingImage;
import etc.UserInfoPanel;

@SuppressWarnings("serial")
public class SignIn extends UserInfoPanel {

	private Image img = UsingImage.getImage("src/Image/kakao.png");
	private final String SignIn = "Sign In";

	private final String Cancel = "Cancel";


	private ArrayList<JTextField> user = new ArrayList<JTextField>(); // 이메일과 비밀번호

	public SignIn() {
		showFormTitle("Sign In");
		SignIninputData();
		SignInBtn();
	}

	public void SignIninputData() {

		for (CommonWord tradeWord : CommonWord.values()) {

			if (tradeWord.getNum() == CommonWord.ID.getNum()) {
				idLabel = new JLabel("ID : ");
				idLabel.setFont(new Font("굴림", Font.BOLD, 12));
				idLabel.setBounds(30, 150, 200, 50);
				add(idLabel);

				signIntext = new JTextField(10);
				signIntext.setBounds(60, 150, 230, 50);
				add(signIntext);
				user.add(signIntext);

			} else if (tradeWord.getNum() == CommonWord.PASSWORD.getNum()) {

				idLabel = new JLabel("Password : ");
				idLabel.setFont(new Font("굴림", Font.BOLD, 12));
				idLabel.setBounds(30, 250, 200, 50);
				add(idLabel);
				userInfoPasswordField = new JPasswordField(10);
				userInfoPasswordField.setBounds(120, 250, 210, 50);
				add(userInfoPasswordField);


				userInfoPasswordField.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loginUser();
					}
				});

				user.add(userInfoPasswordField);
			} else {
				continue;
			}
		}
	}

	private void SignInBtn() {
		JButton loginButton = showFormButton(SignIn);


		loginButton.setBounds(100, 380, 130, 40);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginUser();
			}
		});
	}

	private void loginUser() {
		Database database = Database.getInstance();
		database.findUser(user);
	}
}
