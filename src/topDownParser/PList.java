package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenCompare;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class PList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenCompare.class)) {
			Helper.eat(tokenQueue, TokenCompare.class);
			PlusMinusExp.parse(tokenQueue);
			PList.parse(tokenQueue);
		}
	}

}