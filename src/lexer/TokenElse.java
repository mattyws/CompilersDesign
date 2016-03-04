package lexer;

public class TokenElse extends Token {

	public TokenElse(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
