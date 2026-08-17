package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CompareGenerator extends MethodGenerator {
    private static int CURRENT_INDEX = 2;
    private static int DOM_INDEX = 1;
    private static int LAST_INDEX = 5;
    private static int LEVEL_INDEX = 3;
    private static int TRANSLET_INDEX = 4;
    private int ITERATOR_INDEX;
    private final Instruction _aloadDom;
    private final Instruction _aloadIterator;
    private final Instruction _astoreIterator;
    private final Instruction _iloadCurrent;
    private final Instruction _iloadLast;
    private final Instruction _istoreCurrent;

    public CompareGenerator(int i, com.sun.org.apache.bcel.internal.generic.Type type, com.sun.org.apache.bcel.internal.generic.Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        super(i, type, typeArr, strArr, str, str2, instructionList, constantPoolGen);
        this.ITERATOR_INDEX = 6;
        this._iloadCurrent = new ILOAD(CURRENT_INDEX);
        this._istoreCurrent = new ISTORE(CURRENT_INDEX);
        this._aloadDom = new ALOAD(DOM_INDEX);
        this._iloadLast = new ILOAD(LAST_INDEX);
        this.ITERATOR_INDEX = addLocalVariable(Constants.ITERATOR_PNAME, Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null).getIndex();
        this._aloadIterator = new ALOAD(this.ITERATOR_INDEX);
        this._astoreIterator = new ASTORE(this.ITERATOR_INDEX);
        instructionList.append(new ACONST_NULL());
        instructionList.append(storeIterator());
    }

    public int getHandlerIndex() {
        return -1;
    }

    public int getIteratorIndex() {
        return -1;
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

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public Instruction loadIterator() {
        return this._aloadIterator;
    }

    public Instruction loadLastNode() {
        return this._iloadLast;
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
