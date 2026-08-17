package com.reandroid.dex.ins;

import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliPackedSwitchEntry;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PackedSwitchEntry extends IntegerItem implements SwitchEntry {
    private Ins targetIns;

    private PackedSwitchDataList getParentDataList() {
        return (PackedSwitchDataList) getParent();
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.newLine();
        smaliWriter.appendLabelName(getLabelName());
        int i = get();
        smaliWriter.appendComment(HexUtil.toHex(i, 1));
        smaliWriter.appendResourceIdComment(i);
    }

    @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
    public void appendExtra(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendLabelName(getLabelName());
        smaliWriter.appendComment(HexUtil.toHex(get(), 1));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            PackedSwitchEntry packedSwitchEntry = (PackedSwitchEntry) obj;
            if (getIndex() == packedSwitchEntry.getIndex() && getParent() == packedSwitchEntry.getParent()) {
                return true;
            }
        }
        return false;
    }

    public void fromSmali(SmaliPackedSwitchEntry smaliPackedSwitchEntry) {
        setAddress(smaliPackedSwitchEntry.getRelativeOffset().intValue());
    }

    public int get() {
        return getParentDataList().getFirstKey() + getIndex();
    }

    @Override // com.reandroid.dex.ins.Label
    public int getAddress() {
        return super.get();
    }

    @Override // com.reandroid.dex.ins.Label
    public String getLabelName() {
        return HexUtil.toHex(":pswitch_", getTargetAddress(), 1);
    }

    @Override // com.reandroid.dex.ins.SwitchEntry, com.reandroid.dex.ins.PayloadEntry
    public InsPackedSwitchData getPayload() {
        return getParentDataList().getSwitchData();
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getSortOrder() {
        return 5;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getTargetAddress() {
        return getParentDataList().getBaseAddress() + getAddress();
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public Ins getTargetIns() {
        Ins ins = this.targetIns;
        if (ins != null) {
            return ins;
        }
        setTargetIns(findTargetIns());
        return this.targetIns;
    }

    public int hashCode() {
        return getIndex();
    }

    public void merge(PackedSwitchEntry packedSwitchEntry) {
        setAddress(packedSwitchEntry.getAddress());
    }

    public void set(int i) {
        if (i != get()) {
            getParentDataList().onDataChange(getIndex(), i);
        }
    }

    public void setAddress(int i) {
        super.set(i);
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetAddress(int i) {
        setAddress(i - getParentDataList().getBaseAddress());
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetIns(Ins ins) {
        if (ins != this.targetIns) {
            this.targetIns = ins;
            if (ins != null) {
                ins.addExtraLine(this);
            }
        }
    }

    public SmaliPackedSwitchEntry toSmali() {
        SmaliPackedSwitchEntry smaliPackedSwitchEntry = new SmaliPackedSwitchEntry();
        smaliPackedSwitchEntry.getLabel().setLabelName(getLabelName());
        return smaliPackedSwitchEntry;
    }

    public String toString() {
        return getLabelName();
    }
}
