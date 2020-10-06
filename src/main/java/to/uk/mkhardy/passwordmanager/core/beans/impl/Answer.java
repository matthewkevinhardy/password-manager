package to.uk.mkhardy.passwordmanager.core.beans.impl;

public final class Answer {
	//private final  byte[] byteValue;
	private final String hashValue;
	private final Question question;
	private final User user;
	
	public Answer(String hashValue, Question question, User user) {
		//this.byteValue = value.getBytes();
		this.question = question;
		this.user= user;
		this.hashValue=hashValue;
	}
	public String getHashValue() {
		return hashValue;
	}
	public Question getQuestion() {
		return question;
	}
	public User getUser() {
		return user;
	}
	
	
}
