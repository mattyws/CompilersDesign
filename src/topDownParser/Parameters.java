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

public class Parameters {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenType.class)) {
			Helper.eat(tokenQueue, TokenType.class);
			Helper.eat(tokenQueue, TokenIdentifier.class);
			while (Helper.is(tokenQueue.peek(), TokenComma.class)) {
				ParametersList.parse(tokenQueue);
			}
		}
	}

}
