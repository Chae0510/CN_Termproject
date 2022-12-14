
package client.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Database.Database;
import etc.*;

@SuppressWarnings("serial")
public class searchFriend extends UserInfoPanel {

	private final String SEARCH = "Search";

	private final String GOBACK = "Cancel";

	public static MainFrame frame;

	private String friendid;

	public searchFriend() {
		showFormTitle("Search Your Friend");
		SignIninputData();
		showSearchButton();
		showBackButton();
	}

	public void SignIninputData() {
		idLabel = new JLabel("Input ID :");
		idLabel.setFont(new Font("굴림", Font.BOLD, 14));
		idLabel.setBounds(30, 200, 200, 50);
		add(idLabel);

		signIntext = new JTextField(10);
		signIntext.setBounds(130, 200, 200, 50);
		add(signIntext);


	}
	private void showSearchButton() {

		JButton searchButton = showFormButton(SEARCH);
		searchButton.setBounds(100, 300, 180, 40);
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				friendid = signIntext.getText();
				addUser(friendid);

			}
		});
	}

	private void showBackButton() {

		JButton gobackButton = showFormButton(GOBACK);
		gobackButton.setBounds(100, 350, 180, 40);
		gobackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profileGUI profileGUI = new profileGUI();
				MainPanel.frame.resetData(profileGUI);
			}
		});
	}

	private void addUser(String friendid) {

		Database database = Database.getInstance();
		database.addFriendDB(friendid);
	}
}