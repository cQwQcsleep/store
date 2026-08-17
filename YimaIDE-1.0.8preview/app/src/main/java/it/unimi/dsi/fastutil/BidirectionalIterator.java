package it.unimi.dsi.fastutil;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BidirectionalIterator<K> extends Iterator<K> {
    boolean hasPrevious();

    K previous();
}
