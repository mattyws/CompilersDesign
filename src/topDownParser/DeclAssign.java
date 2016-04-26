package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenLBrace;
import lexer.TokenRBrace;
import treeNodes.NExp;

public class DeclAssign {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {		
		if (Helper.is(tokenQueue.peek(), TokenAssign.class)) {
			Helper.eat(tokenQueue, TokenAssign.class);			
			if (Helper.is(tokenQueue.peek(), TokenLBrace.class)) {
				Helper.eat(tokenQueue, TokenLBrace.class);
				NExp exp = ArrayExpParam.parse(tokenQueue);
				Helper.eat(tokenQueue, TokenRBrace.class);
				return exp;
			} else {
				NExp exp = Exp.parse(tokenQueue);
				return exp;
			}
		}
		return null;
	}

}
