package to.uk.mkhardy.passwordmanager.core.beans.impl;

import java.io.Serializable;

public final class DataKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final byte[] dataKey;
	private final User user;
	
	public DataKey(byte[] dataKey, User user) {
		super();
		this.dataKey = dataKey;
		this.user = user;
	}
	
	public byte[] getDataKey() {
		return dataKey;
	}
	public User getUser() {
		return user;
	}
	
	
}
