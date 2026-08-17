package com.sun.org.apache.bcel.internal.classfile;

import defpackage.yr2;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumElementValue extends ElementValue {
    private final int typeIdx;
    private final int valueIdx;

    public EnumElementValue(int i, int i2, int i3, ConstantPool constantPool) {
        super(i, constantPool);
        if (i != 101) {
            yr2.a("Only element values of type enum can be built with this ctor - type specified: ", i);
            throw null;
        }
        this.typeIdx = i2;
        this.valueIdx = i3;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getType());
        dataOutputStream.writeShort(this.typeIdx);
        dataOutputStream.writeShort(this.valueIdx);
    }

    public String getEnumTypeString() {
        return super.getConstantPool().getConstantUtf8(this.typeIdx).getBytes();
    }

    public String getEnumValueString() {
        return super.getConstantPool().getConstantUtf8(this.valueIdx).getBytes();
    }

    public int getTypeIndex() {
        return this.typeIdx;
    }

    public int getValueIndex() {
        return this.valueIdx;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String stringifyValue() {
        return super.getConstantPool().getConstantUtf8(this.valueIdx).getBytes();
    }
}
