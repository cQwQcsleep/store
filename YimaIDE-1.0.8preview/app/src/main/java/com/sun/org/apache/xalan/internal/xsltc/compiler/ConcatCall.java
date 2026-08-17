package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ConcatCall extends FunctionCall {
    public ConcatCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iArgumentCount = argumentCount();
        if (iArgumentCount == 0) {
            instructionList.append(new PUSH(constantPool, ""));
            return;
        }
        if (iArgumentCount == 1) {
            argument().translate(classGenerator, methodGenerator);
            return;
        }
        int iAddMethodref = constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, Const.CONSTRUCTOR_NAME, "()V");
        INVOKEVIRTUAL invokevirtual = new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
        int iAddMethodref2 = constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, "toString", "()Ljava/lang/String;");
        instructionList.append(new NEW(constantPool.addClass(Constants.STRING_BUFFER_CLASS)));
        instructionList.append(Constants.DUP);
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        for (int i = 0; i < iArgumentCount; i++) {
            argument(i).translate(classGenerator, methodGenerator);
            instructionList.append(invokevirtual);
        }
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        for (int i = 0; i < argumentCount(); i++) {
            Expression expressionArgument = argument(i);
            Type typeTypeCheck = expressionArgument.typeCheck(symbolTable);
            Type type = Type.String;
            if (!typeTypeCheck.identicalTo(type)) {
                setArgument(i, new CastExpr(expressionArgument, type));
            }
        }
        Type type2 = Type.String;
        this._type = type2;
        return type2;
    }
}
