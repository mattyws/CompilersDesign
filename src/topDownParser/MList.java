package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
			if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "/")
					|| Helper.is(tokenQueue.peek(), TokenArithOp.class, "%")) {
				Helper.eat(tokenQueue, TokenArithOp.class);
				MultExp.parse(tokenQueue);
				MList.parse(tokenQueue);
			}
	}

}
