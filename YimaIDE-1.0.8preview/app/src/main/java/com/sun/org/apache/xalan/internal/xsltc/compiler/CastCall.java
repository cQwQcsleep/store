package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ObjectType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CastCall extends FunctionCall {
    private String _className;
    private Expression _right;

    public CastCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._right.translate(classGenerator, methodGenerator);
        instructionList.append(new CHECKCAST(constantPool.addClass(this._className)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (argumentCount() != 2) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, (Object) getName(), (SyntaxTreeNode) this));
        }
        Expression expressionArgument = argument(0);
        if (!(expressionArgument instanceof LiteralExpr)) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR, (Object) getName(), (SyntaxTreeNode) this));
        }
        String value = ((LiteralExpr) expressionArgument).getValue();
        this._className = value;
        this._type = Type.newObjectType(value);
        Expression expressionArgument2 = argument(1);
        this._right = expressionArgument2;
        Type typeTypeCheck = expressionArgument2.typeCheck(symbolTable);
        if (typeTypeCheck == Type.Reference || (typeTypeCheck instanceof ObjectType)) {
            return this._type;
        }
        throw new TypeCheckError(new ErrorMsg("DATA_CONVERSION_ERR", typeTypeCheck, this._type, this));
    }
}
