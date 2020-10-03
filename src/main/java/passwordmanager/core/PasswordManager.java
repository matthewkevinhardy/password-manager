package passwordmanager.core;

import passwordmanager.core.beans.impl.Password;
import passwordmanager.core.impl.PasswordRuleException;

public interface PasswordManager {
	public boolean isValidPassword(Password password) throws PasswordRuleException;
	public byte[] hashPassword(Password password);
}
