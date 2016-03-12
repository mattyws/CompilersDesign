package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenCompare extends Token {

	public TokenCompare(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
