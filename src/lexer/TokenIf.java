package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenIf extends Token {

	public TokenIf(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}
}
