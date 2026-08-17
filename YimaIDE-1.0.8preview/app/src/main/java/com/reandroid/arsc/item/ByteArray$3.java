package com.reandroid.arsc.item;

import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ByteArray$3 extends AbstractList<Integer> {
    final /* synthetic */ ByteArray this$0;

    public ByteArray$3(ByteArray byteArray) {
        this.this$0 = byteArray;
    }

    @Override // java.util.AbstractList, java.util.List
    public Integer get(int i) {
        return Integer.valueOf(this.this$0.getInteger(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.this$0.size() / 4;
    }
}
