package bergCompiler;

public class Simplify implements Visitor {

	@Override
	public void visit(Program n) {
	}

	@Override
	public void visit(MainClass n) {
	}

	@Override
	public void visit(ClassDeclList<ClassDecl> n) {
	}

	@Override
	public void visit(ClassDecl n) {
	}

	@Override
	public void visit(ClassDeclSimple n) {
	}

	@Override
	public void visit(ClassDeclExtends n) {
	}

	@Override
	public void visit(MethodDecl n) {
	}

	@Override
	public String visit(Exp n) {
		return null;
	}

	@Override
	public String visit(And n) {
		return null;
	}

	@Override
	public String visit(LessThan n) {
		return null;
	}

	@Override
	public String visit(Plus n) {
		String value = n.left.toString() + " + ";
		value += n.right.toString();
		return value;
	}

	@Override
	public String visit(Times n) {
		return null;
	}

	@Override
	public String visit(IdentifierExp n) {
		return null;
	}

	@Override
	public String visit(Product n) {
		if(n.right.accept(this) == 0 || n.left.accept(this) == 0){
			return "0";
		}
		if(n.right.accept(this) == 1 ){
			return n.left.accept();
		}
		if(n.left.accept(this) == 1 ){
			return n.right.accept();
		}
		return String.toString(n.left.accept(this) * n.right.accept(this));
	}

	@Override
	public String visit(Quotient n) {
		if( n.left.accept(this) == 0 && n.right.accept(this) != 0){
			return "0";
		}
		if(n.right.accept(this) == 1 && n.right.accept(this) != 0){
			return n.left.accept();
		}		
		return String.toString(n.left.accept(this) * n.right.accept(this));
	}

	@Override
	public String visit(Mod n) {
		return String.toString(n.left.accept(this) * n.right.accept(this));
	}

	@Override
	public String visit(Assign n) {
		return null;
	}


}
