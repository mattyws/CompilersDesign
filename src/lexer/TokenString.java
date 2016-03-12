package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenString extends Token {
	
	public TokenString(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
