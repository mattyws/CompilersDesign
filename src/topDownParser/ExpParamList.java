package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ExpParamList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenComma.class)) {
			Helper.eat(tokenQueue, TokenComma.class);
			NExp exp = Exp.parse(tokenQueue);
			return exp;
		}
		return null;
	}

}
