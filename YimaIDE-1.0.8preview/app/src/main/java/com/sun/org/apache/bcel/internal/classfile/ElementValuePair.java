package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementValuePair {
    static final ElementValuePair[] EMPTY_ARRAY = new ElementValuePair[0];
    private final ConstantPool constantPool;
    private final int elementNameIndex;
    private final ElementValue elementValue;

    public ElementValuePair(int i, ElementValue elementValue, ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementNameIndex = i;
        this.constantPool = constantPool;
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.elementNameIndex);
        this.elementValue.dump(dataOutputStream);
    }

    public int getNameIndex() {
        return this.elementNameIndex;
    }

    public String getNameString() {
        return this.constantPool.getConstantUtf8(this.elementNameIndex).getBytes();
    }

    public final ElementValue getValue() {
        return this.elementValue;
    }

    public String toShortString() {
        return getNameString() + "=" + getValue().toShortString();
    }
}
