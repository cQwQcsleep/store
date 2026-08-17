package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class NestHost extends Attribute {
    private int hostClassIndex;

    public NestHost(NestHost nestHost) {
        this(nestHost.getNameIndex(), nestHost.getLength(), nestHost.getHostClassIndex(), nestHost.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitNestHost(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        NestHost nestHost = (NestHost) clone();
        nestHost.setConstantPool(constantPool);
        return nestHost;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.hostClassIndex);
    }

    public int getHostClassIndex() {
        return this.hostClassIndex;
    }

    public void setHostClassIndex(int i) {
        this.hostClassIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return "NestHost: " + Utility.compactClassName(super.getConstantPool().getConstantString(this.hostClassIndex, (byte) 7), false);
    }

    public NestHost(int i, int i2, int i3, ConstantPool constantPool) {
        super((byte) 26, i, i2, constantPool);
        this.hostClassIndex = Args.requireU2(i3, "hostClassIndex");
    }

    public NestHost(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, 0, constantPool);
        this.hostClassIndex = dataInput.readUnsignedShort();
    }
}
