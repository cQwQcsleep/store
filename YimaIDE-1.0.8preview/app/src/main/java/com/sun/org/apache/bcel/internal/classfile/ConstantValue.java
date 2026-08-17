package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(ConstantValue constantValue) {
        this(constantValue.getNameIndex(), constantValue.getLength(), constantValue.getConstantValueIndex(), constantValue.getConstantPool());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantValue(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        ConstantValue constantValue = (ConstantValue) clone();
        constantValue.setConstantPool(constantPool);
        return constantValue;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.constantValueIndex);
    }

    public int getConstantValueIndex() {
        return this.constantValueIndex;
    }

    public void setConstantValueIndex(int i) {
        this.constantValueIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        Constant constant = super.getConstantPool().getConstant(this.constantValueIndex);
        byte tag = constant.getTag();
        if (tag == 3) {
            return String.valueOf(((ConstantInteger) constant).getBytes());
        }
        if (tag == 4) {
            return String.valueOf(((ConstantFloat) constant).getBytes());
        }
        if (tag == 5) {
            return String.valueOf(((ConstantLong) constant).getBytes());
        }
        if (tag == 6) {
            return String.valueOf(((ConstantDouble) constant).getBytes());
        }
        if (tag != 8) {
            qu7.a("Type of ConstValue invalid: ", constant);
            return null;
        }
        return "\"" + Utility.convertString(super.getConstantPool().getConstantUtf8(((ConstantString) constant).getStringIndex()).getBytes()) + "\"";
    }

    public ConstantValue(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), constantPool);
    }

    public ConstantValue(int i, int i2, int i3, ConstantPool constantPool) {
        super((byte) 1, i, Args.require(i2, 2, "ConstantValue attribute length"), constantPool);
        this.constantValueIndex = i3;
    }
}
