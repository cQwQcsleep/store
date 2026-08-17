package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.c5c;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class INVOKEINTERFACE extends InvokeInstruction {
    private int nargs;

    public INVOKEINTERFACE(int i, int i2) {
        super(Const.INVOKEINTERFACE, i);
        super.setLength(5);
        if (i2 >= 1) {
            this.nargs = i2;
        } else {
            c5c.a("Number of arguments must be > 0 ", i2);
            throw null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitExceptionThrower(this);
        visitor.visitTypedInstruction(this);
        visitor.visitStackConsumer(this);
        visitor.visitStackProducer(this);
        visitor.visitLoadClass(this);
        visitor.visitCPInstruction(this);
        visitor.visitFieldOrMethod(this);
        visitor.visitInvokeInstruction(this);
        visitor.visitINVOKEINTERFACE(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InvokeInstruction, com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackConsumer
    public int consumeStack(ConstantPoolGen constantPoolGen) {
        return this.nargs;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeShort(super.getIndex());
        dataOutputStream.writeByte(this.nargs);
        dataOutputStream.writeByte(0);
    }

    public int getCount() {
        return this.nargs;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.initFromFile(byteSequence, z);
        super.setLength(5);
        this.nargs = byteSequence.readUnsignedByte();
        byteSequence.readByte();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InvokeInstruction, com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(ConstantPool constantPool) {
        return super.toString(constantPool) + " " + this.nargs;
    }

    public INVOKEINTERFACE() {
    }
}
