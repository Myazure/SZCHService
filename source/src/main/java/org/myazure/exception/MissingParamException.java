package org.myazure.exception;

public class MissingParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingParamException() {
		super();
	}

	public MissingParamException(String message) {
		super(message);
	}

	public MissingParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingParamException(Throwable cause) {
		super(cause);
	}

}
