package com.reandroid.dex.ins;

import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliPackedSwitchEntry;
import com.reandroid.dex.smali.model.SmaliPayloadPackedSwitch;
import com.reandroid.dex.smali.model.SmaliSet;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PackedSwitchDataList extends CountedBlockList<PackedSwitchEntry> implements SmaliFormat, LabelsSet {
    private final InsPackedSwitchData switchData;

    public PackedSwitchDataList(InsPackedSwitchData insPackedSwitchData, IntegerReference integerReference) {
        super(InsPackedSwitchData.CREATOR, integerReference);
        this.switchData = insPackedSwitchData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        for (int i = 0; i < size; i++) {
            ((PackedSwitchEntry) get(i)).append(smaliWriter);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void fromSmali(SmaliPayloadPackedSwitch smaliPayloadPackedSwitch) {
        SmaliSet<T> entries = smaliPayloadPackedSwitch.getEntries();
        int size = entries.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((PackedSwitchEntry) get(i)).fromSmali((SmaliPackedSwitchEntry) entries.get(i));
        }
    }

    public int getBaseAddress() {
        InsPackedSwitch insPackedSwitch = this.switchData.getSwitch();
        if (insPackedSwitch == null) {
            return 0;
        }
        return insPackedSwitch.getAddress();
    }

    public int getFirstKey() {
        return this.switchData.getFirstKey();
    }

    @Override // com.reandroid.dex.ins.LabelsSet
    public Iterator<PackedSwitchEntry> getLabels() {
        return iterator();
    }

    public InsPackedSwitchData getSwitchData() {
        return this.switchData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void merge(PackedSwitchDataList packedSwitchDataList) {
        int size = packedSwitchDataList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((PackedSwitchEntry) get(i)).merge((PackedSwitchEntry) packedSwitchDataList.get(i));
        }
    }

    public void onDataChange(int i, int i2) {
        this.switchData.onDataChange(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void toSmali(SmaliPayloadPackedSwitch smaliPayloadPackedSwitch) {
        int size = size();
        for (int i = 0; i < size; i++) {
            smaliPayloadPackedSwitch.addEntry(((PackedSwitchEntry) get(i)).toSmali());
        }
    }
}
