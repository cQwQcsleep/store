package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DCONST extends Instruction implements ConstantPushInstruction {
    private final double value;

    public DCONST(double d) {
        super((short) 14, (short) 1);
        if (d == XPath.MATCH_SCORE_QNAME) {
            super.setOpcode((short) 14);
        } else {
            if (d != 1.0d) {
                throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + d);
            }
            super.setOpcode((short) 15);
        }
        this.value = d;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitPushInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitTypedInstruction(this);
        visitor.visitConstantPushInstruction(this);
        visitor.visitDCONST(this);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return Type.DOUBLE;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction
    public Number getValue() {
        return Double.valueOf(this.value);
    }

    public DCONST() {
        this(XPath.MATCH_SCORE_QNAME);
    }
}
