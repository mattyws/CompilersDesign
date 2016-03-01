package program3;

public class TokenWhile extends Token{
	
	public TokenWhile(String program, int pos) {
		this.program = program;
		this.pos = pos;
		this.value = "w";
		setValid(false);
	}
	
	@Override
	public void getToken() {
		q1();
	}

	private void q1() {
		if(program.charAt(pos) == 'h') {
			this.value += program.charAt(pos);
			pos++;
			q2();
		} else {
			
		}
		
	}

	private void q2() {
		if(program.charAt(pos) == 'i') {
			this.value += program.charAt(pos);
			pos++;
			q3();
		}
	}

	private void q3() {
		if(program.charAt(pos) == 'l') {
			this.value += program.charAt(pos);
			pos++;
			q4();
		}
	}

	private void q4() {
		if(program.charAt(pos) == 'e') {
			this.value += program.charAt(pos);
			pos++;
			setValid(true);
			q5();
		}
	}

	private void q5() {
		if(Character.isAlphabetic(program.charAt(pos))) {
			this.value += program.charAt(pos);			
			this.setValid(false);
		}
	}

	@Override
	public void setValid(boolean value) {
		this.valid = value;
	}

	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public Token next() {
		// TODO Auto-generated method stub
		return null;
	}

}
