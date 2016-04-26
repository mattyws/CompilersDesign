package treeNodes.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import treeNodes.NExp;

public class NArrayAttribution extends NExp{
	
	public Map<NIntNum, NExp> values;
	private int automaticKey;
	
	public NArrayAttribution() {
		values = new HashMap<>();
		automaticKey = 0;
	}
	
	public void addValue(NExp value){
		values.put(new NIntNum(automaticKey), value);
		automaticKey++;
	}
	
}
