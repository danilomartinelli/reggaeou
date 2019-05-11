package br.reaggeou.ted.exception;

public class UserAlreadyExists extends Exception{

	private static final long serialVersionUID = -1513368330526159985L;
	
	public UserAlreadyExists(String message) {
		super(message);
	}
	
}
