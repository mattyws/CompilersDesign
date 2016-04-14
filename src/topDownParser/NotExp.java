package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenBoolOp;
import lexer.TokenBoolean;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import treeNodes.NExp;
import treeNodes.exp.NNot;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class NotExp {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenBoolOp.class, "!")) {
			Helper.eat(tokenQueue, TokenBoolOp.class);
			NNot not = new NNot();
			NExp exp = Exp.parse(tokenQueue);
			not.setL(exp);
			return not;
		} else if (Helper.is(tokenQueue.peek(), TokenIntNum.class) || Helper.is(tokenQueue.peek(), TokenFloatNum.class)
				|| Helper.is(tokenQueue.peek(), TokenBoolean.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			NExp exp = Factor.parse(tokenQueue);
			return exp;
		}
		return null;
	}

}
