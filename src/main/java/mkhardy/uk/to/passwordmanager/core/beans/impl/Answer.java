package mkhardy.uk.to.passwordmanager.core.beans.impl;

public final class Answer {
	private final String value;
	private final Question question;
	private final User user;
	
	public Answer(String value, Question question, User user) {
		super();
		this.value = value;
		this.question = question;
		this.user= user;
	}
	public String getValue() {
		return value;
	}
	public Question getQuestion() {
		return question;
	}
	public User getUser() {
		return user;
	}
	
	
}
