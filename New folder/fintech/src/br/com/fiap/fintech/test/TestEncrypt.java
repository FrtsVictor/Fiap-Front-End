package br.com.fiap.fintech.test;

import br.com.fiap.fintech.exception.EncryptorException;
import br.com.fiap.fintech.singleton.Encryptor;

public class TestEncrypt {

	public static void main(String[] args) throws EncryptorException {		
	    String password = "ILoveJava";	    
	    Encryptor.encrypt(password);

	    System.out.println(Encryptor.encrypt(password));
	    System.out.println(Encryptor.encrypt(password));
	}
}
