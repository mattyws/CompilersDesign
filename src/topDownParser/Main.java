package topDownParser;

import java.util.List;

import lexer.Lexer;
import lexer.Token;

public class Main {

	public static void main(String[] args) {
		String teste = "int teste(int g){ float p; p = 0; }"
				+ " boolean teste(string g){ float p; p = 0; }"
				+ "void main(){ int i, p; i = 0; p = 2; i=i*p; }";
		Lexer l = new Lexer();
		List<Token> tokens = l.generateTokens(teste);		
		Parser parser = new Parser();
		try {
			parser.parse(tokens);
			System.out.println("Program accepted");
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
