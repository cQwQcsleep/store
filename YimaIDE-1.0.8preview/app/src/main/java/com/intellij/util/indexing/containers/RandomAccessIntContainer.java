package com.intellij.util.indexing.containers;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface RandomAccessIntContainer {
    boolean add(int i);

    Object clone();

    IntIdsIterator intIterator();

    boolean remove(int i);
}
