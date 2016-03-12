package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenComment extends Token{

	public TokenComment(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
