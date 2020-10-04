package to.uk.mkhardy.passwordmanager.core;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordManagerImpl;

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
