package br.reaggeou.ted.exception;

public class EmptyUserEmail extends Exception {

	private static final long serialVersionUID = -6837502348731452298L;
	
	public EmptyUserEmail(String message) {
		super(message);
	}
	
}
