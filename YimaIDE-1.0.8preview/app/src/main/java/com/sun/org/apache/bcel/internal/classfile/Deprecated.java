package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Deprecated extends Attribute {
    private byte[] bytes;

    public Deprecated(Deprecated deprecated) {
        this(deprecated.getNameIndex(), deprecated.getLength(), deprecated.getBytes(), deprecated.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitDeprecated(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        Deprecated deprecated = (Deprecated) clone();
        byte[] bArr = this.bytes;
        if (bArr != null) {
            deprecated.bytes = (byte[]) bArr.clone();
        }
        deprecated.setConstantPool(constantPool);
        return deprecated;
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

    public void setBytes(byte[] bArr) {
        this.bytes = bArr;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return Const.getAttributeName(8) + ": true";
    }

    public Deprecated(int i, int i2, byte[] bArr, ConstantPool constantPool) {
        super((byte) 8, i, Args.require0(i2, "Deprecated attribute length"), constantPool);
        this.bytes = bArr;
    }

    public Deprecated(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (byte[]) null, constantPool);
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            this.bytes = bArr;
            dataInput.readFully(bArr);
            Attribute.println("Deprecated attribute with length > 0");
        }
    }
}
