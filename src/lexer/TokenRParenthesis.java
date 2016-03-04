package lexer;

import java.util.List;

public class TokenRParenthesis extends Token {

	public TokenRParenthesis(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
