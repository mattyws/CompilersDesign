package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;
import treeNodes.NExp;
import treeNodes.exp.NMult;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class NList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "*")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NMult mult = new NMult();
			NExp exp = NotExp.parse(tokenQueue);
			NExp exp2 = NList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				mult.setR(exp2);
			} else {
				mult.setR(exp);
			}
			return mult;
		}
		return null;
	}

}
