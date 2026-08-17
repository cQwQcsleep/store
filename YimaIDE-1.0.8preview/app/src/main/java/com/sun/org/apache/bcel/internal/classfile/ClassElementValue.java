package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassElementValue extends ElementValue {
    private final int idx;

    public ClassElementValue(int i, int i2, ConstantPool constantPool) {
        super(i, constantPool);
        this.idx = i2;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getType());
        dataOutputStream.writeShort(this.idx);
    }

    public String getClassString() {
        return super.getConstantPool().getConstantUtf8(this.idx).getBytes();
    }

    public int getIndex() {
        return this.idx;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String stringifyValue() {
        return super.getConstantPool().getConstantUtf8(this.idx).getBytes();
    }
}
