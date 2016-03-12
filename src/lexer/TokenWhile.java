package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenWhile extends Token{

	public TokenWhile(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
