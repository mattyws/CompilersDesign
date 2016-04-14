package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenIdentifier;
import lexer.TokenType;
import treeNodes.NExp;
import treeNodes.NType;
import treeNodes.NVariable;
import treeNodes.exp.NIdentifier;
import treeNodes.statements.NAssign;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class VarDeclList {

	public static NVariable parse(Queue<Token> tokenQueue, NType type) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenComma.class)) {
			Helper.eat(tokenQueue, TokenComma.class);
			NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());
			NExp exp = DeclAssign.parse(tokenQueue);			
			NAssign assign = new NAssign(id, exp);
			NVariable var = new NVariable(type, id, assign);
			return var;
		}
		return null;
	}

}
