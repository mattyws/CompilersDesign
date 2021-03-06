package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenCompare;
import treeNodes.NExp;
import treeNodes.exp.NEqual;
import treeNodes.exp.NGreaterEqual;
import treeNodes.exp.NGreaterThan;
import treeNodes.exp.NLessEqual;
import treeNodes.exp.NLessThan;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class PList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenCompare.class)) {
			TokenCompare compare = (TokenCompare) Helper.eat(tokenQueue, TokenCompare.class);
			NExp exp1 = PlusExp.parse(tokenQueue);
			NExp exp2 = PList.parse(tokenQueue);
			NExp exp;
			if(exp2 != null) {
				exp2.setL(exp1);
				exp = exp2;
			} else {
				exp = exp1;
			}
			if(compare.getToken().equals("==")){
				NEqual eq = new NEqual(exp);
				return eq;
			} else if(compare.getToken().equals("<")){
				NLessThan lt = new NLessThan(exp);
				return lt;
			} else if(compare.getToken().equals(">")){
				NGreaterThan gt = new NGreaterThan(exp);
				return gt;
			} else if(compare.getToken().equals("<=")){
				NLessEqual le = new NLessEqual(exp);
				return le;
			} else {
				NGreaterEqual ge = new NGreaterEqual(exp);
				return ge;
			}
		}
		return null;
	}

}
