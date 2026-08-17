package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {
    private byte type;

    public NEWARRAY(byte b) {
        super(Const.NEWARRAY, (short) 2);
        this.type = b;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitAllocationInstruction(this);
        visitor.visitExceptionThrower(this);
        visitor.visitStackProducer(this);
        visitor.visitNEWARRAY(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getOpcode());
        dataOutputStream.writeByte(this.type);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ExceptionThrower
    public Class<?>[] getExceptions() {
        return new Class[]{ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION};
    }

    public final Type getType() {
        return new ArrayType(BasicType.getType(this.type), 1);
    }

    public final byte getTypecode() {
        return this.type;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        this.type = byteSequence.readByte();
        super.setLength(2);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(boolean z) {
        return super.toString(z) + " " + Const.getTypeName(this.type);
    }

    public NEWARRAY(BasicType basicType) {
        this(basicType.getType());
    }

    public NEWARRAY() {
    }
}
