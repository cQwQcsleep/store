package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.BooleanReference;
import com.reandroid.dex.dexopt.BooleanBit;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BooleanBit extends BitItem implements BooleanReference {
    public static final Creator<BooleanBit> CREATOR = new Creator() { // from class: jy0
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new BooleanBit();
        }
    };
    private Object tag;

    public BooleanBit() {
        super(1);
    }

    @Override // com.reandroid.arsc.item.BooleanReference
    public boolean get() {
        return get(0);
    }

    public Object getTag() {
        return this.tag;
    }

    @Override // com.reandroid.arsc.item.BooleanReference
    public void set(boolean z) {
        set(0, z);
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    @Override // com.reandroid.dex.dexopt.BitItem
    public String toString() {
        return Boolean.toString(get());
    }
}
