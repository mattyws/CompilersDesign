package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenRBrace extends Token {

	public TokenRBrace(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
