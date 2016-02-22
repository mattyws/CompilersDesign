package program3;

import java.util.List;

public abstract class Token {
	
	public String program;
	public int pos;
	public String value;
	public List<Token> tokenList;
	public boolean valid;

	public abstract void getToken();
	public abstract void setValid(boolean value);
	public abstract boolean isValid();
	public abstract Token next();
}
