package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ConstantCP extends Constant {

    @java.lang.Deprecated
    protected int class_index;

    @java.lang.Deprecated
    protected int name_and_type_index;

    public ConstantCP(ConstantCP constantCP) {
        this(constantCP.getTag(), constantCP.getClassIndex(), constantCP.getNameAndTypeIndex());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeShort(this.class_index);
        dataOutputStream.writeShort(this.name_and_type_index);
    }

    public String getClass(ConstantPool constantPool) {
        return constantPool.constantToString(this.class_index, (byte) 7);
    }

    public final int getClassIndex() {
        return this.class_index;
    }

    public final int getNameAndTypeIndex() {
        return this.name_and_type_index;
    }

    public final void setClassIndex(int i) {
        this.class_index = i;
    }

    public final void setNameAndTypeIndex(int i) {
        this.name_and_type_index = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(class_index = " + this.class_index + ", name_and_type_index = " + this.name_and_type_index + ")";
    }

    public ConstantCP(byte b, int i, int i2) {
        super(b);
        this.class_index = i;
        this.name_and_type_index = i2;
    }

    public ConstantCP(byte b, DataInput dataInput) throws IOException {
        this(b, dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }
}
