package ru.goodroads.net.jsonrpc;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

// TODO:
//
// - add cookie support?
// - think on deserialize result data. Add Class.type in 'call'?

public class JSONRPCClient {

	private String mUrl;
	private IErrorHandler errorHandler = new DefaultErrorHandler();
	private HttpClient httpclient = null;
	
	public JSONRPCClient(String url, IErrorHandler errorHandler) {
		this.mUrl = url;
		this.errorHandler = errorHandler;
				        
        this.httpclient = new DefaultHttpClient();
	}
	
	public IErrorHandler getErrorHandler() {
		return errorHandler;
	}
	
	public void setErrorHandler(IErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}
	
	public Response call(Request request) throws JSONRPCClientException {
		
		System.out.println(request);
		
		Response response = new Response(postData(request.toString()));
		
		getErrorHandler().checkIt(response);
		
		return response;
	}
	
	public Object call(String method, Object params) throws JSONRPCClientException {		
		return call(new Request(method, params)).getResult();
	}
	
	private String postData(String request) {
	    HttpPost httppost = new HttpPost(mUrl);

	    try {        
	    	httppost.setHeader("Accept", "application/json");
	    	
	    	// XXX: we use json-rpc, but server-side accept data as www-form, 
	    	// not application/json
	    	//httppost.setHeader("Content-Type", "application/json");
	    	httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	    	
	    	// TODO: add gzip/deflate, through protocol intercepter or another way
	    	// 
	    	//httppost.setHeader("Accept-Encoding", "gzip,deflate");	    
	    	
	        // XXX: 'data=' is redundantly, but needs. Fix it on server side
	        httppost.setEntity(new StringEntity("data=" + request + "\r\n"));
	        
	        HttpResponse response = httpclient.execute(httppost);
	        
	        HttpEntity entity = response.getEntity();
	        
	        if (entity != null)
	        	return EntityUtils.toString(response.getEntity());
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    return null;
	}
}
