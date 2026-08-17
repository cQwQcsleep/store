package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantDouble;
import com.sun.org.apache.bcel.internal.classfile.ConstantLong;
import defpackage.jt6;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LDC2_W extends CPInstruction implements PushInstruction {
    public LDC2_W(int i) {
        super((short) 20, i);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackProducer(this);
        visitor.visitPushInstruction(this);
        visitor.visitTypedInstruction(this);
        visitor.visitCPInstruction(this);
        visitor.visitLDC2_W(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        byte tag = constantPoolGen.getConstantPool().getConstant(super.getIndex()).getTag();
        if (tag == 5) {
            return Type.LONG;
        }
        if (tag == 6) {
            return Type.DOUBLE;
        }
        jt6.a("Unknown constant type ", super.getOpcode());
        return null;
    }

    public Number getValue(ConstantPoolGen constantPoolGen) {
        Constant constant = constantPoolGen.getConstantPool().getConstant(super.getIndex());
        byte tag = constant.getTag();
        if (tag == 5) {
            return Long.valueOf(((ConstantLong) constant).getBytes());
        }
        if (tag == 6) {
            return Double.valueOf(((ConstantDouble) constant).getBytes());
        }
        jt6.a("Unknown or invalid constant type at ", super.getIndex());
        return null;
    }

    public LDC2_W() {
    }
}
