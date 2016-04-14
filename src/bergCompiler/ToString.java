package bergCompiler;

public class ToString implements Visitor {

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
		return n.varName;
	}

	@Override
	public String visit(Product n) {
		String value = n.left.toString() + " * ";
		value += n.right.toString();
		return value;
	}

	@Override
	public String visit(Quotient n) {
		String value = n.left.toString() + " / ";
		value += n.right.toString();
		return value;
	}

	@Override
	public String visit(Mod n) {
		String value = n.left.toString() + " % ";
		value += n.right.toString();
		return value;
	}

	@Override
	public String visit(Assign n) {
		String value = n.id + " = ";
		value += n.value.toString();
		return value;
	}


}
