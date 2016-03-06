package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	public static void main(String[] args) {
		Lexer l = new Lexer();
		l.generateTokens("while( !(i == 12){ i=i+1;//a random comment\n}");
	}
	
	public List<Token> generateTokens(String program) {
		int pos = 0;
		List<Token> tokens = new ArrayList<>();
		while(pos < program.length()){
			String value = "";
			while( !isTokenizeSymbol(program.charAt(pos)) ){
				value += program.charAt(pos);
				pos++;
			}
			if(!value.isEmpty())
				tokens.add(Classifier.classify(value));
			if( isCompareSymbol(program.charAt(pos)) ){
				if(program.charAt(pos+1) == '='){
					tokens.add(Classifier.classify(program.substring(pos, pos+2)));
					pos+=2;
				} else {
					tokens.add(Classifier.classify(String.valueOf(program.charAt(pos))));
					pos++;
				}
			} else if(program.charAt(pos) == '/'){
				// could it be an comment				
				if(program.charAt(pos+1) == '/'){
					String commentValue = "";
					while(program.charAt(pos) != ((char) 10) && program.charAt(pos) != ((char) 13)){
						commentValue += program.charAt(pos);
						pos++;
					}
					tokens.add(Classifier.classify(commentValue));
					pos++;
				} 
			} else {
				tokens.add(Classifier.classify(String.valueOf(program.charAt(pos))));
				pos++;
			}			
		}
		return tokens;
	}

	private boolean isCompareSymbol(char c) {
		String symbols = "=<>";
		return symbols.indexOf(c) == -1 ? false : true;
	}

	private boolean isTokenizeSymbol(char c) {
		String symbols = ",;(){}=<>!&|+-*/% " + ((char) 10) + ((char) 13) + ((char) 9);
		return symbols.indexOf(c) == -1 ? false : true;
	}

}
