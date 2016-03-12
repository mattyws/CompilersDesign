package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenBoolOp extends Token {

	public TokenBoolOp(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}	
}
