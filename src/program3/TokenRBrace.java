package program3;

import java.util.List;

public class TokenRBrace extends Token {	
	
	public TokenRBrace(String program, int pos) {
		this.program = program;
		this.pos = pos;
		this.value = "{";
	}

	@Override
	public void getToken() {
		this.setValid(true);
	}

	@Override
	public Token next() {
		Token t = null;
		if(program.charAt(pos)=='i'){
			
		}
		return t;
		
	}

	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public void setValid(boolean value) {
		this.valid = value;
	}
}
