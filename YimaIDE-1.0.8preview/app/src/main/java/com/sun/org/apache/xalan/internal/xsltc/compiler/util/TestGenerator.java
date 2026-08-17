package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TestGenerator extends MethodGenerator {
    private static int CONTEXT_NODE_INDEX = 1;
    private static int CURRENT_NODE_INDEX = 4;
    private static int ITERATOR_INDEX = 6;
    private Instruction _aloadDom;
    private final Instruction _aloadIterator;
    private final Instruction _astoreIterator;
    private final Instruction _iloadContext;
    private final Instruction _iloadCurrent;
    private final Instruction _istoreContext;
    private final Instruction _istoreCurrent;

    public TestGenerator(int i, com.sun.org.apache.bcel.internal.generic.Type type, com.sun.org.apache.bcel.internal.generic.Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        super(i, type, typeArr, strArr, str, str2, instructionList, constantPoolGen);
        this._iloadCurrent = new ILOAD(CURRENT_NODE_INDEX);
        this._istoreCurrent = new ISTORE(CURRENT_NODE_INDEX);
        this._iloadContext = new ILOAD(CONTEXT_NODE_INDEX);
        this._istoreContext = new ILOAD(CONTEXT_NODE_INDEX);
        this._astoreIterator = new ASTORE(ITERATOR_INDEX);
        this._aloadIterator = new ALOAD(ITERATOR_INDEX);
    }

    public int getHandlerIndex() {
        return -1;
    }

    public int getIteratorIndex() {
        return ITERATOR_INDEX;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public int getLocalIndex(String str) {
        return str.equals(Keywords.FUNC_CURRENT_STRING) ? CURRENT_NODE_INDEX : super.getLocalIndex(str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadContextNode() {
        return this._iloadContext;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadCurrentNode() {
        return this._iloadCurrent;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadDOM() {
        return this._aloadDom;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadIterator() {
        return this._aloadIterator;
    }

    public void setDomIndex(int i) {
        this._aloadDom = new ALOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction storeContextNode() {
        return this._istoreContext;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction storeCurrentNode() {
        return this._istoreCurrent;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction storeIterator() {
        return this._astoreIterator;
    }
}
