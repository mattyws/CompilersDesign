package lexer;

public class TokenType extends Token{

	public TokenType(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
	
}
