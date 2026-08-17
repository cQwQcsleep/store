package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;

    private EnclosingMethod(int i, int i2, int i3, int i4, ConstantPool constantPool) {
        super((byte) 18, i, Args.require(i2, 4, "EnclosingMethod attribute length"), constantPool);
        this.classIndex = Args.requireU2(i3, 0, constantPool.getLength(), "EnclosingMethod class index");
        this.methodIndex = Args.requireU2(i4, "EnclosingMethod method index");
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitEnclosingMethod(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.classIndex);
        dataOutputStream.writeShort(this.methodIndex);
    }

    public final ConstantClass getEnclosingClass() {
        return (ConstantClass) super.getConstantPool().getConstant(this.classIndex, (byte) 7, ConstantClass.class);
    }

    public final int getEnclosingClassIndex() {
        return this.classIndex;
    }

    public final ConstantNameAndType getEnclosingMethod() {
        if (this.methodIndex == 0) {
            return null;
        }
        return (ConstantNameAndType) super.getConstantPool().getConstant(this.methodIndex, (byte) 12, ConstantNameAndType.class);
    }

    public final int getEnclosingMethodIndex() {
        return this.methodIndex;
    }

    public final void setEnclosingClassIndex(int i) {
        this.classIndex = i;
    }

    public final void setEnclosingMethodIndex(int i) {
        this.methodIndex = i;
    }

    public EnclosingMethod(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), constantPool);
    }
}
