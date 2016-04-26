package topDownParser;

import java.util.Queue;

import lexer.Token;
import treeNodes.NExp;

public class PlusExp {
	
	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		NExp exp = MinusExp.parse(tokenQueue);
		NExp exp2 = MiList.parse(tokenQueue);
		if(exp2 != null) {
			exp2.setL(exp);
			return exp2;
		} else {
			return exp;
		}
	}

}
