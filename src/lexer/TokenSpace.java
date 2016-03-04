package lexer;

public class TokenSpace extends Token{

	public TokenSpace(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
