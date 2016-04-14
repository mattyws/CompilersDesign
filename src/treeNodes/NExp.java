package treeNodes;

public class NExp extends Node{

	public NExp l, r;
	
	public NExp(){}
	public NExp(NExp r){
		this.r=r;
	}

	public NExp(NExp l, NExp r) {
		this.l = l;
		this.r = r;
	}

	public NExp getL() {
		return l;
	}

	public void setL(NExp l) {
		this.l = l;
	}

	public NExp getR() {
		return r;
	}

	public void setR(NExp r) {
		this.r = r;
	}
	
}
