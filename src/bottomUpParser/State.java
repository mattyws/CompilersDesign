package bottomUpParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class State {

	int id;
	ArrayList<Expression> expressions = new ArrayList<>();
	Map<String, State> transictions = new HashMap<>(); 
	
	public void addExpression(Expression c){
		this.expressions.add(c);
	}

	public ArrayList<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(ArrayList<Expression> cells) {
		this.expressions = cells;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, State> getTransictions() {
		return transictions;
	}

	public void setTransictions(Map<String, State> transictions) {
		this.transictions = transictions;
	}
	
	public void addState(String key, State value) {
		transictions.put(key, value);
	}	

	public boolean containsExpressions(Expression c) {
		return expressions.contains(c);
	}
	
	@Override
	public String toString() {
		String message = id + "\n";
		for(Expression e : this.getExpressions()){
			message += e.toString() + "\n";
		}
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expressions == null) ? 0 : expressions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (expressions == null) {
			if (other.expressions != null)
				return false;
		} else if (!expressions.containsAll(other.expressions))
			return false;
		return true;
	}


	
	
}
