package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class StringCall extends FunctionCall {
    public StringCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Type type;
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (argumentCount() == 0) {
            instructionList.append(methodGenerator.loadContextNode());
            type = Type.Node;
        } else {
            Expression expressionArgument = argument();
            expressionArgument.translate(classGenerator, methodGenerator);
            expressionArgument.startIterator(classGenerator, methodGenerator);
            type = expressionArgument.getType();
        }
        Type type2 = Type.String;
        if (type.identicalTo(type2)) {
            return;
        }
        type.translateTo(classGenerator, methodGenerator, type2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        int iArgumentCount = argumentCount();
        if (iArgumentCount > 1) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, (SyntaxTreeNode) this));
        }
        if (iArgumentCount > 0) {
            argument().typeCheck(symbolTable);
        }
        Type type = Type.String;
        this._type = type;
        return type;
    }
}
