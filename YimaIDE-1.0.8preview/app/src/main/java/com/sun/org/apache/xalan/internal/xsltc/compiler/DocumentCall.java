package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DocumentCall extends FunctionCall {
    private Expression _arg1;
    private Type _arg1Type;
    private Expression _arg2;

    public DocumentCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._arg1 = null;
        this._arg2 = null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iArgumentCount = argumentCount();
        int iAddFieldref = constantPool.addFieldref(classGenerator.getClassName(), Constants.DOM_FIELD, Constants.DOM_INTF_SIG);
        int iAddMethodref = constantPool.addMethodref(Constants.LOAD_DOCUMENT_CLASS, "documentF", iArgumentCount == 1 ? "(Ljava/lang/Object;Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;" : "(Ljava/lang/Object;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        this._arg1.translate(classGenerator, methodGenerator);
        if (this._arg1Type == Type.NodeSet) {
            this._arg1.startIterator(classGenerator, methodGenerator);
        }
        if (iArgumentCount == 2) {
            this._arg2.translate(classGenerator, methodGenerator);
            this._arg2.startIterator(classGenerator, methodGenerator);
        }
        instructionList.append(new PUSH(constantPool, getStylesheet().getSystemId()));
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(Constants.DUP);
        instructionList.append(new GETFIELD(iAddFieldref));
        instructionList.append(new INVOKESTATIC(iAddMethodref));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type;
        int iArgumentCount = argumentCount();
        if (iArgumentCount < 1 || iArgumentCount > 2) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, (SyntaxTreeNode) this));
        }
        if (getStylesheet() == null) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, (SyntaxTreeNode) this));
        }
        Expression expressionArgument = argument(0);
        this._arg1 = expressionArgument;
        if (expressionArgument == null) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, (SyntaxTreeNode) this));
        }
        Type typeTypeCheck = expressionArgument.typeCheck(symbolTable);
        this._arg1Type = typeTypeCheck;
        Type type2 = Type.NodeSet;
        if (typeTypeCheck != type2 && typeTypeCheck != (type = Type.String)) {
            this._arg1 = new CastExpr(this._arg1, type);
        }
        if (iArgumentCount == 2) {
            Expression expressionArgument2 = argument(1);
            this._arg2 = expressionArgument2;
            if (expressionArgument2 == null) {
                throw new TypeCheckError(new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, (SyntaxTreeNode) this));
            }
            Type typeTypeCheck2 = expressionArgument2.typeCheck(symbolTable);
            if (typeTypeCheck2.identicalTo(Type.Node)) {
                this._arg2 = new CastExpr(this._arg2, type2);
            } else if (!typeTypeCheck2.identicalTo(type2)) {
                throw new TypeCheckError(new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, (SyntaxTreeNode) this));
            }
        }
        this._type = type2;
        return type2;
    }
}
