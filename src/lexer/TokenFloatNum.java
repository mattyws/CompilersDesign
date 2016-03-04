package lexer;

public class TokenFloatNum extends Token{

	public TokenFloatNum(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
