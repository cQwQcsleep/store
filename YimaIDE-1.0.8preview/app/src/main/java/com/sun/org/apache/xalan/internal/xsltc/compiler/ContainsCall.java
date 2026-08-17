package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ContainsCall extends FunctionCall {
    private Expression _base;
    private Expression _token;

    public ContainsCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._base = null;
        this._token = null;
    }

    public boolean isBoolean() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateDesynthesized(classGenerator, methodGenerator);
        synthesize(classGenerator, methodGenerator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._base.translate(classGenerator, methodGenerator);
        this._token.translate(classGenerator, methodGenerator);
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref("java.lang.String", "indexOf", Constants.STRING_TO_INT_SIG)));
        this._falseList.add(instructionList.append((BranchInstruction) new IFLT(null)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (argumentCount() != 2) {
            throw new TypeCheckError(ErrorMsg.ILLEGAL_ARG_ERR, getName(), this);
        }
        Expression expressionArgument = argument(0);
        this._base = expressionArgument;
        Type typeTypeCheck = expressionArgument.typeCheck(symbolTable);
        Type type = Type.String;
        if (typeTypeCheck != type) {
            this._base = new CastExpr(this._base, type);
        }
        Expression expressionArgument2 = argument(1);
        this._token = expressionArgument2;
        if (expressionArgument2.typeCheck(symbolTable) != type) {
            this._token = new CastExpr(this._token, type);
        }
        Type type2 = Type.Boolean;
        this._type = type2;
        return type2;
    }
}
