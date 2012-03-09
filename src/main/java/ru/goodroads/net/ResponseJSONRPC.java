package ru.goodroads.net;

import com.google.gson.Gson;

public class ResponseJSONRPC {
	private Object result;
	private ErrorJSONRPC error;

	private class ErrorJSONRPC {
		private String info;
		private int code;
	}
	
	public ResponseJSONRPC(String response) throws ClientJSONRPCException {
		Gson gson = new Gson();
		
		ResponseJSONRPC tmp = (ResponseJSONRPC) gson.fromJson(response, ResponseJSONRPC.class);
		
		if (tmp == null || tmp.error == null || tmp.result == null) {
			throw new ClientJSONRPCException("not json rpc response: \"" + response + "\"");
		}
		
		this.result = tmp.result;
		this.error = tmp.error;
	}
	
	public Object getResult() {
		return result;
	}
	
	public int getErrorCode() {
		return error.code;
	}
	
	public String getErrorInfo() {
		return error.info;
	}
	
	@Override
	public String toString() {
		return result + " " + error.info + " (" + error.code + ")" ;
	}
}
