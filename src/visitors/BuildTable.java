package visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treeNodes.NExpParam;
import treeNodes.NFunctionDecl;
import treeNodes.NMainFunc;
import treeNodes.NParameter;
import treeNodes.NProgram;
import treeNodes.NStatement;
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
import treeNodes.types.NVoidType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class BuildTable extends Visitor {

	public Table scopeTab, methodTab, programTab;
	public boolean insideScoped = false;
	private Map<String, Table> tables = new HashMap<>(); // table for debugging
	private int i = 1; // just for debugging

	@Override
	public Object visit(NProgram n) throws SemanticException {
		scopeTab = new Table();
		methodTab = new Table();
		programTab = new Table();
		for (NFunctionDecl decl : n.functionDeclList)
			visit(decl);
		visit(n.main);
		if (programTab != null)
			tables.put("program", programTab);
		n.setTab(programTab);
		return null;
	}

	@Override
	public Object visit(NFunctionDecl n) throws SemanticException {
		methodTab = new Table();
		if (!programTab.symTable.containsKey(n.name))
			programTab.put(n.name, new Binding(n.name, IdType.METHOD, n.type, n.parameters));
		else
			throw new SemanticException("Variable " + n.name.name + " already exists");
		if (n.parameters != null) {
			for (NParameter p : n.parameters) {
				visit(p);
			}
		}
		for (NStatement st : n.body) {
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
		n.setTab(methodTab);
		if (methodTab != null)
			tables.put(n.name.name, methodTab);
		return null;
	}

	@Override
	public Object visit(NExpParam n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NMainFunc n) throws SemanticException {
		methodTab = new Table();
		if (!programTab.symTable.containsKey(n.name))
			programTab.put(n.name, new Binding(n.name, IdType.METHOD, new NVoidType()));
		else
			throw new SemanticException("Variable " + n.name.name + " already exists");
		for (NStatement st : n.body) {
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
		n.setTab(methodTab);
		if (methodTab != null)
			tables.put("main", methodTab);
		return null;
	}

	@Override
	public Object visit(NParameter n) throws SemanticException {
		if (!methodTab.symTable.containsKey(n.name))
			methodTab.put(n.name, new Binding(n.name, IdType.VARIABLE, n.type));
		else
			throw new SemanticException("Variable " + n.name.name + " already exists");
		return null;
	}

	@Override
	public Object visit(NVariable n) throws SemanticException {
		if (!insideScoped) {
			if (!methodTab.symTable.containsKey(n.id))
				methodTab.put(n.id, new Binding(n.id, IdType.VARIABLE, n.type));
			else
				throw new SemanticException("Variable " + n.id.name + " already exists");
		} else {
			if (!scopeTab.symTable.containsKey(n.id))
				scopeTab.put(n.id, new Binding(n.id, IdType.VARIABLE, n.type));
			else
				throw new SemanticException("Variable " + n.id.name + " already exists");
		}
		return null;
	}

	@Override
	public Object visit(NAssign n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NElseStatement n) throws SemanticException {
		if (n.stm instanceof NAssign) {
			visit((NAssign) n.stm);
		} else if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NMethodCall) {
			visit((NMethodCall) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
		}
		return null;
	}

	@Override
	public Object visit(NIfStatement n) throws SemanticException {
		if (n.stm instanceof NAssign) {
			visit((NAssign) n.stm);
		} else if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NMethodCall) {
			visit((NMethodCall) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
		}
		if (n.elsePart != null) {
			visit(n.elsePart);
		}
		n.setTab(scopeTab);
		return null;
	}

	@Override
	public Object visit(NMethodCall n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NScopedStatement n) throws SemanticException {
		insideScoped = true;
		scopeTab = new Table();
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
		n.setTab(scopeTab);
		if (scopeTab != null) {
			tables.put("scope" + i, scopeTab);
			i++;
		}
		insideScoped = false;
		return null;
	}

	@Override
	public Object visit(NVarDeclStatement n) throws SemanticException {
		for (NVariable var : n.variables) {
			visit(var);
		}
		return null;
	}

	@Override
	public Object visit(NWhileStatement n) throws SemanticException {
		if (n.stm instanceof NAssign) {
			visit((NAssign) n.stm);
		} else if (n.stm instanceof NIfStatement) {
			visit((NIfStatement) n.stm);
		} else if (n.stm instanceof NMethodCall) {
			visit((NMethodCall) n.stm);
		} else if (n.stm instanceof NScopedStatement) {
			visit((NScopedStatement) n.stm);
		} else if (n.stm instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) n.stm);
		} else if (n.stm instanceof NWhileStatement) {
			visit((NWhileStatement) n.stm);
		} else if (n.stm instanceof NWhenStatement) {
			visit((NWhenStatement) n.stm);
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

	public void printTables() {
		for (String t : tables.keySet()) {
			if (t != null && !tables.get(t).symTable.isEmpty()) {
				System.out.println(t);
				System.out.println(tables.get(t));
			}
		}
	}

	@Override
	public Object visit(NReturnStatement n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NWhenStatement n) throws SemanticException {
		if (n.body instanceof NAssign) {
			visit((NAssign) n.body);
		} else if (n.body instanceof NIfStatement) {
			visit((NIfStatement) n.body);
		} else if (n.body instanceof NMethodCall) {
			visit((NMethodCall) n.body);
		} else if (n.body instanceof NScopedStatement) {
			visit((NScopedStatement) n.body);
		} else if (n.body instanceof NVarDeclStatement) {
			visit((NVarDeclStatement) n.body);
		} else if (n.body instanceof NWhileStatement) {
			visit((NWhileStatement) n.body);
		} else if (n.body instanceof NWhenStatement) {
			visit((NWhenStatement) n.body);
		}
		return null;
	}
}
