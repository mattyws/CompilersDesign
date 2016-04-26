package lexer;

public class TokenKeyValue extends Token {
	
	public TokenKeyValue(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
