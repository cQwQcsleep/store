package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FCONST extends Instruction implements ConstantPushInstruction {
    private final float value;

    public FCONST(float f) {
        super((short) 11, (short) 1);
        double d = f;
        if (d == XPath.MATCH_SCORE_QNAME) {
            super.setOpcode((short) 11);
        } else if (d == 1.0d) {
            super.setOpcode((short) 12);
        } else {
            if (d != 2.0d) {
                throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
            }
            super.setOpcode((short) 13);
        }
        this.value = f;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitPushInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitConstantPushInstruction(this);
        visitor.visitFCONST(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.FLOAT;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction
    public Number getValue() {
        return Float.valueOf(this.value);
    }

    public FCONST() {
        this(0.0f);
    }
}
