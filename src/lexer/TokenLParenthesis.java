package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenLParenthesis extends Token {

	public TokenLParenthesis(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
