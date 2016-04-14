package bottomUpParser;

import lexer.Token;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class TokenEndProgram extends Token {
	
	public TokenEndProgram() {
	}

	public TokenEndProgram(String string) {
		this.token=string;
	}

	@Override
	public String getToken() {
		return token;
	}

}
