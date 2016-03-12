package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenIdentifier;
import lexer.TokenType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class VarDeclList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenComma.class);
		Helper.eat(tokenQueue, TokenIdentifier.class);
		DeclAssign.parse(tokenQueue);
	}

}
