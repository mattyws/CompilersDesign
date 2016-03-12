package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenEndCommand;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class IdDef {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenAssign.class)) {
			Helper.eat(tokenQueue, TokenAssign.class);
			Exp.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenEndCommand.class);
		} else if (Helper.is(tokenQueue.peek(), TokenLParenthesis.class)) {
			Helper.eat(tokenQueue, TokenLParenthesis.class);
			ExpParam.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenRParenthesis.class);
			Helper.eat(tokenQueue, TokenEndCommand.class);
		} else {
			Helper.raiseError("Expecting a \"=\" or \"(\" got " + tokenQueue.peek().getToken());
		}
	}

}
