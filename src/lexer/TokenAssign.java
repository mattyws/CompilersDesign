package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenAssign extends Token {

	public TokenAssign(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
	
}
