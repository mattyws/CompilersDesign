import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenReturn;
import topDownParser.Parser;
import topDownParser.ParserException;
import treeNodes.NFunctionDecl;
import treeNodes.NProgram;
import treeNodes.SemanticException;
import visitors.BuildTable;
import visitors.DeadCodeReturnSimplifier;
import visitors.DeadFunctionVisitor;
import visitors.ExpressionSimplifierVisitor;
import visitors.PrintVisitor;
import visitors.TypeChecker;

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

			 br = new BufferedReader(new FileReader("./optimizationTest3.lmv"));
//			br = new BufferedReader(new FileReader("./optimizationTest2.lmv"));
//			br = new BufferedReader(new FileReader("./optimizationTest.lmv"));

			while ((sCurrentLine = br.readLine()) != null) {
				program += sCurrentLine + "\n";
			}
			System.out.println(program);
			Lexer l = new Lexer();
			List<Token> tokens = l.generateTokens(program);			
			Parser parser = new Parser();
			try {
				NProgram prog = parser.parse(tokens);
				BuildTable build = new BuildTable();
				build.visit(prog);
				build.printTables();
				TypeChecker checker = new TypeChecker();				
				checker.visit(prog);
				DeadFunctionVisitor funcVisit = new DeadFunctionVisitor();
				funcVisit.visit(prog);
				DeadCodeReturnSimplifier returnSimp = new DeadCodeReturnSimplifier();
				returnSimp.visit(prog);
				ExpressionSimplifierVisitor expSimp = new ExpressionSimplifierVisitor();
				expSimp.visit(prog);
				PrintVisitor print = new PrintVisitor();
				System.out.println(print.visit(prog));
				System.out.println("Program successfully checked.");
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(SemanticException e) {
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
