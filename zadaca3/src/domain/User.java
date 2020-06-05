package domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_roles")
	String roles;

	public User() {
	}

	public User(String userName, String password, String roles) {
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public List<String> getRoles() {
		return Arrays.asList(roles.split(","));
	}

	public boolean isAdmin(List<String> roles) {
		boolean result = false;
		for (String r : roles) {
			if (r.equals("admin")) {
				result = true;
				break;
			}
		}
		return result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRolesString() {
		return this.roles;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User userName = " + userName + ", password = " + password + ", roles = " + roles + "";
	}

}
