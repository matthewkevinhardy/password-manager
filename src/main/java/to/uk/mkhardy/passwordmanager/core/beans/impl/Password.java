package to.uk.mkhardy.passwordmanager.core.beans.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Password {
	private final String passwordHash;
	private final User user;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Password(@JsonProperty("passwordHash") String passwordHash, @JsonProperty("user") User user) {
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
