package com.service;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerator {

	
	public String generateToken() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuffer token = new StringBuffer();

		for (int i = 0; i < 32; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			token.append(AlphaNumericString.charAt(index));
		}

		return token.toString();
	}

	public static void main(String[] args) {
		System.out.println(new TokenGenerator().generateToken());
	}
	
	
}
