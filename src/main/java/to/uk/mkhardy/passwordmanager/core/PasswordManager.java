package to.uk.mkhardy.passwordmanager.core;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
	public byte[] hashAnswers(List<Answer> answers);
	public List<Question> getQuestions();
}
