package org.mkhardy.passwordmanager.core;

import java.util.List;

import org.mkhardy.passwordmanager.core.beans.impl.Answer;
import org.mkhardy.passwordmanager.core.beans.impl.Password;
import org.mkhardy.passwordmanager.core.beans.impl.Question;
import org.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
	public byte[] hashAnswers(List<Answer> answers);
	public List<Question> getQuestions();
}
