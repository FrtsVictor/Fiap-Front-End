package br.com.fiap.fintech.singleton;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.fiap.fintech.exception.EncryptorException;

public class Encryptor {
	
	private Encryptor() {
	    throw new IllegalStateException("Utility class");
	}

	public static String encrypt(String value) throws EncryptorException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");	    
			md.update(value.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
		    return new BigInteger(1, digest).toString(16);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
			throw new EncryptorException(e.getMessage());
		}
	}
}
