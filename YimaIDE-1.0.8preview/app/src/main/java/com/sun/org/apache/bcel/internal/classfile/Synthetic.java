package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Synthetic extends Attribute {
    private byte[] bytes;

    public Synthetic(Synthetic synthetic) {
        this(synthetic.getNameIndex(), synthetic.getLength(), synthetic.getBytes(), synthetic.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitSynthetic(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        Synthetic synthetic = (Synthetic) clone();
        byte[] bArr = this.bytes;
        if (bArr != null) {
            synthetic.bytes = (byte[]) bArr.clone();
        }
        synthetic.setConstantPool(constantPool);
        return synthetic;
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
        StringBuilder sb = new StringBuilder(com.sun.tools.classfile.Attribute.Synthetic);
        if (super.getLength() > 0) {
            sb.append(" ");
            sb.append(Utility.toHexString(this.bytes));
        }
        return sb.toString();
    }

    public Synthetic(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, (byte[]) null, constantPool);
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            this.bytes = bArr;
            dataInput.readFully(bArr);
            Attribute.println("Synthetic attribute with length > 0");
        }
    }

    public Synthetic(int i, int i2, byte[] bArr, ConstantPool constantPool) {
        super((byte) 7, i, Args.require0(i2, "Synthetic attribute length"), constantPool);
        this.bytes = bArr;
    }
}
