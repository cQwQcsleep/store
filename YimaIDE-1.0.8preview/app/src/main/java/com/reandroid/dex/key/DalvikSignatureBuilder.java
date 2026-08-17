package com.reandroid.dex.key;

import com.reandroid.utils.collection.ArrayCollection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikSignatureBuilder {
    private boolean flushMarked;
    private final ArrayCollection<StringKey> results = new ArrayCollection<>();
    private final StringBuilder stringBuilder = new StringBuilder();

    public void append(char c) {
        this.stringBuilder.append(c);
    }

    public ArrayValueKey build() {
        ArrayCollection<StringKey> arrayCollection = this.results;
        return ArrayValueKey.of((Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]));
    }

    public void flush() {
        this.flushMarked = false;
        StringBuilder sb = this.stringBuilder;
        int length = sb.length();
        if (length == 0) {
            return;
        }
        this.results.add(StringKey.create(sb.toString()));
        sb.delete(0, length);
    }

    public void flushPending() {
        if (this.flushMarked) {
            flush();
        }
    }

    public StringBuilder getStringBuilder() {
        return this.stringBuilder;
    }

    public void markFlush() {
        this.flushMarked = true;
    }

    public void append(String str) {
        this.stringBuilder.append(str);
    }
}
