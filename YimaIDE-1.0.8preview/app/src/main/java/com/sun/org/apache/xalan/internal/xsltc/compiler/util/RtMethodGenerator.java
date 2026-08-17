package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class RtMethodGenerator extends MethodGenerator {
    private static final int HANDLER_INDEX = 2;
    private final Instruction _aloadHandler;
    private final Instruction _astoreHandler;

    public RtMethodGenerator(int i, com.sun.org.apache.bcel.internal.generic.Type type, com.sun.org.apache.bcel.internal.generic.Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        super(i, type, typeArr, strArr, str, str2, instructionList, constantPoolGen);
        this._astoreHandler = new ASTORE(2);
        this._aloadHandler = new ALOAD(2);
    }

    public int getIteratorIndex() {
        return -1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public int getLocalIndex(String str) {
        return -1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public final Instruction loadHandler() {
        return this._aloadHandler;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public final Instruction storeHandler() {
        return this._astoreHandler;
    }
}
