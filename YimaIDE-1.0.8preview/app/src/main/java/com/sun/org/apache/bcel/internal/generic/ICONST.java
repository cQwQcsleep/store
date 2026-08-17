package com.sun.org.apache.bcel.internal.generic;

import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ICONST extends Instruction implements ConstantPushInstruction {
    private final int value;

    public ICONST(int i) {
        super((short) 3, (short) 1);
        if (i < -1 || i > 5) {
            c5c.a("ICONST can be used only for value between -1 and 5: ", i);
            throw null;
        }
        super.setOpcode((short) (i + 3));
        this.value = i;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitPushInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitConstantPushInstruction(this);
        visitor.visitICONST(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.INT;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction
    public Number getValue() {
        return Integer.valueOf(this.value);
    }

    public ICONST() {
        this(0);
    }
}
