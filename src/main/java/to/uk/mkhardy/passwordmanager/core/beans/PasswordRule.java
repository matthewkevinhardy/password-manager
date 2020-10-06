package to.uk.mkhardy.passwordmanager.core.beans;

import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public abstract class PasswordRule {
	protected final String descriptionKey;
	protected final String errorMessageKey;
	
	public PasswordRule(String descriptionKey, String errorMessageKey) {
		this.descriptionKey = descriptionKey;
		this.errorMessageKey = errorMessageKey;
	}
	
	public String getDescriptionKey() {
		return this.descriptionKey;
	}

	public String getErrorMessageKey() {
		return this.errorMessageKey;
	}
	
	public abstract boolean isValidPassword(String password) throws PasswordRuleException;
}
