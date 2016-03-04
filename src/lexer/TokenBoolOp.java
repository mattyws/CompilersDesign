package lexer;

import java.util.List;

public class TokenBoolOp extends Token {

	public TokenBoolOp(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
