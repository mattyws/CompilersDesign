package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenIntNum extends Token{

	public TokenIntNum(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
