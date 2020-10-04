package passwordmanager.core.beans;

import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.impl.PasswordRuleException;

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
	
	public abstract boolean isValidPassword(Password password) throws PasswordRuleException;
}
