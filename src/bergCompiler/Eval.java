package bergCompiler;

public class Eval implements Visitor {

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
		return n.left.accept(this) + n.right.accept(this);
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
		return n.left.accept(this) * n.right.accept(this);
	}

	@Override
	public String visit(Quotient n) {
		return n.left.accept(this) / n.right.accept(this);
	}

	@Override
	public String visit(Mod n) {
		return n.left.accept(this) % n.right.accept(this);
	}

	@Override
	public String visit(Assign n) {
		return n.value.accept(this);
	}


}
