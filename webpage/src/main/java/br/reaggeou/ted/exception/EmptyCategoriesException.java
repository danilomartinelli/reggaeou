package br.reaggeou.ted.exception;

public class EmptyCategoriesException extends Exception{

	private static final long serialVersionUID = 7115909805150855514L;
	
	public EmptyCategoriesException(String message) {
		super(message);
	}
}
