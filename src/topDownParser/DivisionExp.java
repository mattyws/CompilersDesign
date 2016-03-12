package topDownParser;

import java.util.Queue;

import lexer.Token;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class DivisionExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		MultExp.parse(tokenQueue);
		MList.parse(tokenQueue);
	}

}
