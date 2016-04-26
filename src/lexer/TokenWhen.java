package lexer;

public class TokenWhen extends Token {
	
	public TokenWhen(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
