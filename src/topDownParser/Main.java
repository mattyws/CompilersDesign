package topDownParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import lexer.Lexer;
import lexer.Token;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		String program = "";

		try {

			String sCurrentLine;

//			br = new BufferedReader(new FileReader("./primes.lmv"));
//			br = new BufferedReader(new FileReader("./fibo.lmv"));
			br = new BufferedReader(new FileReader("./digitSum.lmv"));
//			br = new BufferedReader(new FileReader("./digitCount.lmv"));
//			br = new BufferedReader(new FileReader("./perfectDigit.lmv"));

			while ((sCurrentLine = br.readLine()) != null) {
				program += sCurrentLine + "\n";
			}
			System.out.println(program);
			Lexer l = new Lexer();
			List<Token> tokens = l.generateTokens(program);
			Parser parser = new Parser();
			try {
				parser.parse(tokens);
				System.out.println("Program accepted");
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}		
		String teste = "int teste(int g){ float p; p = 0; }" + " boolean teste(string g){ float p; p = 0; }"
				+ "void main(){ int i, p; i = 0; p = 2; i=i*p; }";

	}
}
