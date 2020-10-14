package to.uk.mkhardy.passwordmanager.core.beans.impl;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;

public final class PasswordLowercaseCharRule extends PasswordRule {
	
	public PasswordLowercaseCharRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
	}

	public boolean isValidPassword(String password) {
		
		boolean containsLower = false;
		
		for(char c:password.toCharArray()) {
			if(Character.isLowerCase(c)) {
				containsLower=true;
				break;
			}
		}
		
		if(!containsLower) {
			return false;
		}
		
		return true;
	}

}
