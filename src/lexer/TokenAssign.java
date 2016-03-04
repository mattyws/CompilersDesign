package lexer;

import java.util.List;

public class TokenAssign extends Token {

	public TokenAssign(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
	
}
