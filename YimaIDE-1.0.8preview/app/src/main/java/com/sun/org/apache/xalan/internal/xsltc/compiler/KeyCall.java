package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class KeyCall extends FunctionCall {
    private Expression _name;
    private QName _resolvedQName;
    private Expression _value;
    private Type _valueType;

    public KeyCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._resolvedQName = null;
        int iArgumentCount = argumentCount();
        if (iArgumentCount == 1) {
            this._name = null;
            this._value = argument(0);
        } else if (iArgumentCount != 2) {
            this._value = null;
            this._name = null;
        } else {
            this._name = argument(0);
            this._value = argument(1);
        }
    }

    public void addParentDependency() {
        if (this._resolvedQName == null) {
            return;
        }
        SyntaxTreeNode parent = this;
        while (parent != null && !(parent instanceof TopLevelElement)) {
            parent = parent.getParent();
        }
        TopLevelElement topLevelElement = (TopLevelElement) parent;
        if (topLevelElement != null) {
            topLevelElement.addDependency(getSymbolTable().getKey(this._resolvedQName));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, "getKeyIndex", "(Ljava/lang/String;)Lcom/sun/org/apache/xalan/internal/xsltc/dom/KeyIndex;");
        int iAddMethodref2 = constantPool.addMethodref(Constants.KEY_INDEX_CLASS, "setDom", "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;I)V");
        int iAddMethodref3 = constantPool.addMethodref(Constants.KEY_INDEX_CLASS, "getKeyIndexIterator", "(" + this._valueType.toSignature() + "Z)Lcom/sun/org/apache/xalan/internal/xsltc/dom/KeyIndex$KeyIndexIterator;");
        instructionList.append(classGenerator.loadTranslet());
        Expression expression = this._name;
        if (expression == null) {
            instructionList.append(new PUSH(constantPool, "##id"));
        } else {
            QName qName = this._resolvedQName;
            if (qName != null) {
                instructionList.append(new PUSH(constantPool, qName.toString()));
            } else {
                expression.translate(classGenerator, methodGenerator);
            }
        }
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
        this._value.translate(classGenerator, methodGenerator);
        instructionList.append(this._name != null ? Constants.ICONST_1 : Constants.ICONST_0);
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref3));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type;
        Type typeTypeCheck = super.typeCheck(symbolTable);
        Expression expression = this._name;
        if (expression != null) {
            Type typeTypeCheck2 = expression.typeCheck(symbolTable);
            Expression expression2 = this._name;
            if (expression2 instanceof LiteralExpr) {
                this._resolvedQName = getParser().getQNameIgnoreDefaultNs(((LiteralExpr) expression2).getValue());
            } else if (!(typeTypeCheck2 instanceof StringType)) {
                this._name = new CastExpr(this._name, Type.String);
            }
        }
        Type typeTypeCheck3 = this._value.typeCheck(symbolTable);
        this._valueType = typeTypeCheck3;
        if (typeTypeCheck3 != Type.NodeSet && typeTypeCheck3 != Type.Reference && typeTypeCheck3 != (type = Type.String)) {
            CastExpr castExpr = new CastExpr(this._value, type);
            this._value = castExpr;
            this._valueType = castExpr.typeCheck(symbolTable);
        }
        addParentDependency();
        return typeTypeCheck;
    }
}
