package program3;

public abstract class Token {
	
	public String text;
	public int pos;
	public boolean valid;

	public abstract Token getToken();
	public abstract void isValid(boolean value);
}
