package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ModuleMainClass extends Attribute {
    private int mainClassIndex;

    public ModuleMainClass(ModuleMainClass moduleMainClass) {
        this(moduleMainClass.getNameIndex(), moduleMainClass.getLength(), moduleMainClass.getHostClassIndex(), moduleMainClass.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModuleMainClass(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        ModuleMainClass moduleMainClass = (ModuleMainClass) clone();
        moduleMainClass.setConstantPool(constantPool);
        return moduleMainClass;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.mainClassIndex);
    }

    public int getHostClassIndex() {
        return this.mainClassIndex;
    }

    public void setHostClassIndex(int i) {
        this.mainClassIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return "ModuleMainClass: " + Utility.compactClassName(super.getConstantPool().getConstantString(this.mainClassIndex, (byte) 7), false);
    }

    public ModuleMainClass(int i, int i2, int i3, ConstantPool constantPool) {
        super((byte) 26, i, i2, constantPool);
        this.mainClassIndex = Args.requireU2(i3, "mainClassIndex");
    }

    public ModuleMainClass(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, 0, constantPool);
        this.mainClassIndex = dataInput.readUnsignedShort();
    }
}
