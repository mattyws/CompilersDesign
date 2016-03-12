package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenFloatNum extends Token{

	public TokenFloatNum(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
