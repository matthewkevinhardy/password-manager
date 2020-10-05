package to.uk.mkhardy.passwordmanager.core;

import org.junit.BeforeClass;
import org.junit.Test;

import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.PasswordManagerBuilder;
import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLengthRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;
import to.uk.mkhardy.passwordmanager.core.impl.CryptoUtils;
import to.uk.mkhardy.passwordmanager.core.impl.EncryptorAesGcm;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.crypto.SecretKey;

public class PasswordManagerTest {

	private static PasswordManager PASS_MANAGER;
	private static ResourceBundle MESSAGES_EN = ResourceBundle.getBundle("to.uk.mkhardy.passwordmanager.core.i18n.MessageBundle",
			Locale.ENGLISH);
	
	private static User USER = new User("myUserName");

	private static List<Answer> ANSWERS = new LinkedList<Answer>();
	
	private static Password PASSWORD = new Password("P@SSw0RD");
	
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

		for (Question question : PASS_MANAGER.getQuestions()) {
			Answer answer = new Answer("answer", question, USER);
			ANSWERS.add(answer);
		}
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
	public void testEncrypt() throws Exception {
		String cText = PASS_MANAGER.encrypt("pText".getBytes(), PASSWORD);
		String pText = PASS_MANAGER.decrypt(cText, PASSWORD);
		assertEquals(pText,"pText");
	}
	
	@Test
	public void testQuestions() throws Exception {
		
		String pText = "testing123";
		
		String cText = PASS_MANAGER.encrypt(pText.getBytes(), ANSWERS, USER);
		
		String decryptedText = PASS_MANAGER.decrypt(cText, ANSWERS, USER);
		
		assertEquals(pText, decryptedText);
	}
	
	@Test
	public void testRandomKey() throws Exception {
		
		SecretKey secretKey = CryptoUtils.getAESKey(256);
		
		String cKeyWithPassword = PASS_MANAGER.encrypt(secretKey.getEncoded(), PASSWORD);
		String cKeyWithAnswers = PASS_MANAGER.encrypt(secretKey.getEncoded(), ANSWERS, USER);
		
		
	}
}
