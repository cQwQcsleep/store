package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.FilterGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LangCall extends FunctionCall {
    private Expression _lang;
    private Type _langType;

    public LangCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._lang = argument(0);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public Type getType() {
        return Type.Boolean;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "testLanguage", "(Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;I)Z");
        this._lang.translate(classGenerator, methodGenerator);
        instructionList.append(methodGenerator.loadDOM());
        if (classGenerator instanceof FilterGenerator) {
            instructionList.append(new ILOAD(1));
        } else {
            instructionList.append(methodGenerator.loadContextNode());
        }
        instructionList.append(new INVOKESTATIC(iAddMethodref));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._lang.typeCheck(symbolTable);
        this._langType = typeTypeCheck;
        if (!(typeTypeCheck instanceof StringType)) {
            this._lang = new CastExpr(this._lang, Type.String);
        }
        return Type.Boolean;
    }
}
