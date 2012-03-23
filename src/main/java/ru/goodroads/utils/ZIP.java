package ru.goodroads.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;


public class ZIP {

	public static byte[] compress(String data, String fileName) {

		if (data == null || data.length() == 0) {
			return null;
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		try {
			zos.putNextEntry(new ZipEntry(fileName));
			zos.write(data.getBytes());
			zos.closeEntry();
			zos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return bos.toByteArray();
	}
	
	public static String compressToString(String data, String fileName) {
		return Base64.encodeBase64URLSafeString(compress(data, fileName));
	}
}
