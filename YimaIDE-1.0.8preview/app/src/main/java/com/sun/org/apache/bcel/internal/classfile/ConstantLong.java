package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantLong extends Constant implements ConstantObject {
    private long bytes;

    public ConstantLong(ConstantLong constantLong) {
        this(constantLong.getBytes());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantLong(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getTag());
        dataOutputStream.writeLong(this.bytes);
    }

    public long getBytes() {
        return this.bytes;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ConstantObject
    public Object getConstantValue(ConstantPool constantPool) {
        return Long.valueOf(this.bytes);
    }

    public void setBytes(long j) {
        this.bytes = j;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Constant
    public String toString() {
        return super.toString() + "(bytes = " + this.bytes + ")";
    }

    public ConstantLong(DataInput dataInput) throws IOException {
        this(dataInput.readLong());
    }

    public ConstantLong(long j) {
        super((byte) 5);
        this.bytes = j;
    }
}
