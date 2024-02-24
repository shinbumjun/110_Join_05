package com.feb.test.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.ObjectUtils;

public class Sha512Encoder {
	
	private static Sha512Encoder instance;
	
	public static Sha512Encoder getInstance() {
		if (ObjectUtils.isEmpty(instance)) {
			instance = new Sha512Encoder();
		}
		return instance;
	}
	
	public String getSecurePassword(String passwordToHash){
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
