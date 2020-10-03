package passwordmanager.core.beans.impl;

import passwordmanager.core.beans.PasswordRule;
import passwordmanager.core.impl.PasswordRuleException;

public final class PasswordLowercaseCharRule extends PasswordRule {
	
	public PasswordLowercaseCharRule(String description, String errorMessage) {
		super(description, errorMessage);
	}

	public boolean isValidPassword(Password password) throws PasswordRuleException {
		
		boolean containsLower = false;
		
		for(char c:password.getValue().toCharArray()) {
			if(Character.isLowerCase(c)) {
				containsLower=true;
				break;
			}
		}
		
		if(!containsLower) {
			throw new PasswordRuleException(this);
		}
		
		return true;
	}

}
