package to.uk.mkhardy.passwordmanager.core.impl;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
	
	@Override
	public boolean isValidPassword(String password) throws PasswordRuleException {
		for (PasswordRule passwordRule : passwordRules) {
			passwordRule.isValidPassword(password);
		}
		return true;
	}

	public String encrypt(byte[] pText, List<String> answers, User user) throws Exception {
		StringBuilder answerBuilder = new StringBuilder(user.getUserName());
		for (String answer : answers) {
			answerBuilder.append(answer);
		}

		return encrypt(pText, answerBuilder.toString());
	}

	public String decrypt(String cText, List<String> answers, User user) throws Exception {
		StringBuilder answerBuilder = new StringBuilder(user.getUserName());
		for (String answer : answers) {
			answerBuilder.append(answer);
		}

		return decrypt(cText, answerBuilder.toString());
	}
	
	@Override
	public String encrypt(byte[] pText, String password) throws Exception {
		return EncryptorAesGcmPassword.encrypt(pText, password);
	}
	
	@Override
	public String decrypt(String cText, String password) throws Exception {
		return EncryptorAesGcmPassword.decrypt(cText, password);
	}

	@Override
	public List<Question> getQuestions() {
		return this.questions;
	}

	@Override
	public Answer getAnswer(String value,User user,Question question) {
		StringBuilder hashBuilder = new StringBuilder(user.getUserName())
				.append(question.getQuestionId()).append(value);
		byte[] answerHash = DigestUtils.sha256(hashBuilder.toString().getBytes());
		String answerHashBase64 = Base64.encodeBase64String(answerHash);
		Answer answer = new Answer(answerHashBase64, question, user);
		return answer;
	}

	@Override
	public Password getPassword(String value,User user) {
		StringBuilder hashBuilder = new StringBuilder(user.getUserName())
				.append(value);
		byte[] passHash = DigestUtils.sha256(hashBuilder.toString().getBytes());
		String passHashBase64 = Base64.encodeBase64String(passHash);
		Password password = new Password(passHashBase64,user);
		return password;
	}

	@Override
	public boolean isValidAnswer(String pText, Answer answer) {
		StringBuilder hashBuilder = new StringBuilder(answer.getUser().getUserName())
				.append(answer.getQuestion().getQuestionId()).append(pText);
		byte[] answerHash = DigestUtils.sha256(hashBuilder.toString().getBytes());
		String answerHashBase64 = Base64.encodeBase64String(answerHash);
		
		return answerHashBase64.equals(answer.getHashValue());
	}
	
}
