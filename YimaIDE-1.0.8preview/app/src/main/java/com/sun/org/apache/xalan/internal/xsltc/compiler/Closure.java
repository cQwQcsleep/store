package com.sun.org.apache.xalan.internal.xsltc.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Closure {
    void addVariable(VariableRefBase variableRefBase);

    String getInnerClassName();

    Closure getParentClosure();

    boolean inInnerClass();
}
