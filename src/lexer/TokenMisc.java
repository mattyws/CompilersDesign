package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenMisc extends Token{

	public TokenMisc(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
