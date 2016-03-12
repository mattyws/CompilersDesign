package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenType extends Token{

	public TokenType(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
	
}
