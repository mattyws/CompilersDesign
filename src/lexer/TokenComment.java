package lexer;

public class TokenComment extends Token{

	public TokenComment(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
