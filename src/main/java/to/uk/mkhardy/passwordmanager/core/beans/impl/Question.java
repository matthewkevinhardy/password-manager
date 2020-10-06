package to.uk.mkhardy.passwordmanager.core.beans.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Question {
	private final String questionId;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Question(@JsonProperty("questionId") String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}
	
	
}
