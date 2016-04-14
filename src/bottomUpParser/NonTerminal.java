package bottomUpParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class NonTerminal {
	
	String name;
	Set<String> followSet = new HashSet<>();
	Set<String> firstSet = new HashSet<>();
	boolean hasNullRule = false;
	
	public NonTerminal(String name) {
		this.name=name;
	}
	
	public void addFollowToken(String followToken){
		this.followSet.add(followToken);
	}
	
	public void addFirstToken(String firstToken){
		this.firstSet.add(firstToken);
	}


	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		NonTerminal other = (NonTerminal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getFollowSet() {
		return followSet;
	}

	public void setFollowSet(Set<String> followSet) {
		this.followSet = followSet;
	}

	public Set<String> getFirstSet() {
		return firstSet;
	}

	public void setFirstSet(Set<String> firstSet) {
		this.firstSet = firstSet;
	}

	public boolean hasNullRule() {
		return hasNullRule;
	}

	public void setHasNullRule(boolean hasNullRule) {
		this.hasNullRule = hasNullRule;
	}
	
	

}
