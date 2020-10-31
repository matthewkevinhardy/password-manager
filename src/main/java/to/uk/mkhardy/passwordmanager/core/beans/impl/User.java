package to.uk.mkhardy.passwordmanager.core.beans.impl;

import java.io.Serializable;

public final class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final String userName;
	
	public User(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	
}
