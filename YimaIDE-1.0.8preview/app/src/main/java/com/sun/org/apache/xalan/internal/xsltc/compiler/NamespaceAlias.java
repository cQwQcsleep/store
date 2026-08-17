package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class NamespaceAlias extends TopLevelElement {
    private String rPrefix;
    private String sPrefix;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        this.sPrefix = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_STYLESHEET_PREFIX);
        this.rPrefix = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_RESULT_PREFIX);
        parser.getSymbolTable().addPrefixAlias(this.sPrefix, this.rPrefix);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return Type.Void;
    }
}
