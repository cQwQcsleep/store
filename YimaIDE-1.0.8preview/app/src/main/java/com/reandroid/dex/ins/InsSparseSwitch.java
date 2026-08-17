package com.reandroid.dex.ins;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsSparseSwitch extends InsSwitch {
    public InsSparseSwitch() {
        super(Opcode.SPARSE_SWITCH);
    }

    @Override // com.reandroid.dex.ins.Ins31t
    public String getLabelPrefix() {
        return ":sswitch_data_";
    }

    @Override // com.reandroid.dex.ins.InsSwitch
    public InsSparseSwitchData getPayload() {
        return (InsSparseSwitchData) super.getPayload();
    }

    @Override // com.reandroid.dex.ins.InsSwitch
    public Opcode<InsSparseSwitchData> getPayloadOpcode() {
        return Opcode.SPARSE_SWITCH_PAYLOAD;
    }

    @Override // com.reandroid.dex.ins.Ins31t, com.reandroid.dex.ins.ExtraLine
    public void setTargetAddress(int i) {
        setData(i - getAddress());
    }
}
