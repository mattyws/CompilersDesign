8package program3;

public class TokenComment extends Token{
	
	public TokenComment(String program, int pos) {
		this.program = program;
		this.pos = pos;
		setValid(false);
		this.value = "/";
	}

	@Override
	public void getToken() {
		q1();
	}

	private void q1() {
		if(program.charAt(pos) == '/'){
			this.value += program.charAt(pos);
			pos++;
			setValid(true);
			q2();
		}
	}

	private void q2() {
		while(program.charAt(pos) != '\n'){
			this.value += program.charAt(pos);
			pos++;
		}		
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
