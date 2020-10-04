package org.mkhardy.passwordmanager.core;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mkhardy.passwordmanager.core.PasswordManager;
import org.mkhardy.passwordmanager.core.PasswordManagerBuilder;
import org.mkhardy.passwordmanager.core.beans.PasswordRule;
import org.mkhardy.passwordmanager.core.beans.impl.Answer;
import org.mkhardy.passwordmanager.core.beans.impl.Password;
import org.mkhardy.passwordmanager.core.beans.impl.PasswordLengthRule;
import org.mkhardy.passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import org.mkhardy.passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import org.mkhardy.passwordmanager.core.beans.impl.Question;
import org.mkhardy.passwordmanager.core.beans.impl.User;
import org.mkhardy.passwordmanager.core.impl.PasswordRuleException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PasswordManagerTest {

	private static PasswordManager PASS_MANAGER;
	private static ResourceBundle MESSAGES_EN = ResourceBundle.getBundle("passwordmanager.core.i18n.MessageBundle",
			Locale.ENGLISH);

	@BeforeClass
	public static void setup() {
		PasswordRule lengthRule = new PasswordLengthRule(8, "passwordLengthRule.description",
				"passwordLengthRule.errorMessage");
		PasswordRule uppercaseCharRule = new PasswordUppercaseCharRule("passwordUppercaseCharRule.description",
				"passwordUppercaseCharRule.errorMessage");
		PasswordRule lowercaseCharRule = new PasswordLowercaseCharRule("passwordLowercaseCharRule.description",
				"passwordLowercaseCharRule.errorMessage");

		Question question1 = new Question("question1");
		Question question2 = new Question("question2");
		Question question3 = new Question("question3");

		PASS_MANAGER = new PasswordManagerBuilder().addPasswordRule(uppercaseCharRule)
				.addPasswordRule(lowercaseCharRule).addPasswordRule(lengthRule).addQuestion(question1)
				.addQuestion(question2).addQuestion(question3).build();
	}

	@Test
	public void testWrongLength() {

		Password password = new Password("a23F567");

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword(password);
		});

		assertEquals("passwordLengthRule.errorMessage", exception.getMessage());

	}

	@Test
	public void testNoUpperCase() {

		Password password = new Password("a2345678");

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword(password);
		});

		assertEquals("passwordUppercaseCharRule.errorMessage", exception.getMessage());

		String errorMessage = MESSAGES_EN.getString(exception.getPasswordRule().getErrorMessageKey());
		assertEquals("Min 1 uppercase char", errorMessage);

	}

	@Test
	public void testNoLowerCase() {

		Password password = new Password("A2345678");

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword(password);
		});

		assertEquals("passwordLowercaseCharRule.errorMessage", exception.getPasswordRule().getErrorMessageKey());

	}

	@Test
	public void testQuestions() {

		List<Answer> answers = new LinkedList<Answer>();

		User user = new User("myUserName");

		for (Question question : PASS_MANAGER.getQuestions()) {
			Answer answer = new Answer("answer", question, user);
			answers.add(answer);
		}

	}
}
