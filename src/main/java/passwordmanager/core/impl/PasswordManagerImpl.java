package passwordmanager.core.impl;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import passwordmanager.core.PasswordManager;
import passwordmanager.core.beans.PasswordRule;
import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.beans.impl.Question;

public class PasswordManagerImpl implements PasswordManager {
	private List<Question> questions;
	private List<PasswordRule> passwordRules;
	private ResourceBundle resourceBundle;
	
	public PasswordManagerImpl(ResourceBundle resourceBundle, List<Question> questions, List<PasswordRule> passwordRules) {
		this.questions = questions;
		this.passwordRules = passwordRules;
		this.resourceBundle = resourceBundle;
	}

	public boolean isValidPassword(Password password) throws PasswordRuleException {
		for(PasswordRule passwordRule:passwordRules) {
			passwordRule.isValidPassword(password);
		}
		return true;
	}

	@Override
	public byte[] hashPassword(Password password) {
		return DigestUtils.sha256(password.getValue());
	}
	
	
}
