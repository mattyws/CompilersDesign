package topDownParser;

import java.util.ArrayList;
import java.util.List;
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

public class Parameters {

	public static List<NParameter> parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenType.class)) {
			NType type = Type.parse(tokenQueue);
			NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());
			NParameter parameter = new NParameter(type, id);
			List<NParameter> parameters = new ArrayList<>();
			parameters.add(parameter);
			while (Helper.is(tokenQueue.peek(), TokenComma.class)) {
				parameter = ParametersList.parse(tokenQueue);
				if(parameter != null)
					parameters.add(parameter);
			}
			return parameters;
		}
		return null;
	}

}
