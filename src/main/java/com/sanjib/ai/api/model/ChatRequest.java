package com.sanjib.ai.api.model;

import lombok.Data;

@Data
public class ChatRequest {

	private String question;

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	} 
	
}
