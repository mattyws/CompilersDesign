package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import treeNodes.NExp;

public class DeclAssign {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {		
		if(Helper.is(tokenQueue.peek(), TokenAssign.class)){			
			Helper.eat(tokenQueue, TokenAssign.class);
			NExp exp = Exp.parse(tokenQueue);
			return exp;
		}
		return null;
	}

}
