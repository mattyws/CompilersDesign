package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import treeNodes.NExp;
import treeNodes.exp.NArrayAttribution;

public class ArrayExpList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if(Helper.is(tokenQueue.peek(), TokenComma.class)){
			Helper.eat(tokenQueue, TokenComma.class);
			NExp exp = Exp.parse(tokenQueue);
			return exp;
		}
		return null;
	}

}
