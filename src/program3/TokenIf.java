package program3;

public class TokenIf extends Token {	
	
	public TokenIf(String text, int pos) {
		this.text = text;
		this.pos = pos;
	}

	@Override
	public Token getToken() {
		
	}

	@Override
	public void isValid(boolean value) {
		this.valid = value;
	}
}
