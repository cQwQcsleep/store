package com.reandroid.dex.ins;

import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliPayloadSparseSwitch;
import com.reandroid.dex.smali.model.SmaliSparseSwitchEntry;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArraySupplierIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsSparseSwitchData extends InsSwitchPayload<SparseSwitchEntry> {
    private final ShortItem elementCount;
    final CountedBlockList<IntegerItem> elements;
    final CountedBlockList<SparseSwitchEntryKey> keys;
    boolean mSortRequired;

    public InsSparseSwitchData() {
        super(3, Opcode.SPARSE_SWITCH_PAYLOAD);
        ShortItem shortItem = new ShortItem();
        this.elementCount = shortItem;
        CountedBlockList<IntegerItem> countedBlockList = new CountedBlockList<>(IntegerItem.CREATOR, shortItem);
        this.elements = countedBlockList;
        CountedBlockList<SparseSwitchEntryKey> countedBlockList2 = new CountedBlockList<>(SparseSwitchEntryKey.CREATOR, shortItem);
        this.keys = countedBlockList2;
        addChild(1, shortItem);
        addChild(2, countedBlockList);
        addChild(3, countedBlockList2);
    }

    @Override // com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('.');
        smaliWriter.append((CharSequence) getSmaliDirective().getName());
        int size = size();
        smaliWriter.indentPlus();
        for (int i = 0; i < size; i++) {
            get(i).append(smaliWriter);
        }
        smaliWriter.indentMinus();
        smaliWriter.newLine();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public void fromPackedSwitchData(PackedSwitchDataList packedSwitchDataList) {
        int size = packedSwitchDataList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            get(i).fromPackedSwitch(packedSwitchDataList.get(i));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public void fromSmali(SmaliInstruction smaliInstruction) throws DexException {
        validateOpcode(smaliInstruction);
        SmaliPayloadSparseSwitch smaliPayloadSparseSwitch = (SmaliPayloadSparseSwitch) smaliInstruction;
        int count = smaliPayloadSparseSwitch.getCount();
        setSize(count);
        for (int i = 0; i < count; i++) {
            get(i).fromSmali((SmaliSparseSwitchEntry) smaliPayloadSparseSwitch.getEntry(i));
        }
        this.mSortRequired = true;
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public SparseSwitchEntry get(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }
        return new SparseSwitchEntry(this, this.elements.get(i), this.keys.get(i));
    }

    public int getBaseAddress() {
        InsSparseSwitch insSparseSwitch = getSwitch();
        if (insSparseSwitch == null) {
            return 0;
        }
        return insSparseSwitch.getAddress();
    }

    @Override // com.reandroid.dex.ins.LabelsSet
    public Iterator<SparseSwitchEntry> getLabels() {
        return new ArraySupplierIterator(new ArraySupplier<SparseSwitchEntry>() { // from class: com.reandroid.dex.ins.InsSparseSwitchData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.common.ArraySupplier
            public SparseSwitchEntry get(int i) {
                return InsSparseSwitchData.this.get(i);
            }

            @Override // com.reandroid.common.CountSupplier
            public int getCount() {
                return InsSparseSwitchData.this.size();
            }
        });
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.SPARSE_SWITCH;
    }

    @Override // com.reandroid.dex.ins.InsSwitchPayload
    public InsSparseSwitch getSwitch() {
        return (InsSparseSwitch) super.getSwitch();
    }

    @Override // com.reandroid.dex.ins.InsSwitchPayload
    public Opcode<InsSparseSwitch> getSwitchOpcode() {
        return Opcode.SPARSE_SWITCH;
    }

    @Override // com.reandroid.dex.ins.PayloadData, java.lang.Iterable
    public Iterator<SparseSwitchEntry> iterator() {
        return (Iterator) ObjectsUtil.cast(getLabels());
    }

    @Override // com.reandroid.dex.ins.Ins
    public void merge(Ins ins) {
        InsSparseSwitchData insSparseSwitchData = (InsSparseSwitchData) ins;
        int size = insSparseSwitchData.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            get(i).merge(insSparseSwitchData.get(i));
        }
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void onPreRefresh() {
        sort();
        super.onPreRefresh();
    }

    public boolean remove(SwitchEntry switchEntry) {
        if (!(switchEntry instanceof SparseSwitchEntry)) {
            return false;
        }
        SparseSwitchEntry sparseSwitchEntry = (SparseSwitchEntry) switchEntry;
        Object objRequestLock = requestLock();
        boolean zRemove = this.elements.remove(sparseSwitchEntry.getElement());
        if (zRemove) {
            this.keys.remove(sparseSwitchEntry.getEntryKey());
        }
        releaseLock(objRequestLock);
        return zRemove;
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void setSize(int i) {
        Object objRequestLock = requestLock();
        this.elements.setSize(i);
        this.keys.setSize(i);
        this.elementCount.set(i);
        releaseLock(objRequestLock);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public int size() {
        return this.elements.size();
    }

    public void sort() {
        if (this.mSortRequired) {
            this.mSortRequired = false;
            Comparator comparator = new Comparator() { // from class: mq6
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return CompareUtil.compare(((IntegerItem) obj).get(), ((IntegerItem) obj2).get());
                }
            };
            if (this.elements.needsSort(comparator)) {
                this.elements.sort(comparator, this.keys);
            }
        }
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void toSmaliEntries(SmaliInstruction smaliInstruction) {
        super.toSmaliEntries(smaliInstruction);
        SmaliPayloadSparseSwitch smaliPayloadSparseSwitch = (SmaliPayloadSparseSwitch) smaliInstruction;
        int size = size();
        for (int i = 0; i < size; i++) {
            smaliPayloadSparseSwitch.addEntry(get(i).toSmali());
        }
    }
}
