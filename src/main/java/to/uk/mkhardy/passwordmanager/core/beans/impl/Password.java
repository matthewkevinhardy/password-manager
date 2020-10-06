package to.uk.mkhardy.passwordmanager.core.beans.impl;

public final class Password {
	private final String passwordHash;
	private final User user;

	public Password(String passwordHash,User user) {
		this.passwordHash = passwordHash;
		this.user = user;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public User getUser() {
		return user;
	}
	
	
}
