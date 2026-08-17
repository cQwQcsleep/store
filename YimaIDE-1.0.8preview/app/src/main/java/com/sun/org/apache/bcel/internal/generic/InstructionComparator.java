package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface InstructionComparator {
    public static final InstructionComparator DEFAULT = new InstructionComparator() { // from class: dr6
        @Override // com.sun.org.apache.bcel.internal.generic.InstructionComparator
        public final boolean equals(Instruction instruction, Instruction instruction2) {
            return InstructionComparator.a(instruction, instruction2);
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean a(Instruction instruction, Instruction instruction2) {
        if (instruction.getOpcode() != instruction2.getOpcode() || (instruction instanceof BranchInstruction)) {
            return false;
        }
        if (instruction instanceof ConstantPushInstruction) {
            return ((ConstantPushInstruction) instruction).getValue().equals(((ConstantPushInstruction) instruction2).getValue());
        }
        if (instruction instanceof IndexedInstruction) {
            return ((IndexedInstruction) instruction).getIndex() == ((IndexedInstruction) instruction2).getIndex();
        }
        return !(instruction instanceof NEWARRAY) || ((NEWARRAY) instruction).getTypecode() == ((NEWARRAY) instruction2).getTypecode();
    }

    boolean equals(Instruction instruction, Instruction instruction2);
}
