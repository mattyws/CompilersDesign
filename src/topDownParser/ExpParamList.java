package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ExpParamList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenComma.class);
		Exp.parse(tokenQueue);
	}

}
