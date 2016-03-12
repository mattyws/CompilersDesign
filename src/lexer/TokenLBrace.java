package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenLBrace extends Token {

	public TokenLBrace(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
