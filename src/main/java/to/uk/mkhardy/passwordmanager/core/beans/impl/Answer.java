package to.uk.mkhardy.passwordmanager.core.beans.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Answer {
	// private final byte[] byteValue;
	private final String hashValue;
	private final Question question;
	private final User user;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Answer(@JsonProperty("hashValue") String hashValue, @JsonProperty("question") Question question,
			@JsonProperty("user") User user) {
		// this.byteValue = value.getBytes();
		this.question = question;
		this.user = user;
		this.hashValue = hashValue;
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
