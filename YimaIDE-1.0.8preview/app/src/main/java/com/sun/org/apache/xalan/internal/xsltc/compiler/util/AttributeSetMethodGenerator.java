package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class AttributeSetMethodGenerator extends MethodGenerator {
    protected static final int CURRENT_INDEX = 4;
    private static final int PARAM_START_INDEX = 5;
    private static final String[] argNames = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME, "node"};
    private static final com.sun.org.apache.bcel.internal.generic.Type[] argTypes = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;"), com.sun.org.apache.bcel.internal.generic.Type.INT};

    public AttributeSetMethodGenerator(String str, ClassGenerator classGenerator) {
        super(2, com.sun.org.apache.bcel.internal.generic.Type.VOID, argTypes, argNames, str, classGenerator.getClassName(), new InstructionList(), classGenerator.getConstantPool());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator
    public int getLocalIndex(String str) {
        if (str.equals(Keywords.FUNC_CURRENT_STRING)) {
            return 4;
        }
        return super.getLocalIndex(str);
    }

    public Instruction loadParameter(int i) {
        return new ALOAD(i + 5);
    }

    public Instruction storeParameter(int i) {
        return new ASTORE(i + 5);
    }
}
