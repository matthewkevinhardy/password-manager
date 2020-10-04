package org.mkhardy.passwordmanager.core.beans.impl;

import org.mkhardy.passwordmanager.core.beans.PasswordRule;
import org.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordLowercaseCharRule extends PasswordRule {
	
	public PasswordLowercaseCharRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
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
