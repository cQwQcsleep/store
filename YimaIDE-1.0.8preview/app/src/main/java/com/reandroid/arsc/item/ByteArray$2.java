package com.reandroid.arsc.item;

import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ByteArray$2 extends AbstractList<Short> {
    final /* synthetic */ ByteArray this$0;

    public ByteArray$2(ByteArray byteArray) {
        this.this$0 = byteArray;
    }

    @Override // java.util.AbstractList, java.util.List
    public Short get(int i) {
        return Short.valueOf(this.this$0.getShort(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.this$0.size() / 2;
    }
}
