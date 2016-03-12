package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenElse;
import lexer.TokenIntNum;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ElseStm {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenElse.class)) {
			Helper.eat(tokenQueue, TokenElse.class);
			Stm.pase(tokenQueue);
		}
	}

}
