package to.uk.mkhardy.passwordmanager.core.beans.impl;

public final class Password {
	private final byte[] passwordBytes;

	public Password(String password) {
		this.passwordBytes = password.getBytes();
	}
	
	public byte[] getBytes() {
		return this.passwordBytes;
	}
	
	public String getString() {
		return new String(this.passwordBytes);
	}
}
