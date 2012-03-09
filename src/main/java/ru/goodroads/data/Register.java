package ru.goodroads.data;

public class Register {
	private String login;
	private String password;
	private String email;
	
	public Register(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return login + ":" + password + ":" + email;
	}
}
