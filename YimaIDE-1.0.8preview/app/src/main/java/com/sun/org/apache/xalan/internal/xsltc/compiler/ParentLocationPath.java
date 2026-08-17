package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ParentLocationPath extends RelativeLocationPath {
    private boolean _axisMismatch;
    private boolean _orderNodes = false;
    private final RelativeLocationPath _path;
    private Expression _step;
    private Type stype;

    public ParentLocationPath(RelativeLocationPath relativeLocationPath, Expression expression) {
        this._axisMismatch = false;
        this._path = relativeLocationPath;
        this._step = expression;
        relativeLocationPath.setParent(this);
        this._step.setParent(this);
        if (this._step instanceof Step) {
            this._axisMismatch = checkAxisMismatch();
        }
    }

    public boolean checkAxisMismatch() {
        int axis = this._path.getAxis();
        int axis2 = ((Step) this._step).getAxis();
        if (((axis == 0 || axis == 1) && (axis2 == 3 || axis2 == 4 || axis2 == 5 || axis2 == 10 || axis2 == 11 || axis2 == 12)) || ((axis == 3 && axis2 == 0) || axis2 == 1 || axis2 == 10 || axis2 == 11 || axis == 4 || axis == 5 || (((axis == 6 || axis == 7) && (axis2 == 6 || axis2 == 10 || axis2 == 11 || axis2 == 12)) || ((axis == 11 || axis == 12) && (axis2 == 4 || axis2 == 5 || axis2 == 6 || axis2 == 7 || axis2 == 10 || axis2 == 11 || axis2 == 12))))) {
            return true;
        }
        if (axis2 != 6 || axis != 3) {
            return false;
        }
        RelativeLocationPath relativeLocationPath = this._path;
        return (relativeLocationPath instanceof Step) && ((Step) relativeLocationPath).getNodeType() == 2;
    }

    public void enableNodeOrdering() {
        SyntaxTreeNode parent = getParent();
        if (parent instanceof ParentLocationPath) {
            ((ParentLocationPath) parent).enableNodeOrdering();
        } else {
            this._orderNodes = true;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.RelativeLocationPath
    public int getAxis() {
        return this._path.getAxis();
    }

    public RelativeLocationPath getPath() {
        return this._path;
    }

    public Expression getStep() {
        return this._step;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.RelativeLocationPath
    public void setAxis(int i) {
        this._path.setAxis(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._step.setParser(parser);
        this._path.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "ParentLocationPath(" + this._path + ", " + this._step + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._path.translate(classGenerator, methodGenerator);
        translateStep(classGenerator, methodGenerator);
    }

    public void translateStep(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("parent_location_path_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        this._step.translate(classGenerator, methodGenerator);
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("parent_location_path_tmp2", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
        int iAddMethodref = constantPool.addMethodref(Constants.STEP_ITERATOR_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;)V");
        instructionList.append(new NEW(constantPool.addClass(Constants.STEP_ITERATOR_CLASS)));
        instructionList.append(Constants.DUP);
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
        localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        Expression step = this._step;
        if (step instanceof ParentLocationPath) {
            step = ((ParentLocationPath) step).getStep();
        }
        RelativeLocationPath relativeLocationPath = this._path;
        if ((relativeLocationPath instanceof Step) && (step instanceof Step)) {
            int axis = ((Step) relativeLocationPath).getAxis();
            int axis2 = ((Step) step).getAxis();
            if ((axis == 5 && axis2 == 3) || (axis == 11 && axis2 == 10)) {
                instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_ITERATOR_BASE, "includeSelf", "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;")));
            }
        }
        if (this._orderNodes) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.ORDER_ITERATOR, Constants.ORDER_ITERATOR_SIG);
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(Constants.SWAP);
            instructionList.append(methodGenerator.loadContextNode());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 3));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this.stype = this._step.typeCheck(symbolTable);
        this._path.typeCheck(symbolTable);
        if (this._axisMismatch) {
            enableNodeOrdering();
        }
        Type type = Type.NodeSet;
        this._type = type;
        return type;
    }
}
