package jp.syned.cursomc.resources.exception;

import java.io.Serializable;

public class FiledMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fildName;
	private String message;
	
	public FiledMessage() {}

	public FiledMessage(String fildName, String message) {
		super();
		this.fildName = fildName;
		this.message = message;
	}

	public String getFildName() {
		return fildName;
	}

	public void setFildName(String fildName) {
		this.fildName = fildName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
