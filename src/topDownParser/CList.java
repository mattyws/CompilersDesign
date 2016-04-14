package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenBoolOp;
import treeNodes.NExp;
import treeNodes.NType;
import treeNodes.exp.NAnd;
import treeNodes.exp.NOr;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class CList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenBoolOp.class, "|")) {			
			Helper.eat(tokenQueue, TokenBoolOp.class);
			NOr or = new NOr();
			NExp exp = CompareExp.parse(tokenQueue);
			NExp exp2 = CList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				or.setR(exp2);
			} else {
				or.setR(exp);
			}
			return or;
		}
		if(Helper.is(tokenQueue.peek(), TokenBoolOp.class, "&")){
			Helper.eat(tokenQueue, TokenBoolOp.class);
			NAnd and = new NAnd();
			NExp exp = CompareExp.parse(tokenQueue);
			NExp exp2 = CList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				and.setR(exp2);
			} else {
				and.setR(exp);
			}
			return and;
		}
		return null;
	}

}
