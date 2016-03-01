package program3;

public class TokenSpace extends Token{
	
	public TokenSpace(String program, int pos) {
		this.program = program;
		this.pos = pos;		
	}

	@Override
	public void getToken() {
		pos++;
		setValid(true);
	}

	@Override
	public void setValid(boolean value) {		
		this.valid = value;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public Token next() {
		// TODO Auto-generated method stub
		return null;
	}

}
