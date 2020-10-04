package passwordmanager.core.impl;

import passwordmanager.core.beans.PasswordRule;

public class PasswordRuleException extends Exception {

	private static final long serialVersionUID = -6443867089202147727L;
	
	private PasswordRule passwordRule;

	public PasswordRuleException(PasswordRule passwordRule) {
		this.passwordRule = passwordRule;
	}
	
	public PasswordRule getPasswordRule() {
		return passwordRule;
	}

	@Override
	public String getMessage() {
		return this.passwordRule.getErrorMessageKey();
	}
	
}
