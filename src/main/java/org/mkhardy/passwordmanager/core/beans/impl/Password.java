package org.mkhardy.passwordmanager.core.beans.impl;

public final class Password {
	private final String password;

	public Password(String password) {
		this.password = password;
	}
	
	public String getValue() {
		return this.password;
	}
}
