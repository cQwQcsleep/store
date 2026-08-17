package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class NodeSortRecordGenerator extends ClassGenerator {
    private static final int TRANSLET_INDEX = 4;
    private final Instruction _aloadTranslet;

    public NodeSortRecordGenerator(String str, String str2, String str3, int i, String[] strArr, Stylesheet stylesheet) {
        super(str, str2, str3, i, strArr, stylesheet);
        this._aloadTranslet = new ALOAD(4);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator
    public boolean isExternal() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator
    public Instruction loadTranslet() {
        return this._aloadTranslet;
    }
}
