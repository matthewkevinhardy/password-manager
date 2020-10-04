package mkhardy.uk.to.passwordmanager.core;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import mkhardy.uk.to.passwordmanager.core.beans.PasswordRule;
import mkhardy.uk.to.passwordmanager.core.beans.impl.Question;
import mkhardy.uk.to.passwordmanager.core.impl.PasswordManagerImpl;

public class PasswordManagerBuilder {
	private List<Question> questions;
	private List<PasswordRule> passwordRules;
	
	public PasswordManagerBuilder() {
		this.questions = new LinkedList<Question>();
		this.passwordRules = new LinkedList<PasswordRule>();
	}
	
	public PasswordManagerBuilder addPasswordRule(PasswordRule passwordRule) {
		this.passwordRules.add(passwordRule);
		return this;
	}
	
	public PasswordManagerBuilder addQuestion(Question question) {
		this.questions.add(question);
		return this;
	}
	
	public PasswordManager build() {
		return new PasswordManagerImpl(this.questions, this.passwordRules);
	}
}
