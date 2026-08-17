package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import com.sun.org.apache.bcel.internal.classfile.EnumElementValue;
import defpackage.jt6;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumElementValueGen extends ElementValueGen {
    private final int typeIdx;
    private final int valueIdx;

    public EnumElementValueGen(EnumElementValue enumElementValue, ConstantPoolGen constantPoolGen, boolean z) {
        super(101, constantPoolGen);
        if (z) {
            this.typeIdx = constantPoolGen.addUtf8(enumElementValue.getEnumTypeString());
            this.valueIdx = constantPoolGen.addUtf8(enumElementValue.getEnumValueString());
        } else {
            this.typeIdx = enumElementValue.getTypeIndex();
            this.valueIdx = enumElementValue.getValueIndex();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getElementValueType());
        dataOutputStream.writeShort(this.typeIdx);
        dataOutputStream.writeShort(this.valueIdx);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public ElementValue getElementValue() {
        System.err.println("Duplicating value: " + getEnumTypeString() + ":" + getEnumValueString());
        return new EnumElementValue(super.getElementValueType(), this.typeIdx, this.valueIdx, getConstantPool().getConstantPool());
    }

    public String getEnumTypeString() {
        return ((ConstantUtf8) getConstantPool().getConstant(this.typeIdx)).getBytes();
    }

    public String getEnumValueString() {
        return ((ConstantUtf8) getConstantPool().getConstant(this.valueIdx)).getBytes();
    }

    public int getTypeIndex() {
        return this.typeIdx;
    }

    public int getValueIndex() {
        return this.valueIdx;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public String stringifyValue() {
        return ((ConstantUtf8) getConstantPool().getConstant(this.valueIdx)).getBytes();
    }

    public EnumElementValueGen(int i, int i2, ConstantPoolGen constantPoolGen) {
        super(101, constantPoolGen);
        if (super.getElementValueType() == 101) {
            this.typeIdx = i;
            this.valueIdx = i2;
        } else {
            jt6.a("Only element values of type enum can be built with this ctor - type specified: ", super.getElementValueType());
            throw null;
        }
    }

    public EnumElementValueGen(ObjectType objectType, String str, ConstantPoolGen constantPoolGen) {
        super(101, constantPoolGen);
        this.typeIdx = constantPoolGen.addUtf8(objectType.getSignature());
        this.valueIdx = constantPoolGen.addUtf8(str);
    }
}
