package bottomUpParser;

import java.util.ArrayList;

import lexer.Token;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class Expression {
	
	Rule rule;
	int position;
	String lookAheadSym;
	
	public String getPositionItem(){
		if(this.position < this.rule.getRule().size())
			return rule.getRule().get(this.position);
		else
			return "";
	}
	
	public String lookAhead(){
		if(this.position+1 < this.rule.getRule().size())
			return rule.getRule().get(this.position+1);
		else
			return "";
	}
	public boolean isNullRule(){
		return this.rule.isNullRule();
	}
	
	public String getLookAheadSym() {
		return lookAheadSym;
	}
	public void setLookAheadSym(String lookAheadSym) {
		this.lookAheadSym = lookAheadSym;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return rule.toString() + " " + lookAheadSym + " " + position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lookAheadSym == null) ? 0 : lookAheadSym.hashCode());
		result = prime * result + position;
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
		Expression other = (Expression) obj;
		if (lookAheadSym == null) {
			if (other.lookAheadSym != null)
				return false;
		} else if (!lookAheadSym.equals(other.lookAheadSym))
			return false;
		if (position != other.position)
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		return true;
	}

	public NonTerminal getNonTerminal() {
		return rule.getNonTerminal();
	}
	
	

}
