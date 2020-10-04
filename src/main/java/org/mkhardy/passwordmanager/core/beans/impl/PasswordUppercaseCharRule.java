package org.mkhardy.passwordmanager.core.beans.impl;

import org.mkhardy.passwordmanager.core.beans.PasswordRule;
import org.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordUppercaseCharRule extends PasswordRule {
	
	public PasswordUppercaseCharRule(String description, String errorMessage) {
		super(description, errorMessage);
	}

	public boolean isValidPassword(Password password) throws PasswordRuleException {
		
		boolean containsUpper = false;
		
		for(char c:password.getValue().toCharArray()) {
			if(Character.isUpperCase(c)) {
				containsUpper=true;
				break;
			}
		}
		
		if(!containsUpper) {
			throw new PasswordRuleException(this);
		}
		
		return true;
	}

}
