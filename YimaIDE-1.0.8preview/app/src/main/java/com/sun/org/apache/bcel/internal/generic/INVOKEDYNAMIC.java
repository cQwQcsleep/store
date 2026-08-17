package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.bcel.internal.classfile.ConstantInvokeDynamic;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class INVOKEDYNAMIC extends InvokeInstruction {
    public INVOKEDYNAMIC(int i) {
        super(Const.INVOKEDYNAMIC, i);
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
        visitor.visitINVOKEDYNAMIC(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeShort(super.getIndex());
        dataOutputStream.writeByte(0);
        dataOutputStream.writeByte(0);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.InvokeInstruction, com.sun.org.apache.bcel.internal.generic.FieldOrMethod
    @Deprecated
    public String getClassName(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        return ((ConstantNameAndType) constantPool.getConstant(((ConstantInvokeDynamic) constantPool.getConstant(super.getIndex(), (byte) 18, ConstantInvokeDynamic.class)).getNameAndTypeIndex(), ConstantNameAndType.class)).getName(constantPool);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.FieldOrMethod
    public ReferenceType getReferenceType(ConstantPoolGen constantPoolGen) {
        return new ObjectType(Object.class.getName());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.initFromFile(byteSequence, z);
        super.setLength(5);
        byteSequence.readByte();
        byteSequence.readByte();
    }

    public INVOKEDYNAMIC() {
    }
}
