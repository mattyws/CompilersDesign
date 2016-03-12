package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenComma extends Token{

	public TokenComma(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}

}
