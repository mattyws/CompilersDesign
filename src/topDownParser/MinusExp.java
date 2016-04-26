package topDownParser;

import java.util.Queue;

import lexer.Token;
import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MinusExp {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		NExp exp = MultExp.parse(tokenQueue);
		NExp exp2 = DList.parse(tokenQueue);
		if(exp2 != null) {
			exp2.setL(exp);
			return exp2;
		} else {
			return exp;
		}
	}

}
