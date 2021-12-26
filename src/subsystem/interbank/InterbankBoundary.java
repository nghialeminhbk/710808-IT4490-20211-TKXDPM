package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;
import utils.Utils;

import java.util.logging.Logger;

public class InterbankBoundary {

	private static Logger LOGGER = Utils.getLogger(InterbankSubsystemController.class.getName());

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
