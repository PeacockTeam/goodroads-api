package ru.goodroads.net.jsonrpc;

public interface IErrorHandler {
	public void checkIt(Response response) throws JSONRPCClientException;
}
