package program3;

public class TokenIf extends Token {	
	
	public TokenIf(String text, int pos) {
		this.text = text;
		this.pos = pos;
	}

	@Override
	public Token getToken() {
		Token t = null;
		switch (text.charAt(pos)) {
		case 'f':			
			t = new TokenIf(text, pos++);
//			t.isValid(true);
			break;
		default:
			if(sub == "if") {
				
			}
			break;
		}
		return t;
	}

	@Override
	public void isValid(boolean value) {
		this.valid = value;
	}
}
