package mkhardy.uk.to.passwordmanager.core;

import java.util.List;

import mkhardy.uk.to.passwordmanager.core.beans.impl.Answer;
import mkhardy.uk.to.passwordmanager.core.beans.impl.Password;
import mkhardy.uk.to.passwordmanager.core.beans.impl.Question;
import mkhardy.uk.to.passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
	public byte[] hashAnswers(List<Answer> answers);
	public List<Question> getQuestions();
}
