package topDownParser;

import java.util.Queue;

import lexer.Token;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class CompareExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		PlusMinusExp.parse(tokenQueue);
		PList.parse(tokenQueue);
	}

}
