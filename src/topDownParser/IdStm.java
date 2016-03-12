package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class IdStm {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenIdentifier.class);
		IdDef.parse(tokenQueue);
	}

}
