package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class UnaryOpExpr extends Expression {
    private Expression _left;

    public UnaryOpExpr(Expression expression) {
        this._left = expression;
        expression.setParent(this);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return this._left.hasLastCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return this._left.hasPositionCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "u-(" + this._left + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._left.translate(classGenerator, methodGenerator);
        instructionList.append(this._type.NEG());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._left.typeCheck(symbolTable);
        MethodType methodTypeLookupPrimop = lookupPrimop(symbolTable, "u-", new MethodType(Type.Void, typeTypeCheck));
        if (methodTypeLookupPrimop == null) {
            throw new TypeCheckError(this);
        }
        Type type = methodTypeLookupPrimop.argsType().get(0);
        if (!type.identicalTo(typeTypeCheck)) {
            this._left = new CastExpr(this._left, type);
        }
        Type typeResultType = methodTypeLookupPrimop.resultType();
        this._type = typeResultType;
        return typeResultType;
    }
}
