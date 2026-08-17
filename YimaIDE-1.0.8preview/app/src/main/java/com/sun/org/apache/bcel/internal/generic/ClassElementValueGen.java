package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ClassElementValue;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassElementValueGen extends ElementValueGen {
    private final int idx;

    public ClassElementValueGen(ClassElementValue classElementValue, ConstantPoolGen constantPoolGen, boolean z) {
        super(99, constantPoolGen);
        if (z) {
            this.idx = constantPoolGen.addUtf8(classElementValue.getClassString());
        } else {
            this.idx = classElementValue.getIndex();
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getElementValueType());
        dataOutputStream.writeShort(this.idx);
    }

    public String getClassString() {
        return ((ConstantUtf8) getConstantPool().getConstant(this.idx)).getBytes();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public ElementValue getElementValue() {
        return new ClassElementValue(super.getElementValueType(), this.idx, getConstantPool().getConstantPool());
    }

    public int getIndex() {
        return this.idx;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public String stringifyValue() {
        return getClassString();
    }

    public ClassElementValueGen(int i, ConstantPoolGen constantPoolGen) {
        super(99, constantPoolGen);
        this.idx = i;
    }

    public ClassElementValueGen(ObjectType objectType, ConstantPoolGen constantPoolGen) {
        super(99, constantPoolGen);
        this.idx = constantPoolGen.addUtf8(objectType.getSignature());
    }
}
