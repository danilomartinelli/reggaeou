package br.reaggeou.ted.exception;

public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -1513368330526159985L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
}
