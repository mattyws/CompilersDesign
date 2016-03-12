package lexer;

public class TokenLBracket extends Token{
	
	public TokenLBracket(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
	
}
