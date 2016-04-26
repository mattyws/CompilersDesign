package visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import topDownParser.FuncDecl;
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
import treeNodes.types.NArrayBooleanType;
import treeNodes.types.NArrayCharType;
import treeNodes.types.NArrayFloatType;
import treeNodes.types.NArrayIntType;
import treeNodes.types.NArrayStringType;
import treeNodes.types.NBooleanType;
import treeNodes.types.NCharType;
import treeNodes.types.NFloatType;
import treeNodes.types.NIntType;
import treeNodes.types.NStringType;

public class DeadFunctionVisitor extends Visitor {

	Map<String, List<String>> functions = new HashMap<>();
	String nowFunction;

	@Override
	public Object visit(NProgram n) throws SemanticException {
		for (NFunctionDecl decl : n.functionDeclList) {
			functions.put(decl.name.name, new ArrayList<>());
		}
		nowFunction = "main";
		visit(n.main);
		for (int i = n.functionDeclList.size() - 1; i >= 0; i--) {
			nowFunction = n.functionDeclList.get(i).name.name;
			visit(n.functionDeclList.get(i));
		}
		List<String> removedFunctions = new ArrayList<>();
		boolean removeSome = true;
		while (removeSome) {
			boolean hasRemoved = false;
			List<String> remove = new ArrayList<>();
			for (String key : functions.keySet()) {
				if(functions.get(key).isEmpty()){
					removedFunctions.add(key);
					remove.add(key);
					hasRemoved = true;
				} else {
					for(int i = 0; i < functions.get(key).size(); i++){
						if(removedFunctions.contains(functions.get(key).get(i))){
							functions.get(key).remove(i);
						}
					}
				}				
			}
			for(String r : remove){
				functions.remove(r);
			}
			if(!hasRemoved){
				removeSome = false;
			}
		}
		for (int i = n.functionDeclList.size() - 1; i >= 0; i--) {
			for(String value : removedFunctions){
				if(i < n.functionDeclList.size() && n.functionDeclList.get(i).name.name.equals(value)){
					n.functionDeclList.remove(i);
				}
			}
		}
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
		for (NExp exp : n.expressionParam) {
			typeCheck(exp);
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
		typeCheck(n.exp);
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
		typeCheck(n.returnExp);
		return null;
	}

	@Override
	public Object visit(NIfStatement n) throws SemanticException {
		typeCheck(n.condition);
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
		functions.get(n.id.name).add(nowFunction);
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
		typeCheck(n.condition);
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
		typeCheck(n.value);
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
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NArrayCall n) throws SemanticException {
		typeCheck(n.exp);
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
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NEqual n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NExpMethodCall n) throws SemanticException {
		functions.get(n.id.name).add(nowFunction);
		return null;
	}

	@Override
	public Object visit(NFloatNum n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NGreaterEqual n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NGreaterThan n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
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
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NLessThan n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NMinus n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NMod n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NMult n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NNot n) throws SemanticException {
		typeCheck(n.l);
		return null;
	}

	@Override
	public Object visit(NOr n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NPlus n) throws SemanticException {
		typeCheck(n.l);
		typeCheck(n.r);
		return null;
	}

	@Override
	public Object visit(NString n) throws SemanticException {
		return null;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		for (NExp e : n.values.values()) {
			typeCheck(e);
		}
		return null;
	}

	private void typeCheck(NExp exp) throws SemanticException {
		if (exp instanceof NAnd) {
			visit((NAnd) exp);
		} else if (exp instanceof NArrayCall) {
			visit((NArrayCall) exp);
		} else if (exp instanceof NBoolean) {
			visit((NBoolean) exp);
		} else if (exp instanceof NChar) {
			visit((NChar) exp);
		} else if (exp instanceof NDiv) {
			visit((NDiv) exp);
		} else if (exp instanceof NEqual) {
			visit((NEqual) exp);
		} else if (exp instanceof NExpMethodCall) {
			visit((NExpMethodCall) exp);
		} else if (exp instanceof NFloatNum) {
			visit((NFloatNum) exp);
		} else if (exp instanceof NGreaterEqual) {
			visit((NGreaterEqual) exp);
		} else if (exp instanceof NGreaterThan) {
			visit((NGreaterThan) exp);
		} else if (exp instanceof NIdentifier) {
			visit((NIdentifier) exp);
		} else if (exp instanceof NIntNum) {
			visit((NIntNum) exp);
		} else if (exp instanceof NLessEqual) {
			visit((NLessEqual) exp);
		} else if (exp instanceof NLessThan) {
			visit((NLessThan) exp);
		} else if (exp instanceof NMinus) {
			visit((NMinus) exp);
		} else if (exp instanceof NMod) {
			visit((NMod) exp);
		} else if (exp instanceof NMult) {
			visit((NMult) exp);
		} else if (exp instanceof NNot) {
			visit((NNot) exp);
		} else if (exp instanceof NOr) {
			visit((NOr) exp);
		} else if (exp instanceof NPlus) {
			visit((NPlus) exp);
		} else if (exp instanceof NArrayAttribution) {
			visit((NArrayAttribution) exp);
		} else {
			visit((NString) exp);
		}
	}

}
