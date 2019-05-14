package br.reaggeou.ted.exception;

public class EmptyUserException extends Exception{

	private static final long serialVersionUID = -5477520826579903427L;
	
	public EmptyUserException (String message) {
		super(message);
	}
}
