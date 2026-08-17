package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.dtm.Axis;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class UnionPathExpr extends Expression {
    private Expression[] _components;
    private final Expression _pathExpr;
    private final Expression _rest;
    private boolean _reverse = false;

    public UnionPathExpr(Expression expression, Expression expression2) {
        this._pathExpr = expression;
        this._rest = expression2;
    }

    private void flatten(List<Expression> list) {
        list.add(this._pathExpr);
        Expression expression = this._rest;
        if (expression != null) {
            if (expression instanceof UnionPathExpr) {
                ((UnionPathExpr) expression).flatten(list);
            } else {
                list.add(expression);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        ArrayList arrayList = new ArrayList();
        flatten(arrayList);
        int size = arrayList.size();
        this._components = (Expression[]) arrayList.toArray(new Expression[size]);
        for (int i = 0; i < size; i++) {
            this._components[i].setParser(parser);
            this._components[i].setParent(this);
            Expression expression = this._components[i];
            if (expression instanceof Step) {
                Step step = (Step) expression;
                int axis = step.getAxis();
                int nodeType = step.getNodeType();
                if (axis == 2 || nodeType == 2) {
                    Expression[] expressionArr = this._components;
                    expressionArr[i] = expressionArr[0];
                    expressionArr[0] = step;
                }
                if (Axis.isReverse(axis)) {
                    this._reverse = true;
                }
            }
        }
        if (getParent() instanceof Expression) {
            this._reverse = false;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "union(" + this._pathExpr + ", " + this._rest + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.UNION_ITERATOR_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)V");
        int iAddMethodref2 = constantPool.addMethodref(Constants.UNION_ITERATOR_CLASS, Constants.ADD_ITERATOR, Constants.ADD_ITERATOR_SIG);
        instructionList.append(new NEW(constantPool.addClass(Constants.UNION_ITERATOR_CLASS)));
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        int length = this._components.length;
        for (int i = 0; i < length; i++) {
            this._components[i].translate(classGenerator, methodGenerator);
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
        }
        if (this._reverse) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.ORDER_ITERATOR, Constants.ORDER_ITERATOR_SIG);
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(Constants.SWAP);
            instructionList.append(methodGenerator.loadContextNode());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 3));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        int length = this._components.length;
        for (int i = 0; i < length; i++) {
            Type typeTypeCheck = this._components[i].typeCheck(symbolTable);
            Type type = Type.NodeSet;
            if (typeTypeCheck != type) {
                this._components[i] = new CastExpr(this._components[i], type);
            }
        }
        Type type2 = Type.NodeSet;
        this._type = type2;
        return type2;
    }
}
