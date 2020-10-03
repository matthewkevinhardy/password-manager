package passwordmanager.core.beans.impl;

import passwordmanager.core.beans.PasswordRule;
import passwordmanager.core.impl.PasswordRuleException;

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
