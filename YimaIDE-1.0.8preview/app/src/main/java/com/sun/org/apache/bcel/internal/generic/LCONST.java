package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LCONST extends Instruction implements ConstantPushInstruction {
    private final long value;

    public LCONST(long j) {
        super((short) 9, (short) 1);
        if (j == 0) {
            super.setOpcode((short) 9);
        } else {
            if (j != 1) {
                throw new ClassGenException("LCONST can be used only for 0 and 1: " + j);
            }
            super.setOpcode((short) 10);
        }
        this.value = j;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitPushInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitConstantPushInstruction(this);
        visitor.visitLCONST(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.LONG;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction
    public Number getValue() {
        return Long.valueOf(this.value);
    }

    public LCONST() {
        this(0L);
    }
}
