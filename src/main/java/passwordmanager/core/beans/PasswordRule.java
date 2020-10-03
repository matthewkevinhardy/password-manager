package passwordmanager.core.beans;

import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.impl.PasswordRuleException;

public abstract class PasswordRule {
	protected final String description;
	protected final String errorMessage;
	
	public PasswordRule(String description, String errorMessage) {
		this.description = description;
		this.errorMessage = errorMessage;
	}
	
	public String getDescription() {
		return this.description;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public abstract boolean isValidPassword(Password password) throws PasswordRuleException;
}
