package lexer;

public class TokenComma extends Token{

	public TokenComma(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
