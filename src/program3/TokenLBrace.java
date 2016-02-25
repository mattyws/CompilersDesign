package program3;

import java.util.List;

public class TokenLBrace extends Token {	
	
	public TokenLBrace(String program, int pos) {
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
		char c = program.charAt(pos);
		switch(c) {
			case 'i':
				t = new TokenIf(this.program, this.pos);
				break;
			case 'e':
				t = new TokenElse(this.program, this.pos);
				break;
			case 'w':
				t =  new TokenWhile(this.program, this.pos);
				break;
			case Character.isDigit(c):
				t = new TokenFloatNum(this.program, this.pos);
				break;
			case Character.isAlphabetic(c):
				t = new TokenIdentifier(this.program, this.pos);
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
			case '>' || '<' || '=':
				t = new TokenCompare(this.program, this.pos);
				break;
			case '&' || '|':
				t = new TokenBoolOp(this.program, this.pos);
				break;
			case '+' || '-' || '*' || '/' || '%':
				t = new TokenArithOp(this.program, this.pos);
				break;
			default:
				t = new TokenMisc(this.program, this.pos);
				break;														
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
