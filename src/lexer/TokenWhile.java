package lexer;

public class TokenWhile extends Token{

	public TokenWhile(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
