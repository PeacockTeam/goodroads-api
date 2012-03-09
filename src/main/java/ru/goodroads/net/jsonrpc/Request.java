package ru.goodroads.net.jsonrpc;

import com.google.gson.Gson;

@SuppressWarnings("unused")
public class Request {
	private String method;
	private Object params;
	
	public Request(String method, Object params) {
		this.method = method;
		this.params = params;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();

		return gson.toJson(this);
	}
}
