package lexer;

import java.util.List;

public class TokenRBrace extends Token {

	public TokenRBrace(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
