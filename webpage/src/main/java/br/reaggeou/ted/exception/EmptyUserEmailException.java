package br.reaggeou.ted.exception;

public class EmptyUserEmailException extends Exception {

	private static final long serialVersionUID = -6837502348731452298L;
	
	public EmptyUserEmailException(String message) {
		super(message);
	}
	
}
