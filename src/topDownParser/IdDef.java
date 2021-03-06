package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenEndCommand;
import lexer.TokenLBrace;
import lexer.TokenLBracket;
import lexer.TokenLParenthesis;
import lexer.TokenRBrace;
import lexer.TokenRParenthesis;
import treeNodes.NExp;
import treeNodes.NExpParam;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;
import treeNodes.statements.NAssign;
import treeNodes.statements.NMethodCall;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class IdDef {

	public static NStatement parse(Queue<Token> tokenQueue, NIdentifier id) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenAssign.class)) {
			Helper.eat(tokenQueue, TokenAssign.class);
			if (Helper.is(tokenQueue.peek(), TokenLBrace.class)) {
				Helper.eat(tokenQueue, TokenLBrace.class);
				NExp exp = ArrayExpParam.parse(tokenQueue);
				Helper.eat(tokenQueue, TokenRBrace.class);
				NAssign assign = new NAssign(id, exp);
				Helper.eat(tokenQueue, TokenEndCommand.class);
				return assign;
			} else {
				NExp exp = Exp.parse(tokenQueue);
				NAssign assign = new NAssign(id, exp);
				Helper.eat(tokenQueue, TokenEndCommand.class);
				return assign;
			}
		} else if (Helper.is(tokenQueue.peek(), TokenLParenthesis.class)) {
			Helper.eat(tokenQueue, TokenLParenthesis.class);
			NExpParam param = ExpParam.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenRParenthesis.class);
			Helper.eat(tokenQueue, TokenEndCommand.class);
			NMethodCall methodCall = new NMethodCall(id, param);
			return methodCall;
		} else {
			Helper.raiseError("Expecting a \"=\" or \"(\" got " + tokenQueue.peek().getToken());
		}
		return null;
	}

}
