package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.ins.PackedSwitchEntry;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliInstructionOperand;
import com.reandroid.dex.smali.model.SmaliPayloadPackedSwitch;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsPackedSwitchData extends InsSwitchPayload<PackedSwitchEntry> {
    public static final Creator<PackedSwitchEntry> CREATOR = new Creator() { // from class: lq6
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new PackedSwitchEntry();
        }
    };
    private final PackedSwitchDataList elements;
    private final IntegerItem firstKey;
    private InsSparseSwitchData mReplacement;

    public InsPackedSwitchData() {
        super(3, Opcode.PACKED_SWITCH_PAYLOAD);
        ShortItem shortItem = new ShortItem();
        IntegerItem integerItem = new IntegerItem();
        this.firstKey = integerItem;
        PackedSwitchDataList packedSwitchDataList = new PackedSwitchDataList(this, shortItem);
        this.elements = packedSwitchDataList;
        addChild(1, shortItem);
        addChild(2, integerItem);
        addChild(3, packedSwitchDataList);
    }

    @Override // com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        smaliWriter.append((CharSequence) HexUtil.toHex(this.firstKey.get(), 1));
        smaliWriter.indentPlus();
        this.elements.append(smaliWriter);
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public void fromSmali(SmaliInstruction smaliInstruction) throws DexException {
        validateOpcode(smaliInstruction);
        SmaliPayloadPackedSwitch smaliPayloadPackedSwitch = (SmaliPayloadPackedSwitch) smaliInstruction;
        setFirstKey(smaliPayloadPackedSwitch.getFirstKey());
        this.elements.fromSmali(smaliPayloadPackedSwitch);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.PayloadData
    public PackedSwitchEntry get(int i) {
        return (PackedSwitchEntry) this.elements.get(i);
    }

    public int getFirstKey() {
        return this.firstKey.get();
    }

    @Override // com.reandroid.dex.ins.LabelsSet
    public Iterator<PackedSwitchEntry> getLabels() {
        return this.elements.getLabels();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.PACKED_SWITCH;
    }

    @Override // com.reandroid.dex.ins.InsSwitchPayload
    public InsPackedSwitch getSwitch() {
        return (InsPackedSwitch) super.getSwitch();
    }

    @Override // com.reandroid.dex.ins.InsSwitchPayload
    public Opcode<InsPackedSwitch> getSwitchOpcode() {
        return Opcode.PACKED_SWITCH;
    }

    @Override // com.reandroid.dex.ins.PayloadData, java.lang.Iterable
    public Iterator<PackedSwitchEntry> iterator() {
        return (Iterator) ObjectsUtil.cast(this.elements.iterator());
    }

    @Override // com.reandroid.dex.ins.Ins
    public void merge(Ins ins) {
        InsPackedSwitchData insPackedSwitchData = (InsPackedSwitchData) ins;
        setFirstKey(insPackedSwitchData.getFirstKey());
        this.elements.merge(insPackedSwitchData.elements);
    }

    public void onDataChange(int i, int i2) {
        replaceBySparse().get(i).set(i2);
    }

    public InsSparseSwitchData replaceBySparse() {
        InsSparseSwitchData insSparseSwitchData = this.mReplacement;
        if (insSparseSwitchData != null) {
            return insSparseSwitchData;
        }
        Object objRequestLock = requestLock();
        InsSparseSwitch sparseSwitchReplacement = getSwitch().getSparseSwitchReplacement();
        InsSparseSwitchData insSparseSwitchData2 = (InsSparseSwitchData) Opcode.SPARSE_SWITCH_PAYLOAD.newInstance();
        this.mReplacement = insSparseSwitchData2;
        insSparseSwitchData2.setSwitch(sparseSwitchReplacement);
        insSparseSwitchData2.fromPackedSwitchData(this.elements);
        replace(insSparseSwitchData2);
        releaseLock(objRequestLock);
        return insSparseSwitchData2;
    }

    public void setFirstKey(int i) {
        this.firstKey.set(i);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void setSize(int i) {
        Object objRequestLock = requestLock();
        this.elements.setSize(i);
        releaseLock(objRequestLock);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public int size() {
        return this.elements.size();
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void toSmaliEntries(SmaliInstruction smaliInstruction) {
        super.toSmaliEntries(smaliInstruction);
        this.elements.toSmali((SmaliPayloadPackedSwitch) smaliInstruction);
    }

    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public void toSmaliOperand(SmaliInstruction smaliInstruction) {
        super.toSmaliOperand(smaliInstruction);
        ((SmaliInstructionOperand.SmaliHexOperand) smaliInstruction.getOperand()).setNumber(Integer.valueOf(getFirstKey()));
    }

    @Override // com.reandroid.dex.ins.Ins
    public String toString() {
        return "InsPackedSwitchData{, firstKey=" + this.firstKey + ", elements=" + this.elements + '}';
    }
}
