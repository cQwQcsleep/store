package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.Visitor;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class MarkerInstruction extends Instruction {
    public MarkerInstruction() {
        super((short) -1, (short) 0);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public void accept(Visitor visitor) {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackConsumer
    public final int consumeStack(ConstantPoolGen constantPoolGen) {
        return 0;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public Instruction copy() {
        return this;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction
    public final void dump(DataOutputStream dataOutputStream) throws IOException {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackProducer
    public final int produceStack(ConstantPoolGen constantPoolGen) {
        return 0;
    }
}
