package to.uk.mkhardy.passwordmanager.core.impl;

import java.util.LinkedList;
import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;

public class PasswordRuleException extends Exception {

	private static final long serialVersionUID = -6443867089202147727L;
	
	private List<PasswordRule> passwordRules = new LinkedList<PasswordRule>();

	public void addBrokenRule(PasswordRule pRule) {
		this.passwordRules.add(pRule);
	}
	
	public List<PasswordRule> getPasswordRules() {
		return passwordRules;
	}
	
}
