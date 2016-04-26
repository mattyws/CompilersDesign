package visitors;

import java.util.ArrayList;
import java.util.List;

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

public class TypeChecker extends Visitor {

	Table scopeTable, funcTable, progTable;
	int i = 0;

	@Override
	public Object visit(NProgram n) throws SemanticException {
		progTable = n.getTab();
		for (NFunctionDecl decl : n.functionDeclList) {
			visit(decl);
		}
		visit(n.main);
		return null;
	}

	@Override
	public Object visit(NFunctionDecl n) throws SemanticException {
		funcTable = n.getTab();
		for (NStatement st : n.body) {
			if (st instanceof NAssign) {
				visit((NAssign) st);
			} else if (st instanceof NReturnStatement) {
				NType type = (NType) visit((NReturnStatement) st);
				if (!n.type.equals(type)) {
					throw new SemanticException("Return type mismatch on " + n.name.name);
				}
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
		List<NType> expParamTypes = new ArrayList<>();
		if (n != null) {
			for (NExp exp : n.expressionParam) {
				NType type = typeCheck(exp);
				expParamTypes.add(type);
			}
		}
		return expParamTypes;
	}

	@Override
	public Object visit(NMainFunc n) throws SemanticException {
		funcTable = n.getTab();
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
		NType identifierType;
		if (scopeTable == null) {
			if (!funcTable.containsKey(n.id.name)) {
				throw new SemanticException("Variable do not exists!");
			} else {
				identifierType = funcTable.get(n.id.name).type;
			}
		} else {
			if (!scopeTable.containsKey(n.id.name)) {
				if (!funcTable.containsKey(n.id.name)) {
					throw new SemanticException("Variable do not exists!");
				}
				identifierType = funcTable.get(n.id.name).type;
			} else {
				identifierType = scopeTable.get(n.id.name).type;
			}

		}
		NType type = typeCheck(n.exp);
		if (!(identifierType instanceof NCharType && type instanceof NIntType)
				&& !(identifierType instanceof NFloatType && type instanceof NIntType)
				&& !(identifierType instanceof NArrayFloatType && type instanceof NArrayIntType)
				&& !identifierType.equals(type)) {
			throw new SemanticException("Assigning " + type + " to a variable " + identifierType);
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
	public Object visit(NIfStatement n) throws SemanticException {
		NType conditionType = typeCheck(n.condition);
		if (conditionType instanceof NBooleanType) {
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
		} else {
			throw new SemanticException("Condition must be boolean.");
		}
		return null;
	}

	@Override
	public Object visit(NMethodCall n) throws SemanticException {
		if (!progTable.containsKey(n.id.name)) {
			throw new SemanticException("Method " + n.id.name + " does not exist!");
		} else {
			List<NType> callParamTypes = (List<NType>) visit(n.parameters);
			Binding b = progTable.get(n.id.name);
			List<NType> methodParamTypes = new ArrayList<>();
			if (b != null && b.params != null) {
				for (NParameter param : b.params) {
					methodParamTypes.add(param.type);
				}
			}
			if (callParamTypes.size() == methodParamTypes.size()) {
				for (int i = 0; i < callParamTypes.size(); i++) {
					if (!callParamTypes.get(i).equals(methodParamTypes.get(i))) {
						throw new SemanticException("Expecting " + methodParamTypes.get(i) + " found "
								+ callParamTypes.get(i) + " on " + n.id.name);
					}
				}
				return b.type;
			} else {
				String message = "Method " + n.id.name + " with parameters ";
				for (NType t : callParamTypes) {
					message += t.toString() + ", ";
				}
				message += "does not exist";
				throw new SemanticException(message);
			}
		}
	}

	@Override
	public Object visit(NScopedStatement n) throws SemanticException {
		scopeTable = n.getTab();
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
		scopeTable = null;
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
		NType conditionType = typeCheck(n.condition);
		if (conditionType instanceof NBooleanType) {
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
		} else {
			throw new SemanticException("Condition must be boolean.");
		}
		return null;
	}

	@Override
	public Object visit(NAnd n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NBooleanType) || !(rType instanceof NBooleanType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NArrayCall n) throws SemanticException {
		NType identifierType;
		if (scopeTable == null) {
			if (!funcTable.containsKey(n.id.name)) {
				throw new SemanticException("Variable do not exists!");
			} else {
				identifierType = funcTable.get(n.id.name).type;
			}
		} else {
			if (!scopeTable.containsKey(n.id.name)) {
				if (!funcTable.containsKey(n.id.name)) {
					throw new SemanticException("Variable do not exists!");
				}
				identifierType = funcTable.get(n.id.name).type;
			} else {
				identifierType = scopeTable.get(n.id.name).type;
			}
		}
		NType expType = typeCheck(n.exp);
		if (!(expType instanceof NIntType)) {
			throw new SemanticException("Type mismatch: must be an integer to access an array.");
		}
		if (identifierType instanceof NArrayIntType) {
			return new NIntType();
		} else if (identifierType instanceof NArrayStringType) {
			return new NStringType();
		} else if (identifierType instanceof NArrayCharType) {
			return new NCharType();
		} else if (identifierType instanceof NArrayFloatType) {
			return new NFloatType();
		} else if (identifierType instanceof NArrayBooleanType) {
			return new NBooleanType();
		} else {
			throw new SemanticException("Variable is not an array.");
		}
	}

	@Override
	public Object visit(NBoolean n) throws SemanticException {
		return new NBooleanType();
	}

	@Override
	public Object visit(NChar n) throws SemanticException {
		return new NCharType();
	}

	@Override
	public Object visit(NDiv n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		return new NFloatType();
	}

	@Override
	public Object visit(NEqual n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)
				&& !(lType instanceof NStringType) && !(lType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)
				&& !(rType instanceof NStringType) && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if ((rType instanceof NStringType && !(lType instanceof NStringType))
				|| lType instanceof NStringType && !(rType instanceof NStringType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		if ((rType instanceof NBooleanType && !(lType instanceof NBooleanType))
				|| lType instanceof NBooleanType && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NExpMethodCall n) throws SemanticException {
		if (!progTable.containsKey(n.id.name)) {
			throw new SemanticException("Method " + n.id.name + " does not exist!");
		}
		List<NType> callParamTypes = new ArrayList<>();
		if (n.parameters != null) {
			callParamTypes = (List<NType>) visit(n.parameters);
		}
		Binding b = progTable.get(n.id.name);
		List<NType> methodParamTypes = new ArrayList<>();
		if (b.params != null) {
			for (NParameter param : b.params) {
				methodParamTypes.add(param.type);
			}
		}
		if (callParamTypes.size() == methodParamTypes.size()) {
			for (int i = 0; i < callParamTypes.size(); i++) {
				if (!callParamTypes.get(i).equals(methodParamTypes.get(i))) {
					throw new SemanticException("Expecting " + methodParamTypes.get(i) + " found "
							+ callParamTypes.get(i) + " on " + n.id.name);
				}
			}
			return b.type;
		} else {
			String message = "Method " + n.id.name + " with parameters ";
			for (NType t : callParamTypes) {
				message += t.toString() + ", ";
			}
			message += "does not exist";
			throw new SemanticException(message);
		}

	}

	@Override
	public Object visit(NFloatNum n) throws SemanticException {
		return new NFloatType();
	}

	@Override
	public Object visit(NGreaterEqual n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)
				&& !(lType instanceof NStringType) && !(lType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)
				&& !(rType instanceof NStringType) && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if ((rType instanceof NStringType && !(lType instanceof NStringType))
				|| lType instanceof NStringType && !(rType instanceof NStringType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		if ((rType instanceof NBooleanType && !(lType instanceof NBooleanType))
				|| lType instanceof NBooleanType && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NGreaterThan n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)
				&& !(lType instanceof NStringType) && !(lType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)
				&& !(rType instanceof NStringType) && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if ((rType instanceof NStringType && !(lType instanceof NStringType))
				|| lType instanceof NStringType && !(rType instanceof NStringType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		if ((rType instanceof NBooleanType && !(lType instanceof NBooleanType))
				|| lType instanceof NBooleanType && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NIdentifier n) throws SemanticException {
		NType identifierType;
		if (scopeTable == null) {
			if (!funcTable.containsKey(n.name)) {
				throw new SemanticException("Variable " + n.name + " do not exists!");
			} else {
				identifierType = funcTable.get(n.name).type;
			}
		} else {
			if (!scopeTable.containsKey(n.name)) {
				if (!funcTable.containsKey(n.name)) {
					throw new SemanticException("Variable do not exists!");
				}
				identifierType = funcTable.get(n.name).type;
			} else {
				identifierType = scopeTable.get(n.name).type;
			}
		}
		return identifierType;
	}

	@Override
	public Object visit(NIntNum n) throws SemanticException {
		return new NIntType();
	}

	@Override
	public Object visit(NLessEqual n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)
				&& !(lType instanceof NStringType) && !(lType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)
				&& !(rType instanceof NStringType) && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if ((rType instanceof NStringType && !(lType instanceof NStringType))
				|| lType instanceof NStringType && !(rType instanceof NStringType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		if ((rType instanceof NBooleanType && !(lType instanceof NBooleanType))
				|| lType instanceof NBooleanType && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NLessThan n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)
				&& !(lType instanceof NStringType) && !(lType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)
				&& !(rType instanceof NStringType) && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch");
		}
		if ((rType instanceof NStringType && !(lType instanceof NStringType))
				|| lType instanceof NStringType && !(rType instanceof NStringType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		if ((rType instanceof NBooleanType && !(lType instanceof NBooleanType))
				|| lType instanceof NBooleanType && !(rType instanceof NBooleanType)) {
			throw new SemanticException("Type mismatch : can't compare " + lType + " with " + rType);
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NMinus n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if ((rType instanceof NCharType && lType instanceof NFloatType)
				|| (lType instanceof NCharType && rType instanceof NFloatType)) {
			throw new SemanticException("Type mismatch : can't operate " + lType + " with " + rType);
		}
		if (lType instanceof NFloatType || rType instanceof NFloatType) {
			return new NFloatType();
		} else if (lType instanceof NCharType || rType instanceof NCharType) {
			return new NCharType();
		} else {
			return new NIntType();
		}
	}

	@Override
	public Object visit(NMod n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType)) {
			throw new SemanticException("Mod is a numeric operation.");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType)) {
			throw new SemanticException("Mod is a numeric operation.");
		}
		return new NIntType();
	}

	@Override
	public Object visit(NMult n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if ((rType instanceof NCharType && lType instanceof NFloatType)
				|| (lType instanceof NCharType && rType instanceof NFloatType)) {
			throw new SemanticException("Type mismatch : can't operate " + lType + " with " + rType);
		}
		if (lType instanceof NFloatType || rType instanceof NFloatType) {
			return new NFloatType();
		} else if (lType instanceof NCharType || rType instanceof NCharType) {
			return new NCharType();
		} else {
			return new NIntType();
		}
	}

	@Override
	public Object visit(NNot n) throws SemanticException {
		NType lType = typeCheck(n.l);
		if (!(lType instanceof NBooleanType)) {
			throw new SemanticException("Expecting boolean for a ! operation");
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NOr n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NBooleanType) || !(rType instanceof NBooleanType)) {
			throw new SemanticException("Expecting boolean for a | operation");
		}
		return new NBooleanType();
	}

	@Override
	public Object visit(NPlus n) throws SemanticException {
		NType lType = typeCheck(n.l), rType = typeCheck(n.r);
		if (!(lType instanceof NIntType) && !(lType instanceof NFloatType) && !(lType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if (!(rType instanceof NIntType) && !(rType instanceof NFloatType) && !(rType instanceof NCharType)) {
			throw new SemanticException("Expecting boolean for a & operation");
		}
		if ((rType instanceof NCharType && lType instanceof NFloatType)
				|| (lType instanceof NCharType && rType instanceof NFloatType)) {
			throw new SemanticException("Type mismatch : can't operate " + lType + " with " + rType);
		}
		if (lType instanceof NFloatType || rType instanceof NFloatType) {
			return new NFloatType();
		} else if (lType instanceof NCharType || rType instanceof NCharType) {
			return new NCharType();
		} else {
			return new NIntType();
		}
	}

	@Override
	public Object visit(NString n) throws SemanticException {
		return new NStringType();
	}

	/**
	 * Return the type of the expression
	 * 
	 * @param exp
	 * @return
	 * @throws SemanticException
	 */
	private NType typeCheck(NExp exp) throws SemanticException {
		NType type;
		if (exp instanceof NAnd) {
			type = (NType) visit((NAnd) exp);
		} else if (exp instanceof NArrayCall) {
			type = (NType) visit((NArrayCall) exp);
		} else if (exp instanceof NBoolean) {
			type = (NType) visit((NBoolean) exp);
		} else if (exp instanceof NChar) {
			type = (NType) visit((NChar) exp);
		} else if (exp instanceof NDiv) {
			type = (NType) visit((NDiv) exp);
		} else if (exp instanceof NEqual) {
			type = (NType) visit((NEqual) exp);
		} else if (exp instanceof NExpMethodCall) {
			type = (NType) visit((NExpMethodCall) exp);
		} else if (exp instanceof NFloatNum) {
			type = (NType) visit((NFloatNum) exp);
		} else if (exp instanceof NGreaterEqual) {
			type = (NType) visit((NGreaterEqual) exp);
		} else if (exp instanceof NGreaterThan) {
			type = (NType) visit((NGreaterThan) exp);
		} else if (exp instanceof NIdentifier) {
			type = (NType) visit((NIdentifier) exp);
		} else if (exp instanceof NIntNum) {
			type = (NType) visit((NIntNum) exp);
		} else if (exp instanceof NLessEqual) {
			type = (NType) visit((NLessEqual) exp);
		} else if (exp instanceof NLessThan) {
			type = (NType) visit((NLessThan) exp);
		} else if (exp instanceof NMinus) {
			type = (NType) visit((NMinus) exp);
		} else if (exp instanceof NMod) {
			type = (NType) visit((NMod) exp);
		} else if (exp instanceof NMult) {
			type = (NType) visit((NMult) exp);
		} else if (exp instanceof NNot) {
			type = (NType) visit((NNot) exp);
		} else if (exp instanceof NOr) {
			type = (NType) visit((NOr) exp);
		} else if (exp instanceof NPlus) {
			type = (NType) visit((NPlus) exp);
		} else if (exp instanceof NArrayAttribution) {
			type = (NType) visit((NArrayAttribution) exp);
		} else {
			type = (NType) visit((NString) exp);
		}
		return type;
	}

	@Override
	public Object visit(NReturnStatement n) throws SemanticException {
		NType type = typeCheck(n.returnExp);
		return type;
	}

	@Override
	public Object visit(NArrayAttribution n) throws SemanticException {
		List<NType> types = new ArrayList<>();
		for (NIntNum i : n.values.keySet()) {
			types.add(typeCheck(n.values.get(i)));
		}
		NType t = types.get(0);
		for (int i = 1; i < types.size(); i++) {
			if (t instanceof NIntType && types.get(i) instanceof NFloatType) {
				t = types.get(i);
			}
			if (!types.get(i).equals(t) && !(t instanceof NIntType && types.get(i) instanceof NFloatType
					|| types.get(i) instanceof NIntType && t instanceof NFloatType)) {
				throw new SemanticException("Types on array declaration are different." + t + " " + types.get(i));
			}
		}
		if (t instanceof NIntType) {
			return new NArrayIntType();
		} else if (t instanceof NStringType) {
			return new NArrayStringType();
		} else if (t instanceof NCharType) {
			return new NArrayCharType();
		} else if (t instanceof NFloatType) {
			return new NArrayFloatType();
		} else {
			return new NArrayBooleanType();
		}
	}

	@Override
	public Object visit(NWhenStatement n) throws SemanticException {
		NType identifierType;
		if (scopeTable == null) {
			if (!funcTable.containsKey(n.id.name)) {
				throw new SemanticException("Variable do not exists!");
			} else {
				identifierType = funcTable.get(n.id.name).type;
			}
		} else {
			if (!scopeTable.containsKey(n.id.name)) {
				if (!funcTable.containsKey(n.id.name)) {
					throw new SemanticException("Variable do not exists!");
				}
				identifierType = funcTable.get(n.id.name).type;
			} else {
				identifierType = scopeTable.get(n.id.name).type;
			}
		}
		if (!(identifierType instanceof NArrayIntType) && !(identifierType instanceof NArrayFloatType)
				&& !(identifierType instanceof NArrayBooleanType) && !(identifierType instanceof NArrayCharType)
				&& !(identifierType instanceof NArrayStringType)) {
			throw new SemanticException("When command variable must be an array.");
		}
		NType valueType = typeCheck(n.value);
		if ((identifierType instanceof NArrayIntType) && !(valueType instanceof NIntType)
				|| (identifierType instanceof NArrayFloatType) && !(valueType instanceof NFloatType)
				|| (identifierType instanceof NArrayBooleanType) && !(valueType instanceof NBooleanType)
				|| (identifierType instanceof NArrayCharType) && !(valueType instanceof NCharType)
				|| (identifierType instanceof NArrayStringType) && !(valueType instanceof NStringType)) {
			throw new SemanticException("When command value must be the same primitive type as the array.");
		}
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

}
