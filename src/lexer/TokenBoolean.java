package lexer;

public class TokenBoolean extends Token{

	public TokenBoolean(String token) {
		this.token=token;
	}
	
	@Override
	public String getToken() {
		return token;
	}

}
