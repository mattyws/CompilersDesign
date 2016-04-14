package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenIdentifier;
import lexer.TokenType;
import treeNodes.NParameter;
import treeNodes.NType;
import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ParametersList {

	public static NParameter parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenComma.class)) {
			Helper.eat(tokenQueue, TokenComma.class);
			NType type = Type.parse(tokenQueue);
			NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());
			NParameter parameter = new NParameter(type, id);
			return parameter;
		}
		return null;
	}

}
