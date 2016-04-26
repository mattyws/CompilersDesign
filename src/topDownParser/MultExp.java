package topDownParser;

import java.util.Queue;

import lexer.Token;
import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MultExp {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		NExp exp = DivisionExp.parse(tokenQueue);
		NExp exp2 = NList.parse(tokenQueue);
		if(exp2 != null) {
			exp2.setL(exp);
			return exp2;
		} else {
			return exp;
		}
	}

}
