package br.reaggeou.ted.exception;

public class NonExistentUserException extends Exception{

	private static final long serialVersionUID = 8197924051447896820L;
	
	public NonExistentUserException(String message) {
		super(message);
	}
	
}
