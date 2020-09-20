package jp.syned.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FildMessage> list = new ArrayList<>();
	
	public ValidationError(int i, String msg, Long timeStamp) {
		super(i, msg, timeStamp);
	}
	
	public List<FildMessage> getErrors() {
		return list;
	}

	public void addError(String fildName, String message) {
		list.add(new FildMessage(fildName, message));
	}
}
