package com.reandroid.dex.smali.model;

import com.reandroid.dex.ins.InsPackedSwitch;
import com.reandroid.dex.ins.InsPackedSwitchData;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliPayloadPackedSwitch extends SmaliSwitchPayload<SmaliPackedSwitchEntry> implements SmaliRegion {
    public SmaliPayloadPackedSwitch() {
        super(new SmaliInstructionOperand.SmaliHexOperand());
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload
    public SmaliPackedSwitchEntry createEntry() {
        return new SmaliPackedSwitchEntry();
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public int getCodeUnits() {
        return ((getEntries().size() * 4) + 8) / 2;
    }

    public int getFirstKey() {
        return (int) getOperand().getValueAsLong();
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public Opcode<InsPackedSwitchData> getOpcode() {
        return Opcode.PACKED_SWITCH_PAYLOAD;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public SmaliInstructionOperand.SmaliHexOperand getOperand() {
        return (SmaliInstructionOperand.SmaliHexOperand) super.getOperand();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.PACKED_SWITCH;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSwitchPayload
    public Opcode<InsPackedSwitch> getSwitchOpcode() {
        return Opcode.PACKED_SWITCH;
    }

    public void setFirstKey(int i) {
        getOperand().setNumber(Integer.valueOf(i));
    }
}
