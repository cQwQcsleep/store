package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class LocalVariable implements Cloneable, Node {
    static final LocalVariable[] EMPTY_ARRAY = new LocalVariable[0];
    private ConstantPool constantPool;
    private int index;
    private int length;
    private int nameIndex;
    private final int origIndex;
    private int signatureIndex;
    private int startPc;

    public LocalVariable(int i, int i2, int i3, int i4, int i5, ConstantPool constantPool, int i6) {
        this.startPc = Args.requireU2(i, "startPc");
        this.length = Args.requireU2(i2, "length");
        this.nameIndex = Args.requireU2(i3, "nameIndex");
        this.signatureIndex = Args.requireU2(i4, "signatureIndex");
        this.index = Args.requireU2(i5, "index");
        this.origIndex = Args.requireU2(i6, "origIndex");
        this.constantPool = constantPool;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitLocalVariable(this);
    }

    public LocalVariable copy() {
        try {
            return (LocalVariable) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.startPc);
        dataOutputStream.writeShort(this.length);
        dataOutputStream.writeShort(this.nameIndex);
        dataOutputStream.writeShort(this.signatureIndex);
        dataOutputStream.writeShort(this.index);
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLength() {
        return this.length;
    }

    public String getName() {
        return this.constantPool.getConstantUtf8(this.nameIndex).getBytes();
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getOrigIndex() {
        return this.origIndex;
    }

    public String getSignature() {
        return this.constantPool.getConstantUtf8(this.signatureIndex).getBytes();
    }

    public int getSignatureIndex() {
        return this.signatureIndex;
    }

    public int getStartPC() {
        return this.startPc;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setNameIndex(int i) {
        this.nameIndex = i;
    }

    public void setSignatureIndex(int i) {
        this.signatureIndex = i;
    }

    public void setStartPC(int i) {
        this.startPc = i;
    }

    public String toString() {
        return toStringShared(false);
    }

    public String toStringShared(boolean z) {
        String name = getName();
        String strSignatureToString = Utility.signatureToString(getSignature(), false);
        return "LocalVariable".concat(z ? "Types" : "") + "(startPc = " + this.startPc + ", length = " + this.length + ", index = " + this.index + ":" + strSignatureToString + " " + name + ")";
    }

    public LocalVariable(int i, int i2, int i3, int i4, int i5, ConstantPool constantPool) {
        this(i, i2, i3, i4, i5, constantPool, i5);
    }

    public LocalVariable(DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), constantPool);
    }

    public LocalVariable(LocalVariable localVariable) {
        this(localVariable.getStartPC(), localVariable.getLength(), localVariable.getNameIndex(), localVariable.getSignatureIndex(), localVariable.getIndex(), localVariable.getConstantPool());
    }
}
