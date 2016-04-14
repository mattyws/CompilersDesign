package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIf;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import treeNodes.NExp;
import treeNodes.NStatement;
import treeNodes.statements.NElseStatement;
import treeNodes.statements.NIfStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class IfStm {

	public static NIfStatement parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenIf.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		NExp exp = Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		NStatement stm = Stm.pase(tokenQueue);		
		NElseStatement elseStm = ElseStm.parse(tokenQueue);
		NIfStatement ifStm = new NIfStatement(exp, stm, elseStm);
		return ifStm;
	}

}
