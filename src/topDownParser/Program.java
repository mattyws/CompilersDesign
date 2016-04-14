package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenMain;
import lexer.TokenType;
import treeNodes.NFunctionDecl;
import treeNodes.NMainFunc;
import treeNodes.NProgram;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Program {

	public static NProgram parse(Queue<Token> tokenQueue) throws ParserException{
		List<NFunctionDecl> functionList = new ArrayList<>();
		while(!Helper.is(tokenQueue.peek(), TokenMain.class)){
			NFunctionDecl function = FuncDecl.parse(tokenQueue);
			if(function != null)
				functionList.add(function);
		}
		NMainFunc main = MainFunc.parse(tokenQueue);
		NProgram program = new NProgram(functionList, main);
		return program;
	}
}
