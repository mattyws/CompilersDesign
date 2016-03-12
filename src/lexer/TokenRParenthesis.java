package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenRParenthesis extends Token {

	public TokenRParenthesis(String token) {
		this.token=token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
