package lexer;

public class TokenRBracket extends Token {

	public TokenRBracket(String token) {
		this.token=token;
	}
	
	@Override
	public String getToken() {
		return token;
	}

}
