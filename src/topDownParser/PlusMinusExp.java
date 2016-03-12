package topDownParser;

import java.util.Queue;

import lexer.Token;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class PlusMinusExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		DivisionExp.parse(tokenQueue);
		DList.parse(tokenQueue);
	}

}
