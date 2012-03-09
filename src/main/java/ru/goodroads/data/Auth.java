package ru.goodroads.data;

import com.google.gson.annotations.SerializedName;

public class Auth {
	private String login;
	@SerializedName("secret")
	private String digest;
	
	public Auth(String login, String digest) {
		this.login = login;
		this.digest = digest;
	}
	
	@Override
	public String toString() {
		return login.toString() + ":" + digest.toString();
	}
}
