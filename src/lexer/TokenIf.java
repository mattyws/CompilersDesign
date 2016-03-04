package lexer;

import java.util.List;

public class TokenIf extends Token {

	public TokenIf(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
