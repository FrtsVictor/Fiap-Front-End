package br.com.fiap.fintech.exception;

public class DataBaseConnectionException extends RuntimeException{

	private static final long serialVersionUID = 8927625155327467975L;

	public DataBaseConnectionException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public DataBaseConnectionException(String message) {
		super(message);
	}
}
