package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	public static void main(String[] args) {
		Lexer l = new Lexer();
		l.generateTokens("whil( !(i == 12){ i=i+1;//a random comment\n}");
	}
	
	public void generateTokens(String program) {
		int pos = 0;
		List<String> tokens = new ArrayList<>();
		while(pos < program.length()){
			String value = "";
			while( !isTokenizeSymbol(program.charAt(pos)) ){
				value += program.charAt(pos);
				pos++;
			}
			if(!value.isEmpty())
				tokens.add(value);
			if( isCompareSymbol(program.charAt(pos)) ){
				if(program.charAt(pos+1) == '='){
					tokens.add(program.substring(pos, pos+2));
					pos+=2;
				} else {
					tokens.add(String.valueOf(program.charAt(pos)));
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
					tokens.add(commentValue);
					pos++;
				} 
			} else {
				tokens.add(String.valueOf(program.charAt(pos)));
				pos++;
			}			
		}
		for(String s : tokens){
			System.out.print("<");
			System.out.print(s);
			System.out.print("> ");
		}
	}

	private boolean isCompareSymbol(char c) {
		String symbols = "=<>";
		return symbols.indexOf(c) == -1 ? false : true;
	}

	private boolean isTokenizeSymbol(char c) {
		String symbols = ";(){}=<>!&|+-*/% " + ((char) 10) + ((char) 13) + ((char) 9);
		return symbols.indexOf(c) == -1 ? false : true;
	}

}
