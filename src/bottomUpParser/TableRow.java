package bottomUpParser;

import java.util.HashMap;
import java.util.Map;

import lexer.Token;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class TableRow {
	
	State state;
	Map<String, String> cols = new HashMap<>();
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Map<String, String> getCols() {
		return cols;
	}
	public void setCols(Map<String, String> cols) {
		this.cols = cols;
	}
	public void addCol(String token, String operation){
		cols.put(token, operation);
	}
	public String get(String token){
		return cols.get(token);
	}
	
	@Override
	public String toString() {
		String message = state.id + " -- ";
		for(String s : cols.keySet()){
			message += s+" : "+cols.get(s)+" ";
		}
		return message;
	}
}
