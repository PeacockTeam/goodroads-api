package ru.goodroads.api;

import java.util.ArrayList;

import ru.goodroads.data.Auth;
import ru.goodroads.data.Hole;
import ru.goodroads.data.HoleSet;
import ru.goodroads.data.Register;
import ru.goodroads.net.jsonrpc.JSONRPCClient;
import ru.goodroads.net.jsonrpc.JSONRPCClientException;
import ru.goodroads.utils.SHA256;

public class GoodRoadsClient {

	private static final String GOODROADS_URL = "http://goodroads.ru/another/api.php";
	// private static final String GOODROADS_URL = "http://localhost/another/api.php";
	
	// XXX: one instance?
	private final JSONRPCClient rpcClient = new JSONRPCClient(GOODROADS_URL, new GoodRoadsErrorHandler());
	
	private String mSessionKey = null;
	
	public GoodRoadsClient() {
		// TODO: initialize auth token, currently through cookie.
		// Steph think about add it in json as 'session'
	}
	
	public boolean register(String login, String password, String email) throws JSONRPCClientException {
		String digest = SHA256.compute(password);
		
		Register register = new Register(login, digest, email);
		
		Object result = rpcClient.call("register", register);

		System.out.println(result);
		
		return true;
	}
	
	public boolean auth(String login, String password) throws JSONRPCClientException {
		String digest = SHA256.compute(password);
		
		Auth auth = new Auth(login, digest);
		
		String session = (String) rpcClient.call("auth", auth);

		if (session.compareTo("") == 0) {
			throw new GoodRoadsClientException("Session not found");
		}
		
		setSessionKey(session);
		
		System.out.println("Session key: " + session);
		
		return true;
	}
	
	public boolean addHoleSet(HoleSet holeSet) throws JSONRPCClientException {
		Object result = rpcClient.call("addHoleSet", holeSet);
		
		System.out.println(result);
		
		return true;
	}
	
	public boolean addHole(Hole hole) throws JSONRPCClientException {
		Object result = rpcClient.call("addHole", hole);
		
		System.out.println(result);
		
		return true;
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		GoodRoadsClient grc = new GoodRoadsClient();
		
		try {
			//grc.register("hello2", "omgomgomg", "hello2@mail.ru");
			grc.setSessionKey("Q8dz5UmGC4u7Bg05M61FrJhdMGKDE4tV2MKvhx3jqmgsJgmVbdbJn9mzFx4Jqmfi");
			
			grc.auth("hello2", "omgomgomg");

			//grc.addHole(new Hole());

			grc.addHoleSet(new HoleSet(new ArrayList<Hole>() {
				{
					add(new Hole());
					add(new Hole());
				}
			}));
		} catch (GoodRoadsClientException e) {
			// Some response error (not valid login/pwd or anything)
			e.printStackTrace();
		} catch (JSONRPCClientException e) {
			// Not valid response (not jsonrpc)?
			e.printStackTrace();
		}
	}

	public void setSessionKey(String mSessionKey) {
		this.mSessionKey = mSessionKey;
	}

	public String getSessionKey() {
		return mSessionKey;
	}
}
