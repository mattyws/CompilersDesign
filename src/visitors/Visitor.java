package visitors;

import treeNodes.NExpParam;
import treeNodes.NFunctionDecl;
import treeNodes.NMainFunc;
import treeNodes.NParameter;
import treeNodes.NProgram;
import treeNodes.NVariable;
import treeNodes.SemanticException;
import treeNodes.exp.*;
import treeNodes.statements.*;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public abstract class Visitor {
	
	public abstract Object visit(NProgram n) throws SemanticException;
	public abstract Object visit(NFunctionDecl n) throws SemanticException;
	public abstract Object visit(NExpParam n) throws SemanticException;
	public abstract Object visit(NMainFunc n) throws SemanticException;
	public abstract Object visit(NParameter n) throws SemanticException;
	public abstract Object visit(NVariable n) throws SemanticException;
	public abstract Object visit(NAssign n) throws SemanticException;
	public abstract Object visit(NElseStatement n) throws SemanticException;
	public abstract Object visit(NReturnStatement n) throws SemanticException;
	public abstract Object visit(NIfStatement n) throws SemanticException;
	public abstract Object visit(NMethodCall n) throws SemanticException;
	public abstract Object visit(NScopedStatement n) throws SemanticException;
	public abstract Object visit(NVarDeclStatement n) throws SemanticException;
	public abstract Object visit(NWhileStatement n) throws SemanticException;
	public abstract Object visit(NWhenStatement n) throws SemanticException;
	public abstract Object visit(NAnd n) throws SemanticException;
	public abstract Object visit(NArrayCall n) throws SemanticException;
	public abstract Object visit(NBoolean n) throws SemanticException;
	public abstract Object visit(NChar n) throws SemanticException;
	public abstract Object visit(NDiv n) throws SemanticException;
	public abstract Object visit(NEqual n) throws SemanticException;
	public abstract Object visit(NExpMethodCall n) throws SemanticException;
	public abstract Object visit(NFloatNum n) throws SemanticException;
	public abstract Object visit(NGreaterEqual n) throws SemanticException;
	public abstract Object visit(NGreaterThan n) throws SemanticException;
	public abstract Object visit(NIdentifier n) throws SemanticException;
	public abstract Object visit(NIntNum n) throws SemanticException;
	public abstract Object visit(NLessEqual n) throws SemanticException;
	public abstract Object visit(NLessThan n) throws SemanticException;
	public abstract Object visit(NMinus n) throws SemanticException;
	public abstract Object visit(NMod n) throws SemanticException;
	public abstract Object visit(NMult n) throws SemanticException;
	public abstract Object visit(NNot n) throws SemanticException;
	public abstract Object visit(NOr n) throws SemanticException;
	public abstract Object visit(NPlus n) throws SemanticException;
	public abstract Object visit(NString n) throws SemanticException;
	public abstract Object visit(NArrayAttribution n) throws SemanticException;

}
