package br.com.fiap.fintech.exception;

public class EntityNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = -7614721602003957335L;

	public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
