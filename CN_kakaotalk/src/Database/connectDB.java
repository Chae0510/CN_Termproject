package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

public class connectDB {

	Connection con;

	PreparedStatement pstmt;

	public String username = null;

	//jdbc를 이용해 connection을 만듬.
	public void connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // jdbc driver load

			String url =  "jdbc:mysql://localhost/messenger";
			String user = "root";
			String passwd = "tia010510!";       // root 계정 비밀번호

			con = DriverManager.getConnection(url, user, passwd);

			System.out.println(con);
			System.out.println("MySQL Server Link Successful");

		} catch (Exception e) {
			e.getMessage();
		}
	}

	//DB와의 연결 해제
	public void close() {
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean SignUp(User user) {

		connect();
		String sql = "insert into user values(?,?,?,?,?,?)";

		boolean flag;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getUnickname());
			pstmt.setString(5, user.getUemail());
			pstmt.setString(6, user.getUbirth());
			pstmt.executeUpdate();

			flag = true;

		} catch (SQLException e) {
			flag = false;
		}

		close();

		return flag;

	}

	public boolean addFriend(String friendid) {

		String userid = findUserId();
		String fid = friendid;
		connect();
		String sql = "insert into friend(userid, friendid) values (?,?)";

		boolean isAdd = false;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, fid);
			pstmt.executeUpdate();

			isAdd = true;

		} catch (SQLException e) {
			isAdd = false;
		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fid);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();

			isAdd = true;
		} catch (SQLException e) {
			isAdd = false;
		}

		close();

		return isAdd;

	}

	public int searchUser(String userid) {

		connect();
		String sql = "select * from user where uid = %?";
		String id = userid;

		int result = 0;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	public String findUser(ArrayList<JTextField> userInfos) {

		connect();
		String sql = "select uname from user where uid =? and password=?";
		String uid = userInfos.get(0).getText();
		String password = userInfos.get(1).getText();

		String uname = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				uname = rs.getString("uname");
			}

			username = uname;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

		return username;
	}

	public ArrayList<String> friendList() {

		String uid = findUserId();
		connect();
		ArrayList<String> friends = new ArrayList<String>();
		String sql = "select m.uname from user m, friend f where m.uid = f.friendid and f.userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				friends.add(rs.getString("uname"));
			}
		} catch (SQLException e) {
		}
		close();
		System.out.println(friends.size());
		return friends;
	}

	private String findUserId() {

		connect();
		String sql = "select uid from user where uname = ?";
		String uid = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				uid = rs.getString("uid");
			}
		} catch (SQLException e) {
		}
		close();
		return uid;
	}


}
