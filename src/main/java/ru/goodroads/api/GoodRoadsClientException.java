package ru.goodroads.api;

import ru.goodroads.data.ErrorCode;

public class GoodRoadsClientException extends Exception {

	private final int code;
	private static final long serialVersionUID = 1L;
	
	public GoodRoadsClientException(ErrorCode ec) {
		super(ec.getDescription());
		this.code = ec.getCode();
	}

	public GoodRoadsClientException(String message, int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
