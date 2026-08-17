package com.reandroid.dex.ins;

import com.reandroid.dex.data.InstructionList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class InsSwitch extends Ins31t {
    private InsSwitchPayload switchPayload;

    public InsSwitch(Opcode<?> opcode) {
        super(opcode);
    }

    private InsSwitchPayload findByAddress() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null) {
            return null;
        }
        Iterator it = instructionList.iterator(getPayloadOpcode());
        int targetAddress = getTargetAddress();
        while (it.hasNext()) {
            InsSwitchPayload insSwitchPayload = (InsSwitchPayload) it.next();
            if (insSwitchPayload.getAddress() == targetAddress) {
                return insSwitchPayload;
            }
        }
        return null;
    }

    public InsSwitchPayload getPayload() {
        InsSwitchPayload insSwitchPayloadFindByAddress = this.switchPayload;
        if (insSwitchPayloadFindByAddress == null && (insSwitchPayloadFindByAddress = findByAddress()) != null) {
            this.switchPayload = insSwitchPayloadFindByAddress;
            insSwitchPayloadFindByAddress.setSwitch(this);
        }
        return insSwitchPayloadFindByAddress;
    }

    public abstract Opcode<? extends InsSwitchPayload> getPayloadOpcode();

    public void setPayload(InsSwitchPayload insSwitchPayload) {
        this.switchPayload = insSwitchPayload;
        setTargetAddress(insSwitchPayload.getAddress());
    }
}
