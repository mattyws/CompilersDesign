package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenBoolOp;
import lexer.TokenBoolean;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class NotExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
			if (Helper.is(tokenQueue.peek(), TokenBoolOp.class, "!")) {
				Helper.eat(tokenQueue, TokenBoolOp.class);
				NotExp.parse(tokenQueue);
			} else if (Helper.is(tokenQueue.peek(), TokenIntNum.class)
					|| Helper.is(tokenQueue.peek(), TokenFloatNum.class)
					|| Helper.is(tokenQueue.peek(), TokenBoolean.class)
					|| Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
				FunctionExp.parse(tokenQueue);
			}
	}

}
