package com.reandroid.dex.smali.model;

import com.reandroid.dex.ins.InsSparseSwitch;
import com.reandroid.dex.ins.InsSparseSwitchData;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.smali.SmaliDirective;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliPayloadSparseSwitch extends SmaliSwitchPayload<SmaliSparseSwitchEntry> {
    public SmaliPayloadSparseSwitch() {
        super(SmaliInstructionOperand.NO_OPERAND);
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload
    public SmaliSparseSwitchEntry createEntry() {
        return new SmaliSparseSwitchEntry();
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public int getCodeUnits() {
        return ((getCount() * 8) + 4) / 2;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public Opcode<InsSparseSwitchData> getOpcode() {
        return Opcode.SPARSE_SWITCH_PAYLOAD;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.SPARSE_SWITCH;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSwitchPayload
    public Opcode<InsSparseSwitch> getSwitchOpcode() {
        return Opcode.SPARSE_SWITCH;
    }
}
