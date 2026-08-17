package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class InnerClass implements Cloneable, Node {
    private int innerAccessFlags;
    private int innerClassIndex;
    private int innerNameIndex;
    private int outerClassIndex;

    public InnerClass(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), dataInput.readUnsignedShort());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitInnerClass(this);
    }

    public InnerClass copy() {
        try {
            return (InnerClass) clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.innerClassIndex);
        dataOutputStream.writeShort(this.outerClassIndex);
        dataOutputStream.writeShort(this.innerNameIndex);
        dataOutputStream.writeShort(this.innerAccessFlags);
    }

    public int getInnerAccessFlags() {
        return this.innerAccessFlags;
    }

    public int getInnerClassIndex() {
        return this.innerClassIndex;
    }

    public int getInnerNameIndex() {
        return this.innerNameIndex;
    }

    public int getOuterClassIndex() {
        return this.outerClassIndex;
    }

    public void setInnerAccessFlags(int i) {
        this.innerAccessFlags = i;
    }

    public void setInnerClassIndex(int i) {
        this.innerClassIndex = i;
    }

    public void setInnerNameIndex(int i) {
        this.innerNameIndex = i;
    }

    public void setOuterClassIndex(int i) {
        this.outerClassIndex = i;
    }

    public String toString(ConstantPool constantPool) {
        String str;
        String strCompactClassName = Utility.compactClassName(constantPool.getConstantString(this.innerClassIndex, (byte) 7), false);
        int i = this.outerClassIndex;
        if (i != 0) {
            str = " of class " + Utility.compactClassName(constantPool.getConstantString(i, (byte) 7), false);
        } else {
            str = "";
        }
        int i2 = this.innerNameIndex;
        String bytes = i2 != 0 ? constantPool.getConstantUtf8(i2).getBytes() : "(anonymous)";
        String strAccessToString = Utility.accessToString(this.innerAccessFlags, true);
        return "  " + (strAccessToString.isEmpty() ? "" : strAccessToString.concat(" ")) + bytes + "=class " + strCompactClassName + str;
    }

    public InnerClass(InnerClass innerClass) {
        this(innerClass.getInnerClassIndex(), innerClass.getOuterClassIndex(), innerClass.getInnerNameIndex(), innerClass.getInnerAccessFlags());
    }

    public InnerClass(int i, int i2, int i3, int i4) {
        this.innerClassIndex = i;
        this.outerClassIndex = i2;
        this.innerNameIndex = i3;
        this.innerAccessFlags = i4;
    }

    public String toString() {
        return "InnerClass(" + this.innerClassIndex + ", " + this.outerClassIndex + ", " + this.innerNameIndex + ", " + this.innerAccessFlags + ")";
    }
}
