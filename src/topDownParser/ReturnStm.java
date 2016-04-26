package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenEndCommand;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenReturn;
import treeNodes.NExp;
import treeNodes.statements.NReturnStatement;

public class ReturnStm {

	public static NReturnStatement parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenReturn.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		NExp exp = Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		Helper.eat(tokenQueue, TokenEndCommand.class);
		NReturnStatement rStm = new NReturnStatement(exp);
		return rStm;
	}

}
