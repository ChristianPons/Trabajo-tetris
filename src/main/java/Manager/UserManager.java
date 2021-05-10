package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.User;

public class UserManager {


	public List<User> getUsers(Connection con) {
		try (Statement stmt = con.createStatement()) {
			ResultSet result = stmt.executeQuery("SELECT * FROM users");
			result.beforeFirst();

			List<User> allUsers = new ArrayList<>();

			while (result.next()) {
				allUsers.add(new User(result));
			}

			return allUsers;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User FindByMailandPassword(Connection con, String email, String password) {
		try (PreparedStatement stmt = con
				.prepareStatement("SELECT user_name, country FROM users WHERE email LIKE ? AND password LIKE ?")) {

			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet result = stmt.executeQuery();
			result.first();
			stmt.close();

			return new User(result, true);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public void insertNewUser(Connection con, int id, String name, String surnames, String userName, String password, String email,
			String country, Date signInDate, Date lastLogin) {
		try(PreparedStatement stmt = con.prepareStatement("INSERT INTO TABLE users VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)")) {
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, surnames);
			stmt.setString(4, userName);
			stmt.setString(5, password);
			stmt.setString(6, email);
			stmt.setString(7, country);
			stmt.setDate(8, signInDate);
			stmt.setDate(9, lastLogin);
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void changePassword(Connection con, String userName, String newPassword) {
		try (PreparedStatement stmt = con.prepareStatement("UPDATE users SET password = ? WHERE user_name LIKE ?")){
			stmt.setString(1, newPassword);
			stmt.setString(2, userName);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
