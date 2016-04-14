package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIntNum;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenWhile;
import treeNodes.NExp;
import treeNodes.NStatement;
import treeNodes.statements.NWhileStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class WhileStm {

	public static NWhileStatement parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenWhile.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		NExp exp = Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		NStatement stm = Stm.pase(tokenQueue);
		NWhileStatement whileStm = new NWhileStatement(exp, stm);
		return whileStm;
	}

}
