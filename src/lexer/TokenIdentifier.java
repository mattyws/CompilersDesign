package lexer;

public class TokenIdentifier extends Token{

	public TokenIdentifier(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
