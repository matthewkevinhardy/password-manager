package to.uk.mkhardy.passwordmanager.core.beans.impl;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordLengthRule extends PasswordRule {

	private final int minChars;
	
	public PasswordLengthRule(int minChars, String description, String errorMessage) {
		super(description,errorMessage);
		this.minChars=minChars;
	}

	public boolean isValidPassword(String password) throws PasswordRuleException {
		if(password!=null && password.length()<minChars) {
			throw new PasswordRuleException(this);
		}
		return true;
	}

}
