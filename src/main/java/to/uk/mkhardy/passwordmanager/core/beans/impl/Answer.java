package to.uk.mkhardy.passwordmanager.core.beans.impl;

public final class Answer {
	private final  byte[] byteValue;
	private final Question question;
	private final User user;
	
	public Answer(String value, Question question, User user) {
		this.byteValue = value.getBytes();
		this.question = question;
		this.user= user;
	}
	public String getStringValue() {
		return new String(byteValue);
	}
	public Question getQuestion() {
		return question;
	}
	public User getUser() {
		return user;
	}
	
	
}
