package visitors;

import treeNodes.NExp;
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
import treeNodes.types.NBooleanType;

public class ExpressionSimplifierVisitor extends Visitor {

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
		for (NStatement st : n.body) {
			if (st instanceof NAssign) {
				visit((NAssign) st);
			} else if (st instanceof NReturnStatement) {
				visit((NReturnStatement) st);
			} else if (st instanceof NIfStatement) {
				visit((NIfStatement) st);
			} else if (st instanceof NMethodCall) {
				visit((NMethodCall) st);
			} else if (st instanceof NScopedStatement) {
				visit((NScopedStatement) st);
			} else if (st instanceof NVarDeclStatement) {
				visit((NVarDeclStatement) st);
			} else if (st instanceof NWhileStatement) {
				visit((NWhileStatement) st);
			} else if (st instanceof NWhenStatement) {
				visit((NWhenStatement) st);
			}
		}
		return null;
	}

	@Override
	public Object visit(NExpParam n) throws SemanticException {
		int i = 0;
		if (n != null) {
			while (i < n.expressionParam.size()) {
				NExp e = typeCheck(n.expressionParam.get(i));
				if (e != null) {
					n.expressionParam.set(i, e);
				}
				i++;
			}
		}
		return null;
	}

	@Override
	public Object visit(NMainFunc n) throws SemanticException {
		for (NStatement st : n.body) {
			if (st instanceof NAssign) {
				visit((NAssign) st);
			} else if (st instanceof NIfStatement) {
				visit((NIfStatement) st);
			} else if (st instanceof NReturnStatement) {
				visit((NReturnStatement) st);
			} else if (st instanceof NMethodCall) {
				visit((NMethodCall) st);
			} else if (st instanceof NScopedStatement) {
				visit((NScopedStatement) st);
			} else if (st instanceof NVarDeclStatement) {
				visit((NVarDeclStatement) st);
			} else if (st instanceof NWhileStatement) {
				visit((NWhileStatement) st);
			} else if (st instanceof NWhenStatement) {
				visit((NWhenStatement) st);
			}
		}
		return null;
	}

	@Override
	public Object visit(NParameter n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NVariable n) throws SemanticException {
		if (n.assign != null) {
			visit(n.assign);
		}
		return null;
	}

	@Override
	public Object visit(NAssign n) throws SemanticException {
		NExp e = typeCheck(n.exp);
		if (e != null) {
			n.exp = e;
		}
		return null;
	}

	@Override
	public Object visit(NElseStatement n) throws SemanticException {
		NStatement st = n.stm;
		if (st instanceof NAssign) {
			visit((NAssign) st);
		} else if (st instanceof NReturnStatement) {
			visit((NReturnStatement) st);
		} else if (st instanceof NIfStatement) {
			visit((NIfStatement) st);
		} else if (st instanceof NMethodCall) {
			visit((NMethodCall) st);
		} else if (st instanceof NScopedStatement) {
			visit((NScopedStatement) st);
		} else if (st instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) st);
		} else if (st instanceof NWhileStatement) {
			visit((NWhileStatement) st);
		} else if (st instanceof NWhenStatement) {
			visit((NWhenStatement) st);
		}
		return null;
	}

	@Override
	public Object visit(NReturnStatement n) throws SemanticException {
		NExp e = typeCheck(n.returnExp);
		if (e != null)
			n.returnExp = e;
		return null;
	}

	@Override
	public Object visit(NIfStatement n) throws SemanticException {
		NExp cond = typeCheck(n.condition);
		if (cond != null)
			n.condition = cond;
		NStatement st = n.stm;
		if (st instanceof NAssign) {
			visit((NAssign) st);
		} else if (st instanceof NReturnStatement) {
			visit((NReturnStatement) st);
		} else if (st instanceof NIfStatement) {
			visit((NIfStatement) st);
		} else if (st instanceof NMethodCall) {
			visit((NMethodCall) st);
		} else if (st instanceof NScopedStatement) {
			visit((NScopedStatement) st);
		} else if (st instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) st);
		} else if (st instanceof NWhileStatement) {
			visit((NWhileStatement) st);
		} else if (st instanceof NWhenStatement) {
			visit((NWhenStatement) st);
		}
		return null;
	}

	@Override
	public Object visit(NMethodCall n) throws SemanticException {
		visit(n.parameters);
		return null;
	}

	@Override
	public Object visit(NScopedStatement n) throws SemanticException {
		for (NStatement st : n.statements) {
			if (st instanceof NAssign) {
				visit((NAssign) st);
			} else if (st instanceof NIfStatement) {
				visit((NIfStatement) st);
			} else if (st instanceof NMethodCall) {
				visit((NMethodCall) st);
			} else if (st instanceof NScopedStatement) {
				visit((NScopedStatement) st);
			} else if (st instanceof NVarDeclStatement) {
				visit((NVarDeclStatement) st);
			} else if (st instanceof NWhileStatement) {
				visit((NWhileStatement) st);
			} else if (st instanceof NWhenStatement) {
				visit((NWhenStatement) st);
			}
		}
		return null;
	}

	@Override
	public Object visit(NVarDeclStatement n) throws SemanticException {
		for (NVariable v : n.variables) {
			visit(v);
		}
		return null;
	}

	@Override
	public Object visit(NWhileStatement n) throws SemanticException {
		NExp e = typeCheck(n.condition);
		if (e != null)
			n.condition = e;
		NStatement st = n.stm;
		if (st instanceof NAssign) {
			visit((NAssign) st);
		} else if (st instanceof NIfStatement) {
			visit((NIfStatement) st);
		} else if (st instanceof NMethodCall) {
			visit((NMethodCall) st);
		} else if (st instanceof NScopedStatement) {
			visit((NScopedStatement) st);
		} else if (st instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) st);
		} else if (st instanceof NWhileStatement) {
			visit((NWhileStatement) st);
		} else if (st instanceof NWhenStatement) {
			visit((NWhenStatement) st);
		}
		return null;
	}

	@Override
	public Object visit(NWhenStatement n) throws SemanticException {
		NExp e = typeCheck(n.value);
		if (e != null)
			n.value = e;
		NStatement st = n.body;
		if (st instanceof NAssign) {
			visit((NAssign) st);
		} else if (st instanceof NReturnStatement) {
			visit((NReturnStatement) st);
		} else if (st instanceof NIfStatement) {
			visit((NIfStatement) st);
		} else if (st instanceof NMethodCall) {
			visit((NMethodCall) st);
		} else if (st instanceof NScopedStatement) {
			visit((NScopedStatement) st);
		} else if (st instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) st);
		} else if (st instanceof NWhileStatement) {
			visit((NWhileStatement) st);
		} else if (st instanceof NWhenStatement) {
			visit((NWhenStatement) st);
		}
		return null;
	}

	@Override
	public Object visit(NAnd n) throws SemanticException {
		NBoolean l = null, r = null;
		if (!(n.l instanceof NBoolean)) {
			l = (NBoolean) typeCheck(n.l);
		}
		if (!(n.r instanceof NBoolean)) {
			r = (NBoolean) typeCheck(n.r);
		}
		if ((n.l instanceof NBoolean) && ((NBoolean) n.l).value == false) {
			return new NBoolean(false);
		}
		if ((n.r instanceof NBoolean) && ((NBoolean) n.r).value == false) {
			return new NBoolean(false);
		}
		if (l != null) {
			if (l instanceof NBoolean && l.value == false) {
				return new NBoolean(false);
			}
			if (l instanceof NBoolean) {
				n.l = l;
			}
		}
		if (r != null) {
			if (r instanceof NBoolean && r.value == false) {
				return new NBoolean(false);
			}
			if (r instanceof NBoolean) {
				n.r = r;
			}
		}
		return null;
	}

	@Override
	public Object visit(NArrayCall n) throws SemanticException {
		NExp e = typeCheck(n.exp);
		if (e != null)
			n.exp = e;
		return null;
	}

	@Override
	public Object visit(NBoolean n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NChar n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NDiv n) throws SemanticException {
		NExp l, r;
		if ((n.r instanceof NIntNum)) {
			if (((NIntNum) n.r).value == 0) {
				return null;
			}
		}
		if ((n.r instanceof NFloatNum)) {
			if (((NFloatNum) n.r).value == 0) {
				return null;
			}
		}
		r = typeCheck(n.r);
		if (r != null) {
			if ((r instanceof NIntNum)) {
				if (((NIntNum) r).value == 0) {
					return null;
				}
			}
			if ((r instanceof NFloatNum)) {
				if (((NFloatNum) r).value == 0) {
					return null;
				}
			}
		}

		if ((n.l instanceof NIntNum)) {
			if (((NIntNum) n.l).value == 0) {
				return new NIntNum(0);
			}
		}
		if ((n.l instanceof NFloatNum)) {
			if (((NFloatNum) n.l).value == 0) {
				return new NIntNum(0);
			}
		}
		l = typeCheck(n.l);
		if (l != null) {
			if ((l instanceof NIntNum)) {
				if (((NIntNum) l).value == 0) {
					return new NIntNum(0);
				}
			}
			if ((l instanceof NFloatNum)) {
				if (((NFloatNum) l).value == 0) {
					return new NIntNum(0);
				}
			}
		}

		if ((n.r instanceof NIntNum)) {
			if (((NIntNum) n.r).value == 1) {
				return n.l;
			}
		}
		if ((n.r instanceof NFloatNum)) {
			if (((NFloatNum) n.r).value == 1) {
				return n.l;
			}
		}
		if (r != null) {
			if ((r instanceof NIntNum)) {
				if (((NIntNum) r).value == 1) {
					return l;
				}
			}
			if ((r instanceof NFloatNum)) {
				if (((NFloatNum) r).value == 1) {
					return l;
				}
			}
		}
		if (((n.r instanceof NIntNum) || (n.r instanceof NFloatNum))
				&& ((n.l instanceof NFloatNum) || (n.l instanceof NIntNum))) {
			if ((n.l instanceof NIntNum) && (n.r instanceof NFloatNum)) {
				return new NFloatNum(((NIntNum) n.l).value / ((NFloatNum) n.r).value);
			} else if ((n.r instanceof NIntNum) && (n.l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) n.l).value / ((NIntNum) n.r).value);
			} else if ((n.r instanceof NFloatNum) && (n.l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) n.l).value / ((NFloatNum) n.r).value);
			} else {
				return new NFloatNum((float) ((NIntNum) n.l).value / ((NIntNum) n.r).value);
			}
		}
		System.out.println((float) 5 * 4 / 3);
		if (((r instanceof NIntNum) || (r instanceof NFloatNum))
				&& ((l instanceof NFloatNum) || (l instanceof NIntNum))) {
			if ((l instanceof NIntNum) && (r instanceof NFloatNum)) {
				return new NFloatNum(((NIntNum) l).value / ((NFloatNum) r).value);
			} else if ((r instanceof NIntNum) && (l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) l).value / ((NIntNum) r).value);
			} else if ((r instanceof NFloatNum) && (l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) l).value / ((NFloatNum) r).value);
			} else {
				return new NFloatNum((float) ((NIntNum) l).value / ((NIntNum) r).value);
			}
		}

		if ((r instanceof NIntNum) || (r instanceof NFloatNum)) {
			n.r = r;
		}

		if ((l instanceof NIntNum) || (l instanceof NFloatNum)) {
			n.l = l;
		}

		return null;
	}

	@Override
	public Object visit(NEqual n) throws SemanticException {
		NExp r = typeCheck(n.r);
		if (r != null) {
			n.r = r;
		}
		NExp l = typeCheck(n.l);
		if (l != null) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NExpMethodCall n) throws SemanticException {
		visit(n.parameters);
		return null;
	}

	@Override
	public Object visit(NFloatNum n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NGreaterEqual n) throws SemanticException {
		NExp r = typeCheck(n.r);
		if (r != null) {
			n.r = r;
		}
		NExp l = typeCheck(n.l);
		if (l != null) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NGreaterThan n) throws SemanticException {
		NExp r = typeCheck(n.r);
		if (r != null) {
			n.r = r;
		}
		NExp l = typeCheck(n.l);
		if (l != null) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NIdentifier n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NIntNum n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NLessEqual n) throws SemanticException {
		NExp r = typeCheck(n.r);
		if (r != null) {
			n.r = r;
		}
		NExp l = typeCheck(n.l);
		if (l != null) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NLessThan n) throws SemanticException {
		NExp r = typeCheck(n.r);
		if (r != null) {
			n.r = r;
		}
		NExp l = typeCheck(n.l);
		if (l != null) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NMinus n) throws SemanticException {
		NExp l = typeCheck(n.l), r = typeCheck(n.r);
		if (l != null && r != null) {
			if (((l instanceof NIntNum) || (l instanceof NFloatNum))
					&& ((r instanceof NIntNum) || (r instanceof NFloatNum))) {
				if ((l instanceof NIntNum) && (r instanceof NFloatNum)) {
					return new NFloatNum(((NIntNum) l).value - ((NFloatNum) r).value);
				} else if ((r instanceof NIntNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value - ((NIntNum) r).value);
				} else if ((r instanceof NFloatNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value - ((NFloatNum) r).value);
				} else {
					return new NIntNum(((NIntNum) l).value - ((NIntNum) r).value);
				}
			}
		}
		if (l != null) {
			n.l = l;
		}
		if (r != null) {
			n.r = r;
		}
		return null;
	}

	@Override
	public Object visit(NMod n) throws SemanticException {
		NExp l, r;
		if ((n.r instanceof NIntNum)) {
			if (((NIntNum) n.r).value == 0) {
				return null;
			}
		}
		if ((n.r instanceof NFloatNum)) {
			if (((NFloatNum) n.r).value == 0) {
				return null;
			}
		}
		r = typeCheck(n.r);
		if (r != null) {
			if ((r instanceof NIntNum)) {
				if (((NIntNum) r).value == 0) {
					return null;
				}
			}
			if ((r instanceof NFloatNum)) {
				if (((NFloatNum) r).value == 0) {
					return null;
				}
			}
		}

		if ((n.l instanceof NIntNum)) {
			if (((NIntNum) n.l).value == 0) {
				return new NIntNum(0);
			}
		}
		if ((n.l instanceof NFloatNum)) {
			if (((NFloatNum) n.l).value == 0) {
				return new NIntNum(0);
			}
		}
		l = typeCheck(n.l);
		if (l != null) {
			if ((l instanceof NIntNum)) {
				if (((NIntNum) l).value == 0) {
					return new NIntNum(0);
				}
			}
			if ((l instanceof NFloatNum)) {
				if (((NFloatNum) l).value == 0) {
					return new NIntNum(0);
				}
			}
		}

		if ((n.r instanceof NIntNum)) {
			if (((NIntNum) n.r).value == 1) {
				return n.l;
			}
		}
		if ((n.r instanceof NFloatNum)) {
			if (((NFloatNum) n.r).value == 1) {
				return n.l;
			}
		}
		if (r != null) {
			if ((r instanceof NIntNum)) {
				if (((NIntNum) r).value == 1) {
					return l;
				}
			}
			if ((r instanceof NFloatNum)) {
				if (((NFloatNum) r).value == 1) {
					return l;
				}
			}
		}

		if (((n.r instanceof NIntNum) || (n.r instanceof NFloatNum))
				&& ((n.l instanceof NFloatNum) || (n.l instanceof NIntNum))) {
			if ((n.l instanceof NIntNum) && (n.r instanceof NFloatNum)) {
				return new NFloatNum(((NIntNum) n.l).value % ((NFloatNum) n.r).value);
			} else if ((n.r instanceof NIntNum) && (n.l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) n.l).value % ((NIntNum) n.r).value);
			} else if ((n.r instanceof NFloatNum) && (n.l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) n.l).value % ((NFloatNum) n.r).value);
			} else {
				return new NIntNum(((NIntNum) n.l).value % ((NIntNum) n.r).value);
			}
		}

		if (((r instanceof NIntNum) || (r instanceof NFloatNum))
				&& ((l instanceof NFloatNum) || (l instanceof NIntNum))) {
			if ((l instanceof NIntNum) && (r instanceof NFloatNum)) {
				return new NFloatNum(((NIntNum) l).value % ((NFloatNum) r).value);
			} else if ((r instanceof NIntNum) && (l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) l).value % ((NIntNum) r).value);
			} else if ((r instanceof NFloatNum) && (l instanceof NFloatNum)) {
				return new NFloatNum(((NFloatNum) l).value % ((NFloatNum) r).value);
			} else {
				return new NIntNum(((NIntNum) l).value % ((NIntNum) r).value);
			}
		}
		if ((r instanceof NIntNum) || (r instanceof NFloatNum)) {
			n.r = r;
		}
		if ((l instanceof NIntNum) || (l instanceof NFloatNum)) {
			n.l = l;
		}
		return null;
	}

	@Override
	public Object visit(NMult n) throws SemanticException {
		NExp l = typeCheck(n.l), r = typeCheck(n.r);
		if (l != null && r != null) {
			if (((l instanceof NIntNum) || (l instanceof NFloatNum))
					&& ((r instanceof NIntNum) || (r instanceof NFloatNum))) {
				if (l instanceof NIntNum) {
					if (((NIntNum) l).value == 0)
						return new NIntNum(0);
				}
				if (l instanceof NFloatNum) {
					if (((NFloatNum) l).value == 0)
						return new NIntNum(0);
				}
				if (r instanceof NIntNum) {
					if (((NIntNum) r).value == 0)
						return new NIntNum(0);
				}
				if (r instanceof NFloatNum) {
					if (((NFloatNum) r).value == 0)
						return new NIntNum(0);
				}
				if ((l instanceof NIntNum) && (r instanceof NFloatNum)) {
					return new NFloatNum(((NIntNum) l).value * ((NFloatNum) r).value);
				} else if ((r instanceof NIntNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value * ((NIntNum) r).value);
				} else if ((r instanceof NFloatNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value * ((NFloatNum) r).value);
				} else {
					return new NIntNum(((NIntNum) l).value * ((NIntNum) r).value);
				}
			}
		}
		return null;
	}

	@Override
	public Object visit(NNot n) throws SemanticException {
		NExp l = typeCheck(n.l);
		if (l != null) {
			if (l instanceof NBoolean) {
				return new NBoolean(!((NBoolean) l).value);
			} else {
				n.l = l;
			}
		}
		return null;
	}

	@Override
	public Object visit(NOr n) throws SemanticException {
		NBoolean l = null, r = null;
		if (!(n.l instanceof NBoolean)) {
			l = (NBoolean) typeCheck(n.l);
		}
		if (!(n.r instanceof NBoolean)) {
			r = (NBoolean) typeCheck(n.r);
		}
		if ((n.l instanceof NBoolean) && ((NBoolean) n.l).value == true) {
			return new NBoolean(true);
		}
		if ((n.r instanceof NBoolean) && ((NBoolean) n.r).value == true) {
			return new NBoolean(true);
		}
		if (l != null) {
			if (l instanceof NBoolean && l.value == true) {
				return new NBoolean(true);
			}
			if (l instanceof NBoolean) {
				n.l = l;
			}
		}
		if (r != null) {
			if (r instanceof NBoolean && r.value == true) {
				return new NBoolean(true);
			}
			if (r instanceof NBoolean) {
				n.r = r;
			}
		}
		return null;
	}

	@Override
	public Object visit(NPlus n) throws SemanticException {
		// System.out.println("left" + n.l);
		// System.out.println("right" + n.r);
		NExp l = typeCheck(n.l), r = typeCheck(n.r);
		if (l != null && r != null) {
			if (((l instanceof NIntNum) || (l instanceof NFloatNum))
					&& ((r instanceof NIntNum) || (r instanceof NFloatNum))) {
				if ((l instanceof NIntNum) && (r instanceof NFloatNum)) {
					return new NFloatNum(((NIntNum) l).value + ((NFloatNum) r).value);
				} else if ((r instanceof NIntNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value + ((NIntNum) r).value);
				} else if ((r instanceof NFloatNum) && (l instanceof NFloatNum)) {
					return new NFloatNum(((NFloatNum) l).value + ((NFloatNum) r).value);
				} else {
					return new NIntNum(((NIntNum) l).value + ((NIntNum) r).value);
				}
			}
		}
		if (l != null) {
			n.l = l;
		}
		if (r != null) {
			n.r = r;
		}
		return null;
	}

	@Override
	public Object visit(NString n) throws SemanticException {
		return n;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		for (NIntNum i : n.values.keySet()) {
			NExp e = typeCheck(n.values.get(i));
			if (e != null) {
				n.values.put(i, e);
			}
		}
		return null;
	}

	private NExp typeCheck(NExp exp) throws SemanticException {
		NExp type;
		if (exp instanceof NAnd) {
			type = (NExp) visit((NAnd) exp);
		} else if (exp instanceof NArrayCall) {
			type = (NExp) visit((NArrayCall) exp);
		} else if (exp instanceof NBoolean) {
			type = (NExp) visit((NBoolean) exp);
		} else if (exp instanceof NChar) {
			type = (NExp) visit((NChar) exp);
		} else if (exp instanceof NDiv) {
			type = (NExp) visit((NDiv) exp);
		} else if (exp instanceof NEqual) {
			type = (NExp) visit((NEqual) exp);
		} else if (exp instanceof NExpMethodCall) {
			type = (NExp) visit((NExpMethodCall) exp);
		} else if (exp instanceof NFloatNum) {
			type = (NExp) visit((NFloatNum) exp);
		} else if (exp instanceof NGreaterEqual) {
			type = (NExp) visit((NGreaterEqual) exp);
		} else if (exp instanceof NGreaterThan) {
			type = (NExp) visit((NGreaterThan) exp);
		} else if (exp instanceof NIdentifier) {
			type = (NExp) visit((NIdentifier) exp);
		} else if (exp instanceof NIntNum) {
			type = (NExp) visit((NIntNum) exp);
		} else if (exp instanceof NLessEqual) {
			type = (NExp) visit((NLessEqual) exp);
		} else if (exp instanceof NLessThan) {
			type = (NExp) visit((NLessThan) exp);
		} else if (exp instanceof NMinus) {
			type = (NExp) visit((NMinus) exp);
		} else if (exp instanceof NMod) {
			type = (NExp) visit((NMod) exp);
		} else if (exp instanceof NMult) {
			type = (NExp) visit((NMult) exp);
		} else if (exp instanceof NNot) {
			type = (NExp) visit((NNot) exp);
		} else if (exp instanceof NOr) {
			type = (NExp) visit((NOr) exp);
		} else if (exp instanceof NPlus) {
			type = (NExp) visit((NPlus) exp);
		} else if (exp instanceof NArrayAttribution) {
			type = (NExp) visit((NArrayAttribution) exp);
		} else {
			type = (NExp) visit((NString) exp);
		}
		return type;
	}

}
