package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    public PMGClass(PMGClass pMGClass) {
        this(pMGClass.getNameIndex(), pMGClass.getLength(), pMGClass.getPMGIndex(), pMGClass.getPMGClassIndex(), pMGClass.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        Attribute.println("Visiting non-standard PMGClass object");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.pmgIndex);
        dataOutputStream.writeShort(this.pmgClassIndex);
    }

    public int getPMGClassIndex() {
        return this.pmgClassIndex;
    }

    public String getPMGClassName() {
        return super.getConstantPool().getConstantUtf8(this.pmgClassIndex).getBytes();
    }

    public int getPMGIndex() {
        return this.pmgIndex;
    }

    public String getPMGName() {
        return super.getConstantPool().getConstantUtf8(this.pmgIndex).getBytes();
    }

    public void setPMGClassIndex(int i) {
        this.pmgClassIndex = i;
    }

    public void setPMGIndex(int i) {
        this.pmgIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return "PMGClass(" + getPMGName() + ", " + getPMGClassName() + ")";
    }

    public PMGClass(int i, int i2, int i3, int i4, ConstantPool constantPool) {
        super((byte) 9, i, i2, constantPool);
        this.pmgIndex = i3;
        this.pmgClassIndex = i4;
    }

    public PMGClass(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), constantPool);
    }
}
