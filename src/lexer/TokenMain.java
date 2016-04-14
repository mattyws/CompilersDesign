package lexer;

public class TokenMain extends Token {
	
	public TokenMain(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
