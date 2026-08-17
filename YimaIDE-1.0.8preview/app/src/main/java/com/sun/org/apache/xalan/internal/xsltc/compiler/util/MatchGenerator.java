package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class MatchGenerator extends MethodGenerator {
    private static int CURRENT_INDEX = 1;
    private Instruction _aloadDom;
    private final Instruction _iloadCurrent;
    private final Instruction _istoreCurrent;
    private int _iteratorIndex;

    public MatchGenerator(int i, com.sun.org.apache.bcel.internal.generic.Type type, com.sun.org.apache.bcel.internal.generic.Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        super(i, type, typeArr, strArr, str, str2, instructionList, constantPoolGen);
        this._iteratorIndex = -1;
        this._iloadCurrent = new ILOAD(CURRENT_INDEX);
        this._istoreCurrent = new ISTORE(CURRENT_INDEX);
    }

    public int getHandlerIndex() {
        return -1;
    }

    public int getIteratorIndex() {
        return this._iteratorIndex;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public int getLocalIndex(String str) {
        return str.equals(Keywords.FUNC_CURRENT_STRING) ? CURRENT_INDEX : super.getLocalIndex(str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadCurrentNode() {
        return this._iloadCurrent;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadDOM() {
        return this._aloadDom;
    }

    public void setDomIndex(int i) {
        this._aloadDom = new ALOAD(i);
    }

    public void setIteratorIndex(int i) {
        this._iteratorIndex = i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction storeCurrentNode() {
        return this._istoreCurrent;
    }
}
