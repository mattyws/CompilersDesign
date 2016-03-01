package program3;

public class TokenElse extends Token {
	
	private Token t = null;

	public TokenElse(String program, int pos) {
		this.program = program;
		this.pos = pos;
		setValid(false);
	}

	@Override
	public void getToken() {
		q1();
	}

	private void q1() {
		if (program.charAt(pos) == 'l') {
			this.value += program.charAt(pos);
			pos++;
			q2();
		} else {
			pos -= this.value.length();
			this.t = new TokenIdentifier(this.program, this.pos);
		}

	}

	private void q2() {
		if (program.charAt(pos) == 's') {
			this.value += program.charAt(pos);
			pos++;
			q3();
		} else {
			pos -= this.value.length();
			this.t = new TokenIdentifier(this.program, this.pos);
		}
	}

	private void q3() {
		if (program.charAt(pos) == 'e') {
			this.value += program.charAt(pos);
			pos++;
			setValid(true);
			q4();
		} else {
			pos -= this.value.length();
			this.t = new TokenIdentifier(this.program, this.pos);
		}
	}

	private void q4() {
		if (Character.isLetterOrDigit(program.charAt(pos))) {
			//this.value += program.charAt(pos);
			pos -= this.value.length();
			this.t = new TokenIdentifier(this.program, this.pos);
			this.setValid(false);
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
		Token t = null;
		char c = program.charAt(pos);
		switch (c) {
		case 'i':
			t = new TokenIf(this.program, this.pos);
			break;
		case 'e':
			t = new TokenElse(this.program, this.pos);
			break;
		case 'w':
			t = new TokenWhile(this.program, this.pos);
			break;
		case '(':
			t = new TokenLParenthesis(this.program, this.pos);
			break;
		case ')':
			t = new TokenRParenthesis(this.program, this.pos);
			break;
		case '{':
			t = new TokenLBrace(this.program, this.pos);
			break;
		case '}':
			t = new TokenRBrace(this.program, this.pos);
			break;
		case '>':
			t = new TokenCompare(this.program, this.pos);
			break;
		case '<':
			t = new TokenCompare(this.program, this.pos);
			break;
		case '=':
			t = new TokenCompare(this.program, this.pos);
			break;
		case '&' :
			t = new TokenBoolOp(this.program, this.pos);
			break;
		case '|':
			t = new TokenBoolOp(this.program, this.pos);
			break;
		case '+' :
			t = new TokenArithOp(this.program, this.pos);
			break;
		case '-':
			t = new TokenArithOp(this.program, this.pos);
			break;
		case '*' :
			t = new TokenArithOp(this.program, this.pos);
			break;
		case '/':
			t = new TokenArithOp(this.program, this.pos);
			break;
		case '%':
			t = new TokenArithOp(this.program, this.pos);
			break;
		default:
			t = new TokenMisc(this.program, this.pos);
			break;
		}
		if (Character.isDigit(c) && isValid()) {
			t = new TokenFloatNum(this.program, this.pos);
			break;
		} else if(Character.isDigit(c) && !isValid()){
			t = new TokenIdentifier(this.program, this.pos);
		}
		else if (Character.isLetterOrDigit(c)) {
			t = new TokenIdentifier(this.program, this.pos);
			break;
		}
		return null;
	}

}
