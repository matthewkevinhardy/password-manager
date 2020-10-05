package to.uk.mkhardy.passwordmanager.core.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.commons.codec.digest.DigestUtils;

import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public class PasswordManagerImpl implements PasswordManager {
	private List<Question> questions;
	private List<PasswordRule> passwordRules;

	public PasswordManagerImpl(List<Question> questions, List<PasswordRule> passwordRules) {
		this.questions = questions;
		this.passwordRules = passwordRules;
	}

	public boolean isValidPassword(Password password) throws PasswordRuleException {
		for (PasswordRule passwordRule : passwordRules) {
			passwordRule.isValidPassword(password);
		}
		return true;
	}

	@Override
	public byte[] hashPassword(Password password) {
		return DigestUtils.sha256(password.getBytes());
	}

	public String encrypt(byte[] pText, List<Answer> answers, User user) throws Exception {
		StringBuilder answerBuilder = new StringBuilder(user.getUserName());
		for (Answer answer : answers) {
			answerBuilder.append(answer.getStringValue());
		}

		return encrypt(pText, answerBuilder.toString());
	}
	
	public String decrypt(String cText, List<Answer> answers, User user) throws Exception {
		StringBuilder answerBuilder = new StringBuilder(user.getUserName());
		for (Answer answer : answers) {
			answerBuilder.append(answer.getStringValue());
		}

		return decrypt(cText, answerBuilder.toString());
	}
	
	public String encrypt(byte[] pText, String password) throws Exception {
		return EncryptorAesGcmPassword.encrypt(pText, password);
	}

	public String decrypt(String cText, String password) throws Exception {
		return EncryptorAesGcmPassword.decrypt(cText, password);
	}

	@Override
	public List<Question> getQuestions() {
		return this.questions;
	}

	@Override
	public String encrypt(byte[] pText, Password password) throws Exception {
		return encrypt(pText, password.getString());
	}

	@Override
	public String decrypt(String cText, Password password) throws Exception {
		return decrypt(cText,password.getString());
	}

	
}
