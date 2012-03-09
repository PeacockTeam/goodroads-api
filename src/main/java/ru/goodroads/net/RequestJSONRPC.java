package ru.goodroads.net;

import com.google.gson.Gson;

@SuppressWarnings("unused")
public class RequestJSONRPC {
	private String method;
	private Object params;
	
	public RequestJSONRPC(String method, Object params) {
		this.method = method;
		this.params = params;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();

		return gson.toJson(this);
	}
}
