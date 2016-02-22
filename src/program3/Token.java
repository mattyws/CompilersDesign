package program3;

public abstract class Token {
	
	public String text;
	public int pos;
	//public boolean valid;

	public abstract void getToken();
	//public abstract void isValid(boolean value);
	public abstract void next();
}
