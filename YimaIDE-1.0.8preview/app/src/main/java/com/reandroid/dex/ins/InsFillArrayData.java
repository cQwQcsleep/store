package com.reandroid.dex.ins;

import com.reandroid.dex.data.InstructionList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsFillArrayData extends Ins31t {
    public InsFillArrayData() {
        super(Opcode.FILL_ARRAY_DATA);
    }

    public Ins22c findNewArrayLazy() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null) {
            return null;
        }
        Ins ins = instructionList.get(getIndex() - 1);
        if (ins.getOpcode() == Opcode.NEW_ARRAY) {
            Ins22c ins22c = (Ins22c) ins;
            if (getRegister(0) == ins22c.getRegister(0)) {
                return ins22c;
            }
        }
        return null;
    }

    public InsArrayData getInsArrayData() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null) {
            return null;
        }
        Ins atAddress = instructionList.getAtAddress(getTargetAddress());
        if (atAddress instanceof InsArrayData) {
            return (InsArrayData) atAddress;
        }
        return null;
    }

    @Override // com.reandroid.dex.ins.Ins31t
    public String getLabelPrefix() {
        return ":array_";
    }
}
