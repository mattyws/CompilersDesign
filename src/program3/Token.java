package program3;

public abstract class Token {
	
	public String program;
	public int pos;
	public String value;
	public List<Token> tokenList;
	//public boolean valid;

	public abstract void getToken();
	//public abstract void isValid(boolean value);
	public abstract void next();
}
