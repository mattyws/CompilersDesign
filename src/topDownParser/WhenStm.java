package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenIdentifier;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenWhen;
import treeNodes.NExp;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;
import treeNodes.statements.NWhenStatement;

public class WhenStm {

	public static NWhenStatement parse(Queue<Token> tokenQueue) throws ParserException {
		NWhenStatement whenStatement;
		Helper.eat(tokenQueue, TokenWhen.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).token);
		Helper.eat(tokenQueue, TokenComma.class);
		NExp cond = Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		NStatement body = Stm.pase(tokenQueue);
		whenStatement = new NWhenStatement(id, cond, body);
		return whenStatement;
	}

}
