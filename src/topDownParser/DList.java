package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;
import treeNodes.NExp;
import treeNodes.exp.NMinus;
import treeNodes.exp.NPlus;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class DList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "+")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NPlus plus = new NPlus();
			NExp exp = DivisionExp.parse(tokenQueue);
			NExp exp2 = DList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				plus.setR(exp2);
			} else {
				plus.setR(exp);
			}
			return plus;
		}
		if(Helper.is(tokenQueue.peek(), TokenArithOp.class, "-")){
			Helper.eat(tokenQueue, TokenArithOp.class);
			NMinus minus = new NMinus();
			NExp exp = DivisionExp.parse(tokenQueue);
			NExp exp2 = DList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				minus.setR(exp2);
			} else {
				minus.setR(exp);
			}
			return minus;
		}
		return null;
	}

}
