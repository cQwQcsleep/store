package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class BooleanCall extends FunctionCall {
    private Expression _arg;

    public BooleanCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._arg = null;
        this._arg = argument(0);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._arg.translate(classGenerator, methodGenerator);
        Type type = this._arg.getType();
        Type type2 = Type.Boolean;
        if (type.identicalTo(type2)) {
            return;
        }
        this._arg.startIterator(classGenerator, methodGenerator);
        type.translateTo(classGenerator, methodGenerator, type2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this._arg.typeCheck(symbolTable);
        Type type = Type.Boolean;
        this._type = type;
        return type;
    }
}
