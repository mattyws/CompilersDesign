package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenEndCommand;
import lexer.TokenIdentifier;
import lexer.TokenType;
import treeNodes.NType;
import treeNodes.NVariable;
import treeNodes.exp.NIdentifier;
import treeNodes.statements.NAssign;
import treeNodes.statements.NVarDeclStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class VarDecl {

	public static NVarDeclStatement parse(Queue<Token> tokenQueue) throws ParserException {
		NType type = Type.parse(tokenQueue);
		NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());		
		NAssign assign = new NAssign(id, DeclAssign.parse(tokenQueue));
		NVariable var = new NVariable(type, id, assign);
		List<NVariable> variables = new ArrayList<>();
		variables.add(var);
		while (Helper.is(tokenQueue.peek(), TokenComma.class)) {
			var = VarDeclList.parse(tokenQueue, type);
		}
		Helper.eat(tokenQueue, TokenEndCommand.class);
		NVarDeclStatement varDeclStm = new NVarDeclStatement(variables);
		return varDeclStm;
	}

}
