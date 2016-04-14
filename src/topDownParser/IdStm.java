package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;
import treeNodes.statements.NIdStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class IdStm {

	public static NStatement parse(Queue<Token> tokenQueue) throws ParserException {
		NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());
		NStatement stm = IdDef.parse(tokenQueue, id);
		return stm;
	}

}
