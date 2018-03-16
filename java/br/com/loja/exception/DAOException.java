package br.com.loja.exception;

import org.hibernate.exception.ConstraintViolationException;

public class DAOException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public DAOException(Exception e) {
		e.printStackTrace();
		this.setMessage("Erro ao realizar a operação");
		if (e.getCause() instanceof ConstraintViolationException)
			this.setMessage("Conflito ao realizar operação");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
