package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {

    @Deprecated
    protected int index;

    public CPInstruction(short s, int i) {
        super(s, (short) 3);
        setIndex(i);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeShort(this.index);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final int getIndex() {
        return this.index;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        String constantString = constantPoolGen.getConstantPool().getConstantString(this.index, (byte) 7);
        if (!constantString.startsWith("[")) {
            constantString = "L" + constantString + ";";
        }
        return Type.getType(constantString);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        setIndex(byteSequence.readUnsignedShort());
        super.setLength(3);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public void setIndex(int i) {
        if (i >= 0) {
            this.index = i;
        } else {
            c5c.a("Negative index value: ", i);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(ConstantPool constantPool) {
        Constant constant = constantPool.getConstant(this.index);
        String strConstantToString = constantPool.constantToString(constant);
        if (constant instanceof ConstantClass) {
            strConstantToString = Utility.packageToPath(strConstantToString);
        }
        return Const.getOpcodeName(super.getOpcode()) + " " + strConstantToString;
    }

    public CPInstruction() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        return super.toString(z) + " " + this.index;
    }
}
