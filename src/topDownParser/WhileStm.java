package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIntNum;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenWhile;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class WhileStm {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenWhile.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		Stm.pase(tokenQueue);
	}

}
