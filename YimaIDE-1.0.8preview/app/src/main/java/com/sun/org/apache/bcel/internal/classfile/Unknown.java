package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Unknown extends Attribute {
    private byte[] bytes;
    private final String name;

    public Unknown(Unknown unknown) {
        this(unknown.getNameIndex(), unknown.getLength(), unknown.getBytes(), unknown.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitUnknown(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        Unknown unknown = (Unknown) clone();
        byte[] bArr = this.bytes;
        if (bArr != null) {
            unknown.bytes = (byte[]) bArr.clone();
        }
        unknown.setConstantPool(constantPool);
        return unknown;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        if (super.getLength() > 0) {
            dataOutputStream.write(this.bytes, 0, super.getLength());
        }
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String getName() {
        return this.name;
    }

    public void setBytes(byte[] bArr) {
        this.bytes = bArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        String hexString;
        if (super.getLength() == 0 || this.bytes == null) {
            return "(Unknown attribute " + this.name + ")";
        }
        int length = super.getLength();
        byte[] bArr = this.bytes;
        if (length > 10) {
            hexString = Utility.toHexString(Arrays.copyOf(bArr, 10)) + "... (truncated)";
        } else {
            hexString = Utility.toHexString(bArr);
        }
        return "(Unknown attribute " + this.name + ": " + hexString + ")";
    }

    public Unknown(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (byte[]) null, constantPool);
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            this.bytes = bArr;
            dataInput.readFully(bArr);
        }
    }

    public Unknown(int i, int i2, byte[] bArr, ConstantPool constantPool) {
        super((byte) -1, i, i2, constantPool);
        this.bytes = bArr;
        this.name = constantPool.getConstantUtf8(i).getBytes();
    }
}
