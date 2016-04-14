package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;
import treeNodes.NExp;
import treeNodes.exp.NDiv;
import treeNodes.exp.NMod;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MList {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "/")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NDiv div = new NDiv();
			NExp exp = MultExp.parse(tokenQueue);
			NExp exp2 = MList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				div.setR(exp2);
			} else {
				div.setR(exp);
			}
			return div;
		}
		if (Helper.is(tokenQueue.peek(), TokenArithOp.class, "%")) {
			Helper.eat(tokenQueue, TokenArithOp.class);
			NMod mod = new NMod();
			NExp exp = MultExp.parse(tokenQueue);
			NExp exp2 = MList.parse(tokenQueue);
			if(exp2 != null){
				exp2.setL(exp);
				mod.setR(exp2);
			} else {
				mod.setR(exp);
			}
			return mod;
		}
		return null;
	}

}
