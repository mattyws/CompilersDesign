package topDownParser;

import java.util.Queue;

import lexer.Token;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MultExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		NotExp.parse(tokenQueue);
		NList.parse(tokenQueue);
	}

}
