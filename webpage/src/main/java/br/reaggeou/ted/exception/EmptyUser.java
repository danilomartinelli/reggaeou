package br.reaggeou.ted.exception;

public class EmptyUser extends Exception{

	private static final long serialVersionUID = -5477520826579903427L;
	
	public EmptyUser (String message) {
		super(message);
	}
}
