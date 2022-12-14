
package client.GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;

// Call Class
import Database.Database;
import server.Message;
import etc.CommonPanel;
import etc.UsingImage;
import etc.UserBtn;

@SuppressWarnings("serial")
public class profileGUI extends CommonPanel {
	private JLabel jLabel;
	private Image profileImg = UsingImage.getImage("src/Image/user.png");
	private Image searchImg = UsingImage.getImage("src/Image/search_button.png");
	Image profileImg2 = profileImg.getScaledInstance(73, 65, java.awt.Image.SCALE_SMOOTH);
	Image searchImg2 = searchImg.getScaledInstance(350, 60, java.awt.Image.SCALE_SMOOTH);
	public static UserBtn userBtn;
	public static MenuPBtn menuBtn;

	public static ArrayList<ChatFrame> chatPanelName = new ArrayList<ChatFrame>();

	Database database;

	public profileGUI() {
		database = Database.getInstance();

		moveAddPanel();
		myProfileBtn();

		friendListTitle("My Friend List");
		friendList();

	}

	private void moveAddPanel() {

		ImageIcon imgIcon2 = new ImageIcon(searchImg2);
		menuBtn = new MenuPBtn("Search");
		menuBtn.setBounds(12, 20, 140, 50);
		menuBtn.setOpaque(true);

		add(menuBtn);

		menuBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchFriend addfrinedpanel = new searchFriend();
				MainPanel.frame.resetData(addfrinedpanel);
			}
		});
	}

	private void myProfileBtn() {
		ImageIcon imageIcon = new ImageIcon(profileImg2);
		userBtn = new UserBtn(imageIcon);
		userBtn.setText(database.username);
		userBtn.setBounds(30, 120, 325, 80);
		add(userBtn);
		userBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userBtn.getText().contains("text")) {

				} else {
					String messageType = "text";
					Message message = new Message(profileGUI.this.database.username, "채팅이 시작되었습니다.",
							LocalTime.now(), messageType, profileGUI.this.database.username);
					ChatFrame c = new ChatFrame(imageIcon, profileGUI.this.database.username);
					new ChatroomFrame(c, profileGUI.this.database.username);
					chatPanelName.add(c);

					Database database = Database.getInstance();
					database.clientSocket.send(message);
				}
			}
		});
	}

	private void friendListTitle(String text) {
		jLabel = new JLabel(text);
		jLabel.setFont(new Font("굴림", Font.BOLD, 14));
		jLabel.setBounds(30, 220, 200, 30);
		add(jLabel);
	}

	private void friendList() {
		FriendVector jpanel = new FriendVector();

		JScrollPane scroller = new JScrollPane(jpanel);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBounds(30, 250, 325, 200);
		add(scroller);
	}


}
