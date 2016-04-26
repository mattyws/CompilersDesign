package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;
import treeNodes.NExp;
import treeNodes.exp.NMinus;
import treeNodes.exp.NPlus;

public class MiList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "+")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NPlus plus = new NPlus();
			NExp exp = MinusExp.parse(tokenQueue);
			NExp exp2 = MiList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				plus.setR(exp2);
			} else {
				plus.setR(exp);
			}
			return plus;
		}
		return null;
	}
}
