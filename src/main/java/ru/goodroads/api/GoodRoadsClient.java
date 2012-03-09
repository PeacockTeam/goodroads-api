package ru.goodroads.api;

import java.util.ArrayList;

import ru.goodroads.data.Auth;
import ru.goodroads.data.Dummy;
import ru.goodroads.data.ErrorCode;
import ru.goodroads.data.Hole;
import ru.goodroads.data.HoleSet;
import ru.goodroads.data.Register;
import ru.goodroads.net.ClientJSONRPC;
import ru.goodroads.net.ClientJSONRPCException;
import ru.goodroads.net.ResponseJSONRPC;
import ru.goodroads.utils.SHA256;

public class GoodRoadsClient {

	private static final String GOODROADS_URL = "http://goodroads.ru/another/api.php";
	
	private ClientJSONRPC rpcClient = new ClientJSONRPC(GOODROADS_URL);
	
	public GoodRoadsClient() {
		// TODO: initialize auth token (currently through cookie)
	}
	
	public boolean register(String login, String password, String email) throws ClientJSONRPCException, GoodRoadsClientException {
		String digest = SHA256.compute(password);
		
		Register register = new Register(login, digest, email);
		
		ResponseJSONRPC result = rpcClient.call(register);

		checkErrorCode(result);
		
		System.out.println(result.getResult());
		
		return true;
	}
	
	public boolean auth(String login, String password) throws ClientJSONRPCException, GoodRoadsClientException {
		String digest = SHA256.compute(password);
		
		Auth auth = new Auth(login, digest);
		
		ResponseJSONRPC result = rpcClient.call(auth);

		checkErrorCode(result);
		
		System.out.println(result.getResult());
		
		return true;
	}
	
	public boolean addHoleSet(HoleSet holeSet) throws ClientJSONRPCException, GoodRoadsClientException {
		
		ResponseJSONRPC result = rpcClient.call(holeSet);
		
		checkErrorCode(result);
		
		System.out.println(result.getResult());
		
		return true;
	}
	
	public boolean addHole(Hole hole) throws ClientJSONRPCException, GoodRoadsClientException {
		
		ResponseJSONRPC result = rpcClient.call(hole);
		
		checkErrorCode(result);
		
		System.out.println(result);
		
		return true;
	}
	
	// TODO: for more cleanly register error code handler in ClientJSONRPC
	private void checkErrorCode(ResponseJSONRPC response) throws GoodRoadsClientException {
		int code = response.getErrorCode();
		String info = response.getErrorInfo();
		
		if (ErrorCode.fromCode(code) == null || ErrorCode.fromCode(code) != ErrorCode.OK) {
			throw new GoodRoadsClientException(info + " (" + code + ")", code);
		}
	}
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		GoodRoadsClient grc = new GoodRoadsClient();
		
		try {
			//grc.register("hello2", "omgomgomg", "hello2@mail.ru");
			grc.auth("hello2", "omgomgomg");

			grc.addHole(new Hole(1.2, 1.2, 1.2, 1));

			grc.addHoleSet(new HoleSet(new ArrayList<Hole>() {
				{
					add(new Hole(1.2, 1.2, 1.2, 1));
					add(new Hole(1.2, 1.2, 1.2, 1));
				}
			}));
		} catch (ClientJSONRPCException e) {
			// Not valid response (not jsonrpc)?
			e.printStackTrace();
		} catch (GoodRoadsClientException e) {
			// Some response error (not valid login/pwd or anything)
			e.printStackTrace();
		}
	}
}
