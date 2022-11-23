package br.com.fiap.fintech.exception;

public class EncryptorException extends Exception {
	
	private static final long serialVersionUID = 6093798453434076011L;

	public EncryptorException() {
		super();
	}

	/**
	 * @param message
	 */
	public EncryptorException(String message) {
		super(message);
	}
	
	
	
}
