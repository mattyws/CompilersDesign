package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenBoolean;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;

public class Factor {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenIntNum.class)) {
			Helper.eat(tokenQueue, TokenIntNum.class);
		} else if (Helper.is(tokenQueue.peek(), TokenFloatNum.class)) {
			Helper.eat(tokenQueue, TokenFloatNum.class);
		} else if (Helper.is(tokenQueue.peek(), TokenBoolean.class)) {
			Helper.eat(tokenQueue, TokenBoolean.class);
		} else if (Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			Helper.eat(tokenQueue, TokenIdentifier.class);
		}
	}

}
