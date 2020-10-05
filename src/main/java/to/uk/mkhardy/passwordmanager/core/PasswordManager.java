package to.uk.mkhardy.passwordmanager.core;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
	//public SecretKey getAnswerKey(List<Answer> answers,User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
	public List<Question> getQuestions();
	
	public String encrypt(byte[] pText, List<Answer> answers, User user) throws Exception;

	public String encrypt(byte[] pText, Password password) throws Exception;

	public String decrypt(String cText, Password password) throws Exception;
	
	public String decrypt(String pText, List<Answer> answers, User user) throws Exception;
}
