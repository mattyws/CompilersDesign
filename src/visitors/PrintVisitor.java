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

public class PrintVisitor extends Visitor {

	String program = "";
	String tab = "";

	@Override
	public Object visit(NProgram n) throws SemanticException {
		for (NFunctionDecl decl : n.functionDeclList) {
			program += (String) visit(decl) + "\n";
		}
		program += (String) visit(n.main);
		return program;
	}

	@Override
	public Object visit(NFunctionDecl n) throws SemanticException {
		String func = n.type + " " + n.name.name + "(";
		if (n.parameters != null) {
			for (NParameter p : n.parameters) {
				func += p.type + " " + p.name.name + ", ";
			}
			func = func.substring(0, func.length() - 2);
		}
		func += ") {\n";
		tab = "\t";
		for (NStatement st : n.body) {
			if (st instanceof NAssign) {
				func += (String) visit((NAssign) st) + "\n";
			} else if (st instanceof NReturnStatement) {
				func += (String) visit((NReturnStatement) st) + "\n";
			} else if (st instanceof NIfStatement) {
				func += (String) visit((NIfStatement) st) + "\n";
			} else if (st instanceof NMethodCall) {
				func += (String) visit((NMethodCall) st) + "\n";
			} else if (st instanceof NScopedStatement) {
				func += (String) visit((NScopedStatement) st) + "\n";
			} else if (st instanceof NVarDeclStatement) {
				func += (String) visit((NVarDeclStatement) st) + "\n";
			} else if (st instanceof NWhileStatement) {
				func += (String) visit((NWhileStatement) st) + "\n";
			} else if (st instanceof NWhenStatement) {
				func += (String) visit((NWhenStatement) st) + "\n";
			}
		}
		tab = "\t";
		func += "}\n";
		return func;
	}

	@Override
	public Object visit(NExpParam n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMainFunc n) throws SemanticException {
		String func = n.name.name + "() {\n";
		tab = "\t";
		for (NStatement st : n.body) {
			if (st instanceof NAssign) {
				func += (String) visit((NAssign) st) + "\n";
			} else if (st instanceof NReturnStatement) {
				func += (String) visit((NReturnStatement) st) + "\n";
			} else if (st instanceof NIfStatement) {
				func += (String) visit((NIfStatement) st) + "\n";
			} else if (st instanceof NMethodCall) {
				func += (String) visit((NMethodCall) st) + "\n";
			} else if (st instanceof NScopedStatement) {
				func += (String) visit((NScopedStatement) st) + "\n";
			} else if (st instanceof NVarDeclStatement) {
				func += (String) visit((NVarDeclStatement) st) + "\n";
			} else if (st instanceof NWhileStatement) {
				func += (String) visit((NWhileStatement) st) + "\n";
			} else if (st instanceof NWhenStatement) {
				func += (String) visit((NWhenStatement) st) + "\n";
			}
		}
		func += "}\n";
		tab = "\t";
		return func;
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
		String assign = n.id.name + " = " + typeCheck(n.exp);
		return assign;
	}

	@Override
	public Object visit(NElseStatement n) throws SemanticException {
		String elseS = tab + "else ";
		tab += "\t";
		if (n.stm instanceof NAssign) {
			elseS += (String) visit((NAssign) n.stm) + "\n";
		} else if (n.stm instanceof NReturnStatement) {
			elseS += (String) visit((NReturnStatement) n.stm) + "\n";
		} else if (n.stm instanceof NIfStatement) {
			elseS += (String) visit((NIfStatement) n.stm) + "\n";
		} else if (n.stm instanceof NMethodCall) {
			elseS += (String) visit((NMethodCall) n.stm) + "\n";
		} else if (n.stm instanceof NScopedStatement) {
			elseS += (String) visit((NScopedStatement) n.stm) + "\n";
		} else if (n.stm instanceof NVarDeclStatement) {
			elseS += (String) visit((NVarDeclStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhileStatement) {
			elseS += (String) visit((NWhileStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhenStatement) {
			elseS += (String) visit((NWhenStatement) n.stm) + "\n";
		}
		tab = tab.substring(0, tab.length() - 1);
		return elseS;
	}

	@Override
	public Object visit(NReturnStatement n) throws SemanticException {
		return tab + "return(" + typeCheck(n.returnExp) + ")";
	}

	@Override
	public Object visit(NIfStatement n) throws SemanticException {
		String ifS = tab + "if(" + typeCheck(n.condition) + ")\n";
		if (n.stm instanceof NAssign) {
			ifS += (String) visit((NAssign) n.stm) + "\n";
		} else if (n.stm instanceof NReturnStatement) {
			ifS += (String) visit((NReturnStatement) n.stm) + "\n";
		} else if (n.stm instanceof NIfStatement) {
			ifS += (String) visit((NIfStatement) n.stm) + "\n";
		} else if (n.stm instanceof NMethodCall) {
			ifS += (String) visit((NMethodCall) n.stm) + "\n";
		} else if (n.stm instanceof NScopedStatement) {
			ifS += (String) visit((NScopedStatement) n.stm) + "\n";
		} else if (n.stm instanceof NVarDeclStatement) {
			ifS += (String) visit((NVarDeclStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhileStatement) {
			ifS += (String) visit((NWhileStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhenStatement) {
			ifS += (String) visit((NWhenStatement) n.stm) + "\n";
		}
		tab = tab.substring(0, tab.length() - 1);
		return ifS;
	}

	@Override
	public Object visit(NMethodCall n) throws SemanticException {
		if (n != null) {
			String methodCall = tab + n.id.name + "(";
			if (n.parameters != null && n.parameters.expressionParam != null) {
				for (NExp p : n.parameters.expressionParam) {
					methodCall += typeCheck(p) + ", ";
				}
			}
			methodCall += ")";
			return methodCall;
		}
		return null;
	}

	@Override
	public Object visit(NScopedStatement n) throws SemanticException {
		String func = tab + "{\n";
		tab += "\t";
		for (NStatement st : n.statements) {
			if (st instanceof NAssign) {
				func += (String) visit((NAssign) st) + "\n";
			} else if (st instanceof NReturnStatement) {
				func += (String) visit((NReturnStatement) st) + "\n";
			} else if (st instanceof NIfStatement) {
				func += (String) visit((NIfStatement) st) + "\n";
			} else if (st instanceof NMethodCall) {
				func += (String) visit((NMethodCall) st) + "\n";
			} else if (st instanceof NScopedStatement) {
				func += (String) visit((NScopedStatement) st) + "\n";
			} else if (st instanceof NVarDeclStatement) {
				func += (String) visit((NVarDeclStatement) st) + "\n";
			} else if (st instanceof NWhileStatement) {
				func += (String) visit((NWhileStatement) st) + "\n";
			} else if (st instanceof NWhenStatement) {
				func += (String) visit((NWhenStatement) st) + "\n";
			}
		}
		tab = tab.substring(0, tab.length() - 1);
		return func + tab + "}";
	}

	@Override
	public Object visit(NVarDeclStatement n) throws SemanticException {
		String declStm = tab + n.variables.get(0).type + " ";
		for (NVariable v : n.variables) {
			if (!declStm.isEmpty()) {
				declStm += visit(v.assign);
			} else {
				declStm += v.id.name + ", ";
			}
		}
		return declStm;
	}

	@Override
	public Object visit(NWhileStatement n) throws SemanticException {
		String whileS = tab + "while(" + typeCheck(n.condition) + ")\n";
		tab += "\t";
		if (n.stm instanceof NAssign) {
			whileS += (String) visit((NAssign) n.stm) + "\n";
		} else if (n.stm instanceof NReturnStatement) {
			whileS += (String) visit((NReturnStatement) n.stm) + "\n";
		} else if (n.stm instanceof NIfStatement) {
			whileS += (String) visit((NIfStatement) n.stm) + "\n";
		} else if (n.stm instanceof NMethodCall) {
			whileS += (String) visit((NMethodCall) n.stm) + "\n";
		} else if (n.stm instanceof NScopedStatement) {
			whileS += (String) visit((NScopedStatement) n.stm) + "\n";
		} else if (n.stm instanceof NVarDeclStatement) {
			whileS += (String) visit((NVarDeclStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhileStatement) {
			whileS += (String) visit((NWhileStatement) n.stm) + "\n";
		} else if (n.stm instanceof NWhenStatement) {
			whileS += (String) visit((NWhenStatement) n.stm) + "\n";
		}
		tab = tab.substring(0, tab.length() - 1);
		return whileS;
	}

	@Override
	public Object visit(NWhenStatement n) throws SemanticException {
		String shen = tab + "when(" + n.id.name + ", " + typeCheck(n.value) + ")\n";
		tab += "\t";
		if (n.body instanceof NAssign) {
			shen += (String) visit((NAssign) n.body) + "\n";
		} else if (n.body instanceof NReturnStatement) {
			shen += (String) visit((NReturnStatement) n.body) + "\n";
		} else if (n.body instanceof NIfStatement) {
			shen += (String) visit((NIfStatement) n.body) + "\n";
		} else if (n.body instanceof NMethodCall) {
			shen += (String) visit((NMethodCall) n.body) + "\n";
		} else if (n.body instanceof NScopedStatement) {
			shen += (String) visit((NScopedStatement) n.body) + "\n";
		} else if (n.body instanceof NVarDeclStatement) {
			shen += (String) visit((NVarDeclStatement) n.body) + "\n";
		} else if (n.body instanceof NWhileStatement) {
			shen += (String) visit((NWhileStatement) n.body) + "\n";
		} else if (n.body instanceof NWhenStatement) {
			shen += (String) visit((NWhenStatement) n.body) + "\n";
		}
		tab = tab.substring(0, tab.length() - 1);
		return shen;
	}

	@Override
	public Object visit(NAnd n) throws SemanticException {
		String and = typeCheck(n.l) + " & " + typeCheck(n.r);
		return and;
	}

	@Override
	public Object visit(NArrayCall n) throws SemanticException {
		String aray = n.id.name + "[" + typeCheck(n.exp) + "]";
		return aray;
	}

	@Override
	public Object visit(NBoolean n) throws SemanticException {
		return n.value;
	}

	@Override
	public Object visit(NChar n) throws SemanticException {
		return n.value;
	}

	@Override
	public Object visit(NDiv n) throws SemanticException {
		String div = typeCheck(n.l) + " / " + typeCheck(n.r);
		return div;
	}

	@Override
	public Object visit(NEqual n) throws SemanticException {
		String eq = typeCheck(n.l) + " == " + typeCheck(n.r);
		return eq;
	}

	@Override
	public Object visit(NExpMethodCall n) throws SemanticException {
		String expCall = n.id.name + "(";
		for (NExp e : n.parameters.expressionParam) {
			expCall += typeCheck(e) + ", ";
		}
		expCall = expCall.substring(0, expCall.length() - 3) + ")";
		return expCall;
	}

	@Override
	public Object visit(NFloatNum n) throws SemanticException {
		return String.valueOf(n.value);
	}

	@Override
	public Object visit(NGreaterEqual n) throws SemanticException {
		String ge = typeCheck(n.l) + " >= " + typeCheck(n.r);
		return ge;
	}

	@Override
	public Object visit(NGreaterThan n) throws SemanticException {
		String gt = typeCheck(n.l) + " > " + typeCheck(n.r);
		return gt;
	}

	@Override
	public Object visit(NIdentifier n) throws SemanticException {
		return n.name;
	}

	@Override
	public Object visit(NIntNum n) throws SemanticException {
		return String.valueOf(n.value);
	}

	@Override
	public Object visit(NLessEqual n) throws SemanticException {
		String le = typeCheck(n.l) + " <= " + typeCheck(n.r);
		return le;
	}

	@Override
	public Object visit(NLessThan n) throws SemanticException {
		String lt = typeCheck(n.l) + " < " + typeCheck(n.r);
		return lt;
	}

	@Override
	public Object visit(NMinus n) throws SemanticException {
		String minus = typeCheck(n.l) + " - " + typeCheck(n.r);
		return minus;
	}

	@Override
	public Object visit(NMod n) throws SemanticException {
		String mod = typeCheck(n.l) + " & " + typeCheck(n.r);
		return mod;
	}

	@Override
	public Object visit(NMult n) throws SemanticException {
		String mult = typeCheck(n.l) + " * " + typeCheck(n.r);
		return mult;
	}

	@Override
	public Object visit(NNot n) throws SemanticException {
		String not = "! " + typeCheck(n.l);
		return not;
	}

	@Override
	public Object visit(NOr n) throws SemanticException {
		String or = typeCheck(n.l) + " | " + typeCheck(n.r);
		return or;
	}

	@Override
	public Object visit(NPlus n) throws SemanticException {
		String plus = typeCheck(n.l) + " + " + typeCheck(n.r);
		return plus;
	}

	@Override
	public Object visit(NString n) throws SemanticException {
		return n.value;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		String arrayAtt = "{";
		for (NExp e : n.values.values()) {
			arrayAtt += typeCheck(e) + ", ";
		}
		arrayAtt = arrayAtt.substring(0, arrayAtt.length() - 3) + "}";
		return arrayAtt;
	}

	private String typeCheck(NExp exp) throws SemanticException {
		String type;
		if (exp instanceof NAnd) {
			type = (String) visit((NAnd) exp);
		} else if (exp instanceof NArrayCall) {
			type = (String) visit((NArrayCall) exp);
		} else if (exp instanceof NBoolean) {
			type = (String) visit((NBoolean) exp);
		} else if (exp instanceof NChar) {
			type = (String) visit((NChar) exp);
		} else if (exp instanceof NDiv) {
			type = (String) visit((NDiv) exp);
		} else if (exp instanceof NEqual) {
			type = (String) visit((NEqual) exp);
		} else if (exp instanceof NExpMethodCall) {
			type = (String) visit((NExpMethodCall) exp);
		} else if (exp instanceof NFloatNum) {
			type = (String) visit((NFloatNum) exp);
		} else if (exp instanceof NGreaterEqual) {
			type = (String) visit((NGreaterEqual) exp);
		} else if (exp instanceof NGreaterThan) {
			type = (String) visit((NGreaterThan) exp);
		} else if (exp instanceof NIdentifier) {
			type = (String) visit((NIdentifier) exp);
		} else if (exp instanceof NIntNum) {
			type = (String) visit((NIntNum) exp);
		} else if (exp instanceof NLessEqual) {
			type = (String) visit((NLessEqual) exp);
		} else if (exp instanceof NLessThan) {
			type = (String) visit((NLessThan) exp);
		} else if (exp instanceof NMinus) {
			type = (String) visit((NMinus) exp);
		} else if (exp instanceof NMod) {
			type = (String) visit((NMod) exp);
		} else if (exp instanceof NMult) {
			type = (String) visit((NMult) exp);
		} else if (exp instanceof NNot) {
			type = (String) visit((NNot) exp);
		} else if (exp instanceof NOr) {
			type = (String) visit((NOr) exp);
		} else if (exp instanceof NPlus) {
			type = (String) visit((NPlus) exp);
		} else if (exp instanceof NArrayAttribution) {
			type = (String) visit((NArrayAttribution) exp);
		} else {
			type = (String) visit((NString) exp);
		}
		return type;
	}

}
