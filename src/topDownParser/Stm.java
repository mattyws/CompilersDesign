package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenLBrace;
import lexer.TokenRBrace;
import lexer.TokenType;
import lexer.TokenWhile;

public class Stm {

	public static void pase(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenLBrace.class)) {
			Helper.eat(tokenQueue, TokenLBrace.class);
			while (Helper.is(tokenQueue.peek(), TokenLBrace.class) || Helper.is(tokenQueue.peek(), TokenType.class)
					|| Helper.is(tokenQueue.peek(), TokenIdentifier.class)
					|| Helper.is(tokenQueue.peek(), TokenWhile.class) || Helper.is(tokenQueue.peek(), TokenIf.class)) {
				Stm.pase(tokenQueue);
			}
			Helper.eat(tokenQueue, TokenRBrace.class);
		} else if (Helper.is(tokenQueue.peek(), TokenType.class)) {
			VarDecl.parse(tokenQueue);
		} else if (Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			IdStm.parse(tokenQueue);
		} else if (Helper.is(tokenQueue.peek(), TokenWhile.class)) {
			WhileStm.parse(tokenQueue);
		} else if (Helper.is(tokenQueue.peek(), TokenIf.class)) {
			IfStm.parse(tokenQueue);
		} else {
			Helper.raiseError("Expecting \"(\", \"int\", \"boolean\", \"float\","
					+ " \"char\", \"string\", a identifier, \"while\", \"if\", got " + tokenQueue.peek().getToken());
		}
	}

}