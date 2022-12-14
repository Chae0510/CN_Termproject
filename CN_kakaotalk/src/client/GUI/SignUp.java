package client.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import Database.Database;
import etc.CommonWord;
import Database.User;
import etc.UserInfoPanel;

@SuppressWarnings("serial")
public class SignUp extends UserInfoPanel {
	private final String SIGN_UP = "Sign Up";
	private ArrayList<JTextField> userInfos = new ArrayList<JTextField>();

	private User user;

	public SignUp() {

		showFormTitle("Sign UP");
		SignIninputData();
		SignUpBtn();

	}

	public void SignIninputData() {

		int y_coordinate = 120;
		for (CommonWord tradeWord : CommonWord.values()) {
			if (tradeWord.getNum() >= CommonWord.ID.getNum() && tradeWord.getNum() <= CommonWord.BIRTH.getNum()) {

				idLabel = new JLabel(tradeWord.getText());
				idLabel.setFont(new Font("굴림", Font.BOLD, 14));
				idLabel.setBounds(30, y_coordinate, 200, 50);
				add(idLabel);

				if(tradeWord.getNum() == CommonWord.PASSWORD.getNum()) {

					userInfoPasswordField = new JPasswordField(10);
					userInfoPasswordField.setBounds(30, y_coordinate + 35, 325, 30);
					add(userInfoPasswordField);

					userInfos.add(userInfoPasswordField);

				} else {

					signIntext = new JTextField(10);
					signIntext.setBounds(30, y_coordinate + 35, 325, 30);
					add(signIntext);

					userInfos.add(signIntext);
				}

				y_coordinate += 55;


			} else {
				continue;
			}
		}


	}

	private void SignUpBtn() {

		JButton signupButton = showFormButton(SIGN_UP);
		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(checkError() == 1) {
					createUser();
				}


			}
		});
	}

	private int checkError() {
		if(userInfos.get(0).getText().equals("") || userInfos.get(1).getText().equals("") ||
				userInfos.get(2).getText().equals("") || userInfos.get(3).getText().equals("") ||
				userInfos.get(4).getText().equals("") || userInfos.get(5).getText().equals("")) {
			JOptionPane.showMessageDialog(this,"다시 입력해주세요.");
			return 0;
		}

		return 1;

	}
	private void createUser() {

		user = new User(userInfos.get(0).getText(), userInfos.get(1).getText(), userInfos.get(2).getText(),
				userInfos.get(3).getText(), userInfos.get(4).getText(), userInfos.get(5).getText());

		Database database = Database.getInstance();
		database.insertDB(user);
	}
}
