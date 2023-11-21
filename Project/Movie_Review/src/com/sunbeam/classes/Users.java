package com.sunbeam.classes;

import java.util.Date;

public class Users implements Comparable<Users> {
	int userId;
	String firstName;
	String lastName;
	String email;
	String password;
	String mobile;
	java.util.Date birthDate;

	public Users() {
	}

	public Users(int userId, String firstName, String lastName, String email, String password, String mobile,
			Date birthDate) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.birthDate = birthDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public java.util.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserId = ").append(userId).append(", firstName = ").append(firstName).append(", lastName = ")
				.append(lastName).append(", email = ").append(email).append(", mobile = ").append(mobile)
				.append(", birthDate = ").append(birthDate);
		return builder.toString();
	}

	@Override
	public int compareTo(Users arg0) {
		int diff = this.email.compareTo(arg0.getEmail());
		return diff;
	}

}
