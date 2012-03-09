package ru.goodroads.api;

import ru.goodroads.data.ErrorCode;
import ru.goodroads.net.jsonrpc.IErrorHandler;
import ru.goodroads.net.jsonrpc.Response;

public class GoodRoadsErrorHandler implements IErrorHandler {
	public void checkIt(Response response) throws GoodRoadsClientException {
		checkErrorCode(response);
	}
	
	private void checkErrorCode(Response response) throws GoodRoadsClientException {
		int code = response.getErrorCode();
		String info = response.getErrorInfo();
		
		if (ErrorCode.fromCode(code) == null || ErrorCode.fromCode(code) != ErrorCode.OK) {
			throw new GoodRoadsClientException(info + " (" + code + ")", code);
		}
	}
}
