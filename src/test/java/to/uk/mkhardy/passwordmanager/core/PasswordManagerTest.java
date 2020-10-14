package to.uk.mkhardy.passwordmanager.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.crypto.SecretKey;

import org.junit.BeforeClass;
import org.junit.Test;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLengthRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;
import to.uk.mkhardy.passwordmanager.core.impl.CryptoUtils;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public class PasswordManagerTest {

	private static PasswordManager PASS_MANAGER;
	
	private static User USER = new User("myUserName");

	private static List<Answer> ANSWERS = new LinkedList<Answer>();
	private static List<String> PLAIN_TEXT_ANSWERS = new LinkedList<String>();

	private static String PLAIN_TEXT_PASS = "P@SSw0RD";

	private static Password PASSWORD;

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
		Question question4 = new Question("question4");
		Question question5 = new Question("question5");
		Question question6 = new Question("question6");
		Question question7 = new Question("question7");
		Question question8 = new Question("question8");
		Question question9 = new Question("question9");
		Question question10 = new Question("question10");

		PASS_MANAGER = new PasswordManagerBuilder().addPasswordRule(uppercaseCharRule)
				.addPasswordRule(lowercaseCharRule).addPasswordRule(lengthRule).addQuestion(question1)
				.addQuestion(question2).addQuestion(question3).addQuestion(question4).addQuestion(question5)
				.addQuestion(question6).addQuestion(question7).addQuestion(question8).addQuestion(question9)
				.addQuestion(question10).build();

		PASSWORD = PASS_MANAGER.getPassword(PLAIN_TEXT_PASS, USER);

		for (final ListIterator<Question> it = PASS_MANAGER.getQuestions().listIterator(); it.hasNext();) {
			Question q = it.next();
			ANSWERS.add(PASS_MANAGER.getAnswer("answer" + it.previousIndex(), USER, q));
			PLAIN_TEXT_ANSWERS.add("answer" + it.previousIndex());
		}
	}

	@Test
	public void testWrongLength() {

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword("a23F567");
		});

		//assertEquals("passwordLengthRule.errorMessage", exception.getMessage());
		assertTrue(exception.getPasswordRules().stream()
				.anyMatch(item -> "passwordLengthRule.errorMessage".equals(item.getErrorMessageKey())));
	}

	@Test
	public void testNoUpperCase() {

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword("a2345678");
		});

		assertTrue(exception.getPasswordRules().stream()
				.anyMatch(item -> "passwordUppercaseCharRule.errorMessage".equals(item.getErrorMessageKey())));

	}

	@Test
	public void testNoLowerCase() {

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword("A2345678");
		});

		assertTrue(exception.getPasswordRules().stream()
				.anyMatch(item -> "passwordLowercaseCharRule.errorMessage".equals(item.getErrorMessageKey())));

	}

	@Test
	public void testEncrypt() throws Exception {
		String cText = PASS_MANAGER.encrypt("pText".getBytes(), PLAIN_TEXT_PASS);
		String pText = PASS_MANAGER.decrypt(cText, PLAIN_TEXT_PASS);
		assertEquals(pText, "pText");
	}

	@Test
	public void testQuestions() throws Exception {

		String pText = "testing123";

		String cText = PASS_MANAGER.encrypt(pText.getBytes(), PLAIN_TEXT_ANSWERS, USER);

		String decryptedText = PASS_MANAGER.decrypt(cText, PLAIN_TEXT_ANSWERS, USER);

		assertEquals(pText, decryptedText);
	}

	@Test
	public void testRandomKey() throws Exception {

		SecretKey secretKey = CryptoUtils.getAESKey(256);

		String cKeyWithPassword = PASS_MANAGER.encrypt(secretKey.getEncoded(), PLAIN_TEXT_PASS);
		String cKeyWithAnswers = PASS_MANAGER.encrypt(secretKey.getEncoded(), PLAIN_TEXT_ANSWERS, USER);

		String pKeyWithPassword = PASS_MANAGER.decrypt(cKeyWithPassword, PLAIN_TEXT_PASS);
		String pKeyWithAnswers = PASS_MANAGER.decrypt(cKeyWithAnswers, PLAIN_TEXT_ANSWERS, USER);

		assertEquals(pKeyWithPassword, pKeyWithAnswers);
	}

	@Test
	public void testHashAnswers() {

		for (final ListIterator<Answer> it = ANSWERS.listIterator(); it.hasNext();) {
			Answer answer = it.next();
			String plainTxtAnswer = PLAIN_TEXT_ANSWERS.get(it.previousIndex());

			assertTrue(PASS_MANAGER.isCorrectAnswer(plainTxtAnswer, answer));
			assertFalse(PASS_MANAGER.isCorrectAnswer("wrongAnswer", answer));
		}
	}

	@Test
	public void testPassword() {

		assertTrue(PASS_MANAGER.isCorrectPassword(PLAIN_TEXT_PASS, PASSWORD));
		assertFalse(PASS_MANAGER.isCorrectPassword("wrongPassword", PASSWORD));
	}

//	@Test
//	public void testDataKey() throws Exception {
//		String genKey = PASS_MANAGER.generateDataKey(PLAIN_TEXT_PASS, USER);
//		String dataKey = PASS_MANAGER.extractDataKey(genKey, PLAIN_TEXT_PASS, USER);
//	}
}
