package program3;

import java.util.List;

public class TokenIf extends Token {
		
	public TokenIf(String program, int pos) {
		this.program = program;
		this.pos = pos;
		this.value = "i";
	}

	@Override
	public void getToken() {
		q1();
	}

	private void q1() {		
		if(program.charAt(pos) == 'f'){
			this.setValid(true);
		}
		this.value += program.charAt(pos);
		pos++;		
		q2();
	}
	
	private void q2() {		
		if(Character.isAlphabetic(program.charAt(pos))) {
			this.value += program.charAt(pos);			
			this.setValid(false);
		}
	}

	@Override
	public Token next() {
		Token t = null;
		if(Character.isAlphabetic(program.charAt(pos))){
			
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
