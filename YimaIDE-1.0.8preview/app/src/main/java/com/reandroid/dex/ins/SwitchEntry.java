package com.reandroid.dex.ins;

import com.reandroid.dex.data.InstructionList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SwitchEntry extends PayloadEntry, Label {
    default void addEquivalentIfEq(int i) {
        InsSwitch insSwitch = getPayload().getSwitch();
        InstructionList instructionList = insSwitch.getInstructionList();
        Ins targetIns = getTargetIns();
        Ins ins = (Ins) instructionList.createConstIntegerAt(insSwitch.getIndex() + 1, i, get());
        Ins22t ins22t = (Ins22t) Opcode.IF_EQ.newInstance();
        ins22t.setRegister(0, insSwitch.getRegister());
        ins22t.setRegister(1, i);
        ins22t.setTargetIns(targetIns);
        instructionList.add(ins.getIndex() + 1, ins22t);
        ins22t.setTargetIns(targetIns);
    }

    default Ins findTargetIns() {
        InsBlockList insBlockList = getPayload().getInsBlockList();
        if (insBlockList != null) {
            return insBlockList.getAtAddress(getTargetAddress());
        }
        return null;
    }

    @Override // com.reandroid.dex.ins.PayloadEntry
    InsSwitchPayload<?> getPayload();

    @Override // com.reandroid.dex.ins.ExtraLine
    default boolean isRemoved() {
        return getPayload().isRemoved();
    }

    default void updateTargetAddress() {
        setTargetAddress(getTargetIns().getAddress());
    }
}
