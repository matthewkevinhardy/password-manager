package to.uk.mkhardy.passwordmanager.core.beans.impl;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;

public final class PasswordUppercaseCharRule extends PasswordRule {
	
	public PasswordUppercaseCharRule(String description, String errorMessage) {
		super(description, errorMessage);
	}

	public boolean isValidPassword(String password) {
		
		boolean containsUpper = false;
		
		for(char c:password.toCharArray()) {
			if(Character.isUpperCase(c)) {
				containsUpper=true;
				break;
			}
		}
		
		if(!containsUpper) {
			return false;
		}
		
		return true;
	}

}
