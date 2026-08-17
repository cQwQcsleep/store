package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValuePair;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementValuePairGen {
    private final ConstantPoolGen constantPoolGen;
    private final int nameIdx;
    private final ElementValueGen value;

    public ElementValuePairGen(ElementValuePair elementValuePair, ConstantPoolGen constantPoolGen, boolean z) {
        this.constantPoolGen = constantPoolGen;
        if (z) {
            this.nameIdx = constantPoolGen.addUtf8(elementValuePair.getNameString());
        } else {
            this.nameIdx = elementValuePair.getNameIndex();
        }
        this.value = ElementValueGen.copy(elementValuePair.getValue(), constantPoolGen, z);
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIdx);
        this.value.dump(dataOutputStream);
    }

    public ElementValuePair getElementNameValuePair() {
        return new ElementValuePair(this.nameIdx, this.value.getElementValue(), this.constantPoolGen.getConstantPool());
    }

    public int getNameIndex() {
        return this.nameIdx;
    }

    public final String getNameString() {
        return ((ConstantUtf8) this.constantPoolGen.getConstant(this.nameIdx)).getBytes();
    }

    public final ElementValueGen getValue() {
        return this.value;
    }

    public String toString() {
        return "ElementValuePair:[" + getNameString() + "=" + this.value.stringifyValue() + "]";
    }

    public ElementValuePairGen(int i, ElementValueGen elementValueGen, ConstantPoolGen constantPoolGen) {
        this.nameIdx = i;
        this.value = elementValueGen;
        this.constantPoolGen = constantPoolGen;
    }

    public ElementValuePairGen(String str, ElementValueGen elementValueGen, ConstantPoolGen constantPoolGen) {
        this.nameIdx = constantPoolGen.addUtf8(str);
        this.value = elementValueGen;
        this.constantPoolGen = constantPoolGen;
    }
}
