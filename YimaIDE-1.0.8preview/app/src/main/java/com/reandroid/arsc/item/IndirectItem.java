package com.reandroid.arsc.item;

import com.reandroid.arsc.item.BlockItem;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IndirectItem<T extends BlockItem> {
    private final T blockItem;
    private final int offset;

    public IndirectItem(T t, int i) {
        this.blockItem = t;
        this.offset = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            IndirectItem indirectItem = (IndirectItem) obj;
            if (getOffset() == indirectItem.getOffset() && getBlockItem() == indirectItem.getBlockItem()) {
                return true;
            }
        }
        return false;
    }

    public T getBlockItem() {
        return this.blockItem;
    }

    public byte[] getBytesInternal() {
        return this.blockItem.getBytesInternal();
    }

    public int getOffset() {
        return this.offset;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getOffset()), getBlockItem());
    }
}
