package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.ins.SparseSwitchEntryKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SparseSwitchEntryKey extends IntegerItem {
    public static final Creator<SparseSwitchEntryKey> CREATOR = new Creator() { // from class: akd
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new SparseSwitchEntryKey();
        }
    };
    private Ins targetIns;

    public Ins getTargetIns() {
        return this.targetIns;
    }

    public void setTargetIns(Ins ins) {
        this.targetIns = ins;
    }
}
