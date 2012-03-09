package ru.goodroads.net.jsonrpc;

import com.google.gson.Gson;

public class Response {
	private Object result;
	private ErrorJSONRPC error;

	private class ErrorJSONRPC {
		private String info;
		private int code;
	}
	
	public Response(String response) throws JSONRPCClientException {
		Gson gson = new Gson();
		
		Response tmp = (Response) gson.fromJson(response, Response.class);
		
		if (tmp == null || tmp.error == null || tmp.result == null) {
			throw new JSONRPCClientException("not json rpc response: \"" + response + "\"");
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
