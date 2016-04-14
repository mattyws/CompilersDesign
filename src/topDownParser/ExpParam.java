package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenBoolean;
import lexer.TokenChar;
import lexer.TokenComma;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import lexer.TokenString;
import treeNodes.NExp;
import treeNodes.NExpParam;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ExpParam {

	public static NExpParam parse(Queue<Token> tokenQueue) throws ParserException {
		if(Helper.is(tokenQueue.peek(), TokenIntNum.class) || Helper.is(tokenQueue.peek(), TokenFloatNum.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class) || Helper.is(tokenQueue.peek(), TokenBoolean.class)
				|| Helper.is(tokenQueue.peek(), TokenString.class) || Helper.is(tokenQueue.peek(), TokenChar.class)){
			List<NExp> exps = new ArrayList<>();
			NExp exp = Exp.parse(tokenQueue);
			exps.add(exp);
			while(Helper.is(tokenQueue.peek(), TokenComma.class)){
				exp = ExpParamList.parse(tokenQueue);
				if(exp != null)
					exps.add(exp);
			}
			NExpParam param = new NExpParam(exps);
			return param;
		}
		return null;
	}

}
