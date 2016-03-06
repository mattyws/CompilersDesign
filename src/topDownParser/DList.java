package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;

public class DList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "+")
				|| Helper.is(tokenQueue.peek(), TokenArithOp.class, "-")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			DivisionExp.parse(tokenQueue);
			DList.parse(tokenQueue);
		}
	}

}
