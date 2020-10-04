package mkhardy.uk.to.passwordmanager.core.beans.impl;

import mkhardy.uk.to.passwordmanager.core.beans.PasswordRule;
import mkhardy.uk.to.passwordmanager.core.impl.PasswordRuleException;

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
