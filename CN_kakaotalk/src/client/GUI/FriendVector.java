/**
 * 친구 리스트.
 * 내 친구의 아이디에 해당하는 이름과 사진을 JButton을 이용해서 보여줌
 */
package client.GUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.*;
import javax.swing.*;

//Call Class
import Database.Database;
import server.Message;
import etc.UsingImage;
import etc.UserBtn;

@SuppressWarnings("serial")
public class FriendVector extends JPanel {
	private ArrayList<String> friendList;
	private ArrayList<ImageIcon> friendData = new ArrayList<ImageIcon>();
	public static ArrayList<JButton> fBtn = new ArrayList<JButton>();


	public FriendVector() {
		Database database = Database.getInstance();

		friendList = database.friendList();
		int friendNum = friendList.size();

		if (friendNum == 0) {


		} else {
			setLayout(new GridLayout(friendNum, 0));
			for (int index = 0; index < friendNum; index++) {
				Image img = UsingImage.getImage("src/Image/profile2.png");
				ImageIcon imageIcon = new ImageIcon(img);
				UserBtn userprofileButton = new UserBtn(imageIcon);
				userprofileButton.setText(friendList.get(index));
				add(userprofileButton);
				friendData.add(imageIcon);
				fBtn.add(userprofileButton);
			}
			for (int i = 0; i < friendNum; i++) {
				fBtn.get(i).putClientProperty("page", i);
				fBtn.get(i).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int idx = (Integer) ((JButton) e.getSource()).getClientProperty("page");
						Message message = new Message(database.username, "request", LocalTime.now(), "request",
								friendList.get(idx));

						Database database = Database.getInstance();
						database.clientSocket.send(message);
					}
				});
			}

		}

	}

}
