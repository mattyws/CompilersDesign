package lexer;

public class TokenEndCommand extends Token{
	
	public TokenEndCommand(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
