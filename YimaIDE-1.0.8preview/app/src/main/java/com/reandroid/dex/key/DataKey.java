package com.reandroid.dex.key;

import com.reandroid.dex.data.DataItem;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DataKey<T extends DataItem> implements Key {
    private final T item;

    public DataKey(T t) {
        this.item = t;
    }

    private int getOffset() {
        return getItem().getOffset();
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return StringsUtil.compareToString(this, obj);
        }
        DataKey dataKey = (DataKey) obj;
        if (getItem().equals(dataKey.getItem())) {
            return 0;
        }
        int iCompareUnsigned = CompareUtil.compareUnsigned(getItem().getIndex(), dataKey.getItem().getIndex());
        if (iCompareUnsigned != 0) {
            return iCompareUnsigned;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.item.equals(((DataKey) obj).item);
    }

    public T getItem() {
        return this.item;
    }

    public int hashCode() {
        return this.item.hashCode();
    }

    public String toString() {
        return getOffset() + ": {" + getItem() + "}";
    }
}
