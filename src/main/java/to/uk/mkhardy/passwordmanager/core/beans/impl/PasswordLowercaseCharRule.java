package to.uk.mkhardy.passwordmanager.core.beans.impl;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordLowercaseCharRule extends PasswordRule {
	
	public PasswordLowercaseCharRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
	}

	public boolean isValidPassword(String password) throws PasswordRuleException {
		
		boolean containsLower = false;
		
		for(char c:password.toCharArray()) {
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
