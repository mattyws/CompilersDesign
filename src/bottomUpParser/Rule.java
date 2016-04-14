package bottomUpParser;

import java.util.ArrayList;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class Rule {
	
	int id;
	NonTerminal nonTerminal;
	ArrayList<String> rule = new ArrayList<>();
	
	public Rule(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NonTerminal getNonTerminal() {
		return nonTerminal;
	}
	public void setNonTerminal(NonTerminal nonTerminal) {
		this.nonTerminal = nonTerminal;
	}
	public ArrayList<String> getRule() {
		return rule;
	}
	public void setRule(ArrayList<String> rule) {
		this.rule = rule;
	}
	
	
	@Override
	public String toString() {
		String rule = nonTerminal + " -> ";
		for(String s : this.rule){
			rule += s + " ";
		}
		return rule;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nonTerminal == null) ? 0 : nonTerminal.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
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
		Rule other = (Rule) obj;
		if (id != other.id)
			return false;
		if (nonTerminal == null) {
			if (other.nonTerminal != null)
				return false;
		} else if (!nonTerminal.equals(other.nonTerminal))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		return true;
	}


	public boolean isNullRule() {
		boolean nullRule = true;
		for(String s : rule){
			if(!s.replace(" ", "").isEmpty()){
				nullRule = false;
			}
		}
		return nullRule;
	}
	
	

}
