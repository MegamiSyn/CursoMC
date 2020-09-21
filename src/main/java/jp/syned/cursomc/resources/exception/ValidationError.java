package jp.syned.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FiledMessage> list = new ArrayList<>();
	
	public ValidationError(int i, String msg, Long timeStamp) {
		super(i, msg, timeStamp);
	}
	
	public List<FiledMessage> getErrors() {
		return list;
	}

	public void addError(String fildName, String message) {
		list.add(new FiledMessage(fildName, message));
	}
}
