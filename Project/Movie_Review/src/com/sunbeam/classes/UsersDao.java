package com.sunbeam.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends SuperDao {
	private PreparedStatement signIn;
	private PreparedStatement signUp;
	private PreparedStatement editProfile;
	private PreparedStatement changePassword;

	public UsersDao() {
		super();
		try {
			signIn = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ? ");
			signUp = con.prepareStatement(
					"INSERT INTO users (user_id, first_name, last_name, email, password, mobile, birth)\n"
							+ "	  VALUES  ( default , ? ,?, ?, ?, ?, ?);");
			editProfile = con.prepareStatement(
					"UPDATE users SET first_name = ? , last_name = ? , mobile = ? ,birth = ?  WHERE user_id = ?");
			changePassword = con.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		super.close();
		if (signIn != null)
			signIn.close();
		if (signUp != null)
			signUp.close();
		if (editProfile != null)
			editProfile.close();
		if (changePassword != null)
			changePassword.close();
	}

	public Users signIn(String email, String password) {
		try {
			signIn.setString(1, email);
			signIn.setString(2, password);
			try (ResultSet rs = signIn.executeQuery()) {
				if (rs.next()) {
					Users currentUser = new Users();
					currentUser.setUserId(rs.getInt("user_id"));
					currentUser.setFirstName(rs.getString("first_name"));
					currentUser.setLastName(rs.getString("last_name"));
					currentUser.setEmail(rs.getString("email"));
					currentUser.setPassword(rs.getString("password"));
					currentUser.setMobile(rs.getString("mobile"));
					currentUser.setBirthDate(rs.getDate("birth"));
					return currentUser;
				}
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public int signUp(Users newUser) {
		try {
			signUp.setString(1, newUser.getFirstName());
			signUp.setString(2, newUser.getLastName());
			signUp.setString(3, newUser.getEmail());
			signUp.setString(4, newUser.getPassword());
			signUp.setString(5, newUser.getMobile());
			java.util.Date birthDate = newUser.getBirthDate();
			java.sql.Date sDate = new java.sql.Date(birthDate.getTime());
			signUp.setDate(6, sDate);

			int count = signUp.executeUpdate();
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}

	public int editProfile(Users newUser) {
		try {
			editProfile.setString(1, newUser.getFirstName());
			editProfile.setString(2, newUser.getLastName());
			editProfile.setString(3, newUser.getMobile());
			java.util.Date birthDate = newUser.getBirthDate();
			java.sql.Date sDate = new java.sql.Date(birthDate.getTime());
			editProfile.setDate(4, sDate);
			editProfile.setInt(5, newUser.getUserId());

			int count = editProfile.executeUpdate();
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}

	public int changePassword(String password, int userId) {
		try {
			changePassword.setString(1, password);
			changePassword.setInt(2, userId);

			int count = changePassword.executeUpdate();
			return count;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	public List<Users> displayUsers() {
		List<Users> list = new ArrayList<>();

		String sql = "SELECT * FROM users";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("user_id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					java.util.Date uDate = rs.getDate("birth");

					list.add(new Users(id, fname, lname, email, password, mobile, uDate));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
