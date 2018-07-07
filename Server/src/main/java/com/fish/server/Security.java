package com.fish.server;

import java.security.NoSuchAlgorithmException;
import java.security.spec.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


import com.fish.core.util.Utils;

public class Security {
	
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final SecretKeyFactory FACTORY = getAlgorithm();
	
	public static byte[] getHashedPassword(char[] password, byte[] salt, byte[] pepper, int iterations, int derivedKeyLength) {
		byte[] saltPlusPepper = Utils.concat(salt, pepper);
	    KeySpec spec = new PBEKeySpec(password, saltPlusPepper, iterations, derivedKeyLength * 8);

	    try {
			return FACTORY.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
	}
	
	
	private static SecretKeyFactory getAlgorithm() {
		try {
			return SecretKeyFactory.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
		}
	}


	private Security() {
	}

}
