package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import treeNodes.NExp;
import treeNodes.exp.NArrayAttribution;

public class ArrayExpParam {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		NArrayAttribution att = new NArrayAttribution();
		NExp exp = Exp.parse(tokenQueue);
		att.addValue(exp);
		while(Helper.is(tokenQueue.peek(), TokenComma.class)){
			exp = ArrayExpList.parse(tokenQueue);
			att.addValue(exp);
		}
		return att;
	}
}
