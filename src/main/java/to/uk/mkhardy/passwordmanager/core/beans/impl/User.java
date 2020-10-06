package to.uk.mkhardy.passwordmanager.core.beans.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class User {
	private final String userName;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public User(@JsonProperty("userName") String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	
}
