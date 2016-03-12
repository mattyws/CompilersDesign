package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenBoolean extends Token{

	public TokenBoolean(String token) {
		this.token=token;
	}
	
	@Override
	public String getToken() {
		return token;
	}

}
