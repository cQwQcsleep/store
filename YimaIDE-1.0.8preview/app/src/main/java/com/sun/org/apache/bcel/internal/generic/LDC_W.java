package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LDC_W extends LDC {
    public LDC_W() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LDC, com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public void initFromFile(ByteSequence byteSequence, boolean z) throws IOException {
        setIndex(byteSequence.readUnsignedShort());
        super.setOpcode((short) 19);
        super.setLength(3);
    }

    public LDC_W(int i) {
        super(i);
    }
}
