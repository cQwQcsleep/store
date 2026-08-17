package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class I2S extends ConversionInstruction {
    public I2S() {
        super((short) 147);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
        visitor.visitTypedInstruction(this);
        visitor.visitStackProducer(this);
        visitor.visitStackConsumer(this);
        visitor.visitConversionInstruction(this);
        visitor.visitI2S(this);
    }
}
