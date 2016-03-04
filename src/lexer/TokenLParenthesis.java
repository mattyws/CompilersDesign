package lexer;

import java.util.List;

public class TokenLParenthesis extends Token {

	public TokenLParenthesis(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
