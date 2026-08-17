package com.reandroid.dex.ins;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliSparseSwitchEntry;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SparseSwitchEntry implements SwitchEntry {
    private final IntegerReference element;
    private final SparseSwitchEntryKey entryKey;
    private final InsSparseSwitchData payload;

    public SparseSwitchEntry(InsSparseSwitchData insSparseSwitchData, IntegerReference integerReference, SparseSwitchEntryKey sparseSwitchEntryKey) {
        this.payload = insSparseSwitchData;
        this.element = integerReference;
        this.entryKey = sparseSwitchEntryKey;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.newLine();
        int i = get();
        smaliWriter.appendHex(i);
        smaliWriter.append(" -> ");
        smaliWriter.appendLabelName(getLabelName());
        smaliWriter.appendResourceIdComment(i);
    }

    @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
    public void appendExtra(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendLabelName(getLabelName());
        smaliWriter.appendComment(HexUtil.toSignedHex(get()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SparseSwitchEntry sparseSwitchEntry = (SparseSwitchEntry) obj;
            if (this.element == sparseSwitchEntry.element && this.payload == sparseSwitchEntry.payload) {
                return true;
            }
        }
        return false;
    }

    public void fromPackedSwitch(PackedSwitchEntry packedSwitchEntry) {
        set(packedSwitchEntry.get());
        Ins targetIns = packedSwitchEntry.getTargetIns();
        setTargetAddress(targetIns.getAddress());
        setTargetIns(targetIns);
        targetIns.addExtraLine(this);
    }

    public void fromSmali(SmaliSparseSwitchEntry smaliSparseSwitchEntry) {
        set(smaliSparseSwitchEntry.getValue());
        setKey(smaliSparseSwitchEntry.getRelativeOffset().intValue());
    }

    public int get() {
        return this.element.get();
    }

    @Override // com.reandroid.dex.ins.Label
    public int getAddress() {
        return this.payload.getAddress();
    }

    public IntegerReference getElement() {
        return this.element;
    }

    public SparseSwitchEntryKey getEntryKey() {
        return this.entryKey;
    }

    @Override // com.reandroid.dex.ins.PayloadEntry
    public int getIndex() {
        return this.entryKey.getIndex();
    }

    public int getKey() {
        return this.entryKey.get();
    }

    @Override // com.reandroid.dex.ins.Label
    public String getLabelName() {
        return HexUtil.toHex(":sswitch_", getTargetAddress(), 1);
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getSortOrder() {
        return 5;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getTargetAddress() {
        return getKey() + this.payload.getBaseAddress();
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public Ins getTargetIns() {
        Ins targetIns = this.entryKey.getTargetIns();
        if (targetIns != null) {
            return targetIns;
        }
        setTargetIns(findTargetIns());
        return this.entryKey.getTargetIns();
    }

    public int hashCode() {
        return ObjectsUtil.hash(this.payload, this.element);
    }

    public void merge(SparseSwitchEntry sparseSwitchEntry) {
        set(sparseSwitchEntry.get());
        setKey(sparseSwitchEntry.getKey());
    }

    public void removeSelf() {
        this.payload.remove(this);
    }

    public void set(int i) {
        if (i != this.element.get()) {
            this.element.set(i);
            this.payload.mSortRequired = true;
        }
    }

    public void setKey(int i) {
        this.entryKey.set(i);
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetAddress(int i) {
        setKey(i - this.payload.getBaseAddress());
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetIns(Ins ins) {
        if (ins != this.entryKey.getTargetIns()) {
            this.entryKey.setTargetIns(ins);
            if (ins != null) {
                ins.addExtraLine(this);
            }
        }
    }

    public SmaliSparseSwitchEntry toSmali() {
        SmaliSparseSwitchEntry smaliSparseSwitchEntry = new SmaliSparseSwitchEntry();
        smaliSparseSwitchEntry.getLabel().setLabelName(getLabelName());
        smaliSparseSwitchEntry.setValue(get());
        return smaliSparseSwitchEntry;
    }

    public String toString() {
        return HexUtil.toHex8(get()) + " -> " + getKey();
    }

    @Override // com.reandroid.dex.ins.SwitchEntry, com.reandroid.dex.ins.PayloadEntry
    public InsSparseSwitchData getPayload() {
        return this.payload;
    }
}
