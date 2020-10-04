package mkhardy.uk.to.passwordmanager.core.beans.impl;

import mkhardy.uk.to.passwordmanager.core.beans.PasswordRule;
import mkhardy.uk.to.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordLengthRule extends PasswordRule {

	private final int minChars;
	
	public PasswordLengthRule(int minChars, String description, String errorMessage) {
		super(description,errorMessage);
		this.minChars=minChars;
	}

	public boolean isValidPassword(Password password) throws PasswordRuleException {
		if(password!=null && password.getValue().length()<minChars) {
			throw new PasswordRuleException(this);
		}
		return true;
	}

}
