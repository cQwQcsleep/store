package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class Instruction extends SyntaxTreeNode {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        getParser().reportError(2, new ErrorMsg(ErrorMsg.NOT_IMPLEMENTED_ERR, (Object) getClass(), (SyntaxTreeNode) this));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return typeCheckContents(symbolTable);
    }
}
