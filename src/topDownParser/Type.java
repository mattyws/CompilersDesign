package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenLBracket;
import lexer.TokenRBracket;
import lexer.TokenType;
import treeNodes.NType;
import treeNodes.types.NArrayBooleanType;
import treeNodes.types.NArrayCharType;
import treeNodes.types.NArrayFloatType;
import treeNodes.types.NArrayIntType;
import treeNodes.types.NArrayStringType;
import treeNodes.types.NBooleanTrueType;
import treeNodes.types.NCharType;
import treeNodes.types.NFloatType;
import treeNodes.types.NIntType;
import treeNodes.types.NStringType;
import treeNodes.types.NVoidType;

public class Type {
	
	public static NType parse(Queue<Token> tokenQueue) throws ParserException{
		TokenType token = (TokenType) Helper.eat(tokenQueue, TokenType.class);
		if(token == null) {
			throw new ParserException("The token is not an type.");
		}
		NType type = typeDiscover(token);
		if(Helper.is(tokenQueue.peek(), TokenLBracket.class)){
			Helper.eat(tokenQueue, TokenLBracket.class);
			Helper.eat(tokenQueue, TokenRBracket.class);
			type = arrayTypeDiscover(type);
		}
		return type;
	}
	
	private static NType typeDiscover(TokenType token){
		if(token.getToken().equalsIgnoreCase("int")){
			return new NIntType();
		}
		if(token.getToken().equalsIgnoreCase("float") ){
			return new NFloatType();
		}
		if(token.getToken().equalsIgnoreCase("boolean")){
			return new NBooleanTrueType();
		}
		if(token.getToken().equalsIgnoreCase("char")){
			return new NCharType();
		}
		if(token.getToken().equalsIgnoreCase("string")) {
			return new NStringType();
		}
		if(token.getToken().equalsIgnoreCase("void")){
			return new NVoidType();
		}
		return null;
	}
	
	private static NType arrayTypeDiscover(NType type) throws ParserException{
		if(type.getClass() == NIntType.class){
			return new NArrayIntType();
		}
		if(type.getClass() == NFloatType.class){
			return new NArrayFloatType();
		}
		if(type.getClass() == NBooleanTrueType.class){
			return new NArrayBooleanType();
		}
		if(type.getClass() == NCharType.class){
			return new NArrayCharType();
		}
		if(type.getClass() == NStringType.class){
			return new NArrayStringType();
		}
		if(type.getClass() == NVoidType.class){
			throw new ParserException("Void type can't be an array.");
		}
		return null;
	}

}
