package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NumberType extends Type {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean isNumber() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean isSimple() {
        return true;
    }
}
