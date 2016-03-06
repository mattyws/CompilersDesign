package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenBoolOp;

public class CList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
			if (Helper.is(tokenQueue.peek(), TokenBoolOp.class, "|")
					|| Helper.is(tokenQueue.peek(), TokenBoolOp.class, "&")) {
				Helper.eat(tokenQueue, TokenBoolOp.class);
				CompareExp.parse(tokenQueue);
				CList.parse(tokenQueue);
			}
	}

}
