package lexer;

public class TokenIntNum extends Token{

	public TokenIntNum(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
