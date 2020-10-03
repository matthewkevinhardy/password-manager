package passwordmanager;

import org.junit.BeforeClass;
import org.junit.Test;

import passwordmanager.core.PasswordManager;
import passwordmanager.core.PasswordManagerBuilder;
import passwordmanager.core.beans.PasswordRule;
import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.beans.impl.PasswordLengthRule;
import passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import passwordmanager.core.beans.impl.Question;
import passwordmanager.core.impl.PasswordRuleException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;
import java.util.ResourceBundle;

public class PasswordManagerTest {

	private static PasswordManager PASS_MANAGER;

	@BeforeClass
	public static void setup() {
		ResourceBundle messageBundle = ResourceBundle.getBundle("passwordmanager.core.i18n.MessageBundle", Locale.ENGLISH);

		PasswordRule lengthRule = new PasswordLengthRule(8, "passwordLengthRule.description", 
				"passwordLengthRule.errorMessage");
		PasswordRule uppercaseCharRule = new PasswordUppercaseCharRule("passwordUppercaseCharRule.description",
				"passwordUppercaseCharRule.errorMessage");
		PasswordRule lowercaseCharRule = new PasswordLowercaseCharRule("passwordLowercaseCharRule.description",
				"passwordLowercaseCharRule.errorMessage");
		
		Question question1 = new Question("question1");
		Question question2 = new Question("question2");
		Question question3 = new Question("question3");
		
		PASS_MANAGER = new PasswordManagerBuilder(messageBundle).addPasswordRule(uppercaseCharRule)
				.addPasswordRule(lowercaseCharRule)
				.addPasswordRule(lengthRule)
				.addQuestion(question1).addQuestion(question2).addQuestion(question3).build();
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

	}

	@Test
	public void testNoLowerCase() {

		Password password = new Password("A2345678");

		PasswordRuleException exception = assertThrows(PasswordRuleException.class, () -> {
			PASS_MANAGER.isValidPassword(password);
		});

		assertEquals("passwordLowercaseCharRule.errorMessage", exception.getMessage());

	}
}
