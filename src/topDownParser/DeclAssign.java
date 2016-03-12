package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;

public class DeclAssign {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {		
		if(Helper.is(tokenQueue.peek(), TokenAssign.class)){
			Helper.eat(tokenQueue, TokenAssign.class);
			Exp.parse(tokenQueue);
		}
	}

}
