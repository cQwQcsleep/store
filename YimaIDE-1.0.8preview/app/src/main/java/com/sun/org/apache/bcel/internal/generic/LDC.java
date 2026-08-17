package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import com.sun.org.apache.bcel.internal.classfile.ConstantInteger;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.jt6;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {
    public LDC(int i) {
        super((short) 19, i);
        setSize();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitStackProducer(this);
        visitor.visitPushInstruction(this);
        visitor.visitExceptionThrower(this);
        visitor.visitTypedInstruction(this);
        visitor.visitCPInstruction(this);
        visitor.visitLDC(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        if (super.getLength() == 2) {
            dataOutputStream.writeByte(super.getIndex());
        } else {
            dataOutputStream.writeShort(super.getIndex());
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION, new Class[0]);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        byte tag = constantPoolGen.getConstantPool().getConstant(super.getIndex()).getTag();
        if (tag == 3) {
            return Type.INT;
        }
        if (tag == 4) {
            return Type.FLOAT;
        }
        if (tag == 7) {
            return Type.CLASS;
        }
        if (tag == 8) {
            return Type.STRING;
        }
        jt6.a("Unknown or invalid constant type at ", super.getIndex());
        return null;
    }

    public Object getValue(ConstantPoolGen constantPoolGen) {
        Constant constant = constantPoolGen.getConstantPool().getConstant(super.getIndex());
        byte tag = constant.getTag();
        if (tag == 3) {
            return Integer.valueOf(((ConstantInteger) constant).getBytes());
        }
        if (tag == 4) {
            return Float.valueOf(((ConstantFloat) constant).getBytes());
        }
        if (tag == 7) {
            return Type.getType(((ConstantUtf8) constantPoolGen.getConstantPool().getConstant(((ConstantClass) constant).getNameIndex())).getBytes());
        }
        if (tag == 8) {
            return ((ConstantUtf8) constantPoolGen.getConstantPool().getConstant(((ConstantString) constant).getStringIndex())).getBytes();
        }
        jt6.a("Unknown or invalid constant type at ", super.getIndex());
        return null;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        super.setLength(2);
        super.setIndex(byteSequence.readUnsignedByte());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.IndexedInstruction
    public final void setIndex(int i) {
        super.setIndex(i);
        setSize();
    }

    public final void setSize() {
        if (super.getIndex() <= 255) {
            super.setOpcode((short) 18);
            super.setLength(2);
        } else {
            super.setOpcode((short) 19);
            super.setLength(3);
        }
    }

    public LDC() {
    }
}
