package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsPackedSwitch extends InsSwitch {
    private InsSparseSwitch mReplacement;

    public InsPackedSwitch() {
        super(Opcode.PACKED_SWITCH);
    }

    @Override // com.reandroid.dex.ins.Ins31t
    public String getLabelPrefix() {
        return ":pswitch_data_";
    }

    @Override // com.reandroid.dex.ins.InsSwitch
    public InsPackedSwitchData getPayload() {
        return (InsPackedSwitchData) super.getPayload();
    }

    @Override // com.reandroid.dex.ins.InsSwitch
    public Opcode<InsPackedSwitchData> getPayloadOpcode() {
        return Opcode.PACKED_SWITCH_PAYLOAD;
    }

    public InsSparseSwitch getSparseSwitchReplacement() {
        InsSparseSwitch insSparseSwitch = this.mReplacement;
        if (insSparseSwitch != null) {
            return insSparseSwitch;
        }
        int register = getRegister();
        int targetAddress = getTargetAddress();
        InsSparseSwitch insSparseSwitch2 = (InsSparseSwitch) replace(Opcode.SPARSE_SWITCH);
        this.mReplacement = insSparseSwitch2;
        insSparseSwitch2.setRegister(register);
        insSparseSwitch2.clearExtraLines();
        insSparseSwitch2.setTargetAddress(targetAddress);
        return insSparseSwitch2;
    }
}
