package com.reandroid.arsc.model;

import com.reandroid.arsc.value.Entry;
import com.reandroid.utils.collection.IterableIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResourceEntry$1 extends IterableIterator<Entry, String> {
    final /* synthetic */ ResourceEntry this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResourceEntry$1(ResourceEntry resourceEntry, Iterator it) {
        super(it);
        this.this$0 = resourceEntry;
    }

    public Iterator<String> iterator(Entry entry) {
        return this.this$0.getStringValues(entry);
    }
}
