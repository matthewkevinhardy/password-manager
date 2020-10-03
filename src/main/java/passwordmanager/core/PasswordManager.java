package passwordmanager.core;

import java.util.List;

import passwordmanager.core.beans.impl.Answer;
import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.beans.impl.Question;
import passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
	public byte[] hashAnswers(List<Answer> answers);
	public List<Question> getQuestions();
}
