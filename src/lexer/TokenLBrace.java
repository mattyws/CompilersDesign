package lexer;

import java.util.List;

public class TokenLBrace extends Token {

	public TokenLBrace(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
