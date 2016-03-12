package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class NList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "*")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NotExp.parse(tokenQueue);
			NList.parse(tokenQueue);
		}
	}

}
