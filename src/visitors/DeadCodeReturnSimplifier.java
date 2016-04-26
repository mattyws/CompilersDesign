package visitors;

import treeNodes.NExpParam;
import treeNodes.NFunctionDecl;
import treeNodes.NMainFunc;
import treeNodes.NParameter;
import treeNodes.NProgram;
import treeNodes.NStatement;
import treeNodes.NType;
import treeNodes.NVariable;
import treeNodes.SemanticException;
import treeNodes.exp.NAnd;
import treeNodes.exp.NArrayAttribution;
import treeNodes.exp.NArrayCall;
import treeNodes.exp.NBoolean;
import treeNodes.exp.NChar;
import treeNodes.exp.NDiv;
import treeNodes.exp.NEqual;
import treeNodes.exp.NExpMethodCall;
import treeNodes.exp.NFloatNum;
import treeNodes.exp.NGreaterEqual;
import treeNodes.exp.NGreaterThan;
import treeNodes.exp.NIdentifier;
import treeNodes.exp.NIntNum;
import treeNodes.exp.NLessEqual;
import treeNodes.exp.NLessThan;
import treeNodes.exp.NMinus;
import treeNodes.exp.NMod;
import treeNodes.exp.NMult;
import treeNodes.exp.NNot;
import treeNodes.exp.NOr;
import treeNodes.exp.NPlus;
import treeNodes.exp.NString;
import treeNodes.statements.NAssign;
import treeNodes.statements.NElseStatement;
import treeNodes.statements.NIfStatement;
import treeNodes.statements.NMethodCall;
import treeNodes.statements.NReturnStatement;
import treeNodes.statements.NScopedStatement;
import treeNodes.statements.NVarDeclStatement;
import treeNodes.statements.NWhenStatement;
import treeNodes.statements.NWhileStatement;

public class DeadCodeReturnSimplifier extends Visitor {

	@Override
	public Object visit(NProgram n) throws SemanticException {
		for (NFunctionDecl decl : n.functionDeclList) {
			visit(decl);
		}
		visit(n.main);
		return null;
	}

	@Override
	public Object visit(NFunctionDecl n) throws SemanticException {
		int i = 0;
		while (i < n.body.size()) {
			if (n.body.get(i) instanceof NReturnStatement) {
				while (i < n.body.size()-1) {
					n.body.remove(i + 1);
				}
			} else if (n.body.get(i) instanceof NIfStatement) {
				visit((NIfStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NScopedStatement) {
				visit((NScopedStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NWhileStatement) {
				visit((NWhileStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NWhenStatement) {
				visit((NWhenStatement) n.body.get(i));
			}
			i++;
		}
		return null;
	}

	@Override
	public Object visit(NExpParam n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMainFunc n) throws SemanticException {
		int i = 0;
		while (i < n.body.size()) {
			if (n.body.get(i) instanceof NReturnStatement) {
				while (i < n.body.size()-1) {
					n.body.remove(i + 1);
				}
			} else if (n.body.get(i) instanceof NIfStatement) {
				visit((NIfStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NScopedStatement) {
				visit((NScopedStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NWhileStatement) {
				visit((NWhileStatement) n.body.get(i));
			} else if (n.body.get(i) instanceof NWhenStatement) {
				visit((NWhenStatement) n.body.get(i));
			}
			i++;
		}
		return null;
	}

	@Override
	public Object visit(NParameter n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NVariable n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NAssign n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NElseStatement n) throws SemanticException {
		if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
		}
		return null;
	}

	@Override
	public Object visit(NReturnStatement n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NIfStatement n) throws SemanticException {
		if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
		}
		return null;
	}

	@Override
	public Object visit(NMethodCall n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NScopedStatement n) throws SemanticException {
		int i = 0;
		while (i < n.statements.size()) {
			if (n.statements.get(i) instanceof NReturnStatement) {
				while (i < n.statements.size()-1) {
					n.statements.remove(i + 1);
				}
			} else if (n.statements.get(i) instanceof NIfStatement) {
				visit((NIfStatement) n.statements.get(i));
			} else if (n.statements.get(i) instanceof NScopedStatement) {
				visit((NScopedStatement) n.statements.get(i));
			} else if (n.statements.get(i) instanceof NWhileStatement) {
				visit((NWhileStatement) n.statements.get(i));
			} else if (n.statements.get(i) instanceof NWhenStatement) {
				visit((NWhenStatement) n.statements.get(i));
			}
			i++;
		}
		return null;
	}

	@Override
	public Object visit(NVarDeclStatement n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NWhileStatement n) throws SemanticException {
		if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
		}
		return null;
	}

	@Override
	public Object visit(NWhenStatement n) throws SemanticException {
		if (n.body instanceof NIfStatement) {
			visit((NIfStatement) n.body);
		} else if (n.body instanceof NScopedStatement) {
			visit((NScopedStatement) n.body);
		} else if (n.body instanceof NWhileStatement) {
			visit((NWhileStatement) n.body);
		} else if (n.body instanceof NWhenStatement) {
			visit((NWhenStatement) n.body);
		}
		return null;
	}

	@Override
	public Object visit(NAnd n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NArrayCall n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NBoolean n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NChar n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NDiv n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NEqual n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NExpMethodCall n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NFloatNum n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NGreaterEqual n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NGreaterThan n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NIdentifier n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NIntNum n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NLessEqual n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NLessThan n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMinus n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMod n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMult n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NNot n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NOr n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NPlus n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NString n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		return null;
	}

}
