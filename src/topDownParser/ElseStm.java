package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenElse;
import lexer.TokenIntNum;
import treeNodes.NStatement;
import treeNodes.statements.NElseStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ElseStm {

	public static NElseStatement parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenElse.class)) {
			Helper.eat(tokenQueue, TokenElse.class);
			NStatement stm = Stm.pase(tokenQueue);
			NElseStatement elseStm = new NElseStatement(stm);
			return elseStm;
		}
		return null;
	}

}
