package lexer;

public class TokenReturn extends Token {
	
	public TokenReturn(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {		
		return token;
	}

}
