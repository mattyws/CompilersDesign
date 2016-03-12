package lexer;

import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class TokenArithOp extends Token {

	public TokenArithOp(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;		
	}	
		
}
