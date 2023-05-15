package com.alura.aurelio.videosbackend.infra;

import java.util.Arrays;
import java.util.List;

public class CustomException extends RuntimeException {
	
	private List<String> extraMessage;
	
	private static final long serialVersionUID = 1L;
	
	public CustomException(String message) {
		super(message);
		this.extraMessage = Arrays.asList();
	}
	
	public CustomException(String message, List<String> extraMessage) {
		super(message);
		this.extraMessage = extraMessage;
	}

	public List<String> getExtraMessage() {
		return this.extraMessage;
	}

	public void setExtraMessage(List<String> extraMessage) {
		this.extraMessage = extraMessage;
	}
	
	public boolean hasExtraMessage() {
		return this.extraMessage != null || this.extraMessage.isEmpty();
	}
	

}
