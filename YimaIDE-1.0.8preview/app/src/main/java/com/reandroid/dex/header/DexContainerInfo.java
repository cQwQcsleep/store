package com.reandroid.dex.header;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexContainerInfo extends CountAndOffsetV41 implements BlockRefresh {
    public int getFileSize() {
        return getCount();
    }

    public IntegerReference getOffsetReference() {
        return getSecond();
    }

    public void refresh() {
        DexHeader dexHeader;
        if (isNull() || (dexHeader = (DexHeader) getParentInstance(DexHeader.class)) == null) {
            return;
        }
        setFileSize(dexHeader.getFileSize());
        setOffset(dexHeader.getOffset());
    }

    public void setFileSize(int i) {
        super.setCount(i);
    }

    @Override // com.reandroid.dex.base.BlockIntegerPair
    public String toString() {
        return isNull() ? "(--, --)" : super.toString();
    }
}
