package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenElse;
import lexer.TokenIntNum;

public class ElseStm {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenElse.class)) {
			Helper.eat(tokenQueue, TokenElse.class);
			Stm.pase(tokenQueue);
		}
	}

}
