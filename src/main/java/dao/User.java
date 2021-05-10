package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	int id;
	String name;
	String surnames;
	String userName;
	String password;
	String email;
	String country;
	Date signInDate;
	Date lastLogin;
	
	public User(ResultSet result) {
		try {
			
			this.id = result.getInt("id");
			this.name = result.getString("name");
			this.surnames = result.getString("surnames");
			this.userName = result.getString("user_name");
			this.password = result.getString("password");
			this.email = result.getString("email");
			this.country = result.getString("country");
			this.signInDate = result.getDate("sign_in_date");
			this.lastLogin = result.getDate("last_login");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public User(ResultSet result, boolean loginSuccesfull) {
		try {
			this.userName = result.getString("user_name");
			this.country = result.getString("country");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
