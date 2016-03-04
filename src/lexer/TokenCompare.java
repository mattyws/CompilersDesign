package lexer;

import java.util.List;

public class TokenCompare extends Token {

	public TokenCompare(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
