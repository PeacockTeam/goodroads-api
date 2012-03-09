package ru.goodroads.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static String compute(String input) {
		MessageDigest mdigest;
		StringBuffer hexString = new StringBuffer();
		
		try {
			mdigest = MessageDigest.getInstance("SHA-256");

			mdigest.update(input.getBytes());

			byte[] digest = mdigest.digest();

			
			for (int i = 0; i < digest.length; i++) {
				String hex = Integer.toHexString(0xFF & digest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			// XXX: this is not happen
			e.printStackTrace();
		}

		return hexString.toString();
	}
}
