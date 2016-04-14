package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenLBrace;
import lexer.TokenLParenthesis;
import lexer.TokenMain;
import lexer.TokenRBrace;
import lexer.TokenRParenthesis;
import lexer.TokenType;
import lexer.TokenWhile;
import treeNodes.NMainFunc;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class MainFunc {

	public static NMainFunc parse(Queue<Token> tokenQueue) throws ParserException {
		TokenMain id = (TokenMain) Helper.eat(tokenQueue, TokenMain.class);
		NIdentifier nameId = new NIdentifier(id.getToken());
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		Helper.eat(tokenQueue, TokenLBrace.class);
		List<NStatement> statements = new ArrayList<>();
		while (Helper.is(tokenQueue.peek(), TokenLBrace.class) || Helper.is(tokenQueue.peek(), TokenType.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class) || Helper.is(tokenQueue.peek(), TokenWhile.class)
				|| Helper.is(tokenQueue.peek(), TokenIf.class)) {
			NStatement statement = Stm.pase(tokenQueue);
			statements.add(statement);
		}
		Helper.eat(tokenQueue, TokenRBrace.class);
		NMainFunc mainFunc = new NMainFunc(nameId, statements);
		return mainFunc;
	}

}
