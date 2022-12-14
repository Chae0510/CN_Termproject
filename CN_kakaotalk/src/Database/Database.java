package Database;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.ClientSocket;
import client.GUI.profileGUI;
import client.GUI.MainPanel;
import client.GUI.searchFriend;

public class Database {

	private static Database singleton = new Database();

	public String username = null;

	public ArrayList<String> userinfo = null;

	public ClientSocket clientSocket;

	public String msg = null;

	connectDB connectDB;

	private Database() {

		clientSocket = new ClientSocket();

		connectDB = new connectDB();

	}

	public static Database getInstance() {

		return singleton;
	}

	public void insertDB(User user) {

		boolean isInsert = connectDB.SignUp(user);

		if (isInsert) {
			MainPanel mainPanel = new MainPanel(MainPanel.frame);
			MainPanel.frame.resetData(mainPanel);
			JOptionPane.showMessageDialog(mainPanel, "Sign up Success", "Sign Up", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Please fill in all the information", "Sign up Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println("Sign up Failed > Member information not entered");

		}
	}

	public void findUser(ArrayList<JTextField> userInfos) {

		username = connectDB.findUser(userInfos);

		if (username != null) {
			profileGUI profileGUI = new profileGUI();
			MainPanel.frame.resetData(profileGUI);
			clientSocket.startClient();
			JOptionPane.showMessageDialog(profileGUI, "Sign In Success!", "Sign In", JOptionPane.WARNING_MESSAGE);
		} else if (username == null) {
			JOptionPane.showMessageDialog(null, "Please fill in all the information", "Sign In Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println("Sign in Failed > Member information not entered");

		}
	}

	public void addFriendDB(String friendId) {

		int already = connectDB.searchUser(friendId);

		boolean isAdd = false;

		if (already == 0) {
			searchFriend addfriend = new searchFriend();
			JOptionPane.showMessageDialog(addfriend, "There is no data", "Error!", JOptionPane.WARNING_MESSAGE);
		} else if (already == 1){

			isAdd = connectDB.addFriend(friendId);

			if (isAdd) {
				profileGUI profileGUI = new profileGUI();
				MainPanel.frame.resetData(profileGUI);
				JOptionPane.showMessageDialog(profileGUI, "Success", "Success!", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Failed", "Failed", JOptionPane.ERROR_MESSAGE);
				System.out.println("Failed > Member information not entered");

			}
		}

	}

	//내 친구 리스트를 return
	public ArrayList<String> friendList() {

		ArrayList<String> friends = new ArrayList<String>();
		friends = connectDB.friendList();

		return friends;
	}

}
