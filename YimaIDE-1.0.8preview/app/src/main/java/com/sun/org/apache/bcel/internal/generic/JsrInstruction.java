package com.sun.org.apache.bcel.internal.generic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer {
    public JsrInstruction() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return new ReturnaddressType(physicalSuccessor());
    }

    public InstructionHandle physicalSuccessor() {
        InstructionHandle target = super.getTarget();
        while (target.getPrev() != null) {
            target = target.getPrev();
        }
        while (target.getInstruction() != this) {
            target = target.getNext();
        }
        InstructionHandle next = target;
        while (next != null) {
            next = next.getNext();
            if (next != null && next.getInstruction() == this) {
                k2d.a("physicalSuccessor() called on a shared JsrInstruction.");
                return null;
            }
        }
        return target.getNext();
    }

    public JsrInstruction(short s, InstructionHandle instructionHandle) {
        super(s, instructionHandle);
    }
}
