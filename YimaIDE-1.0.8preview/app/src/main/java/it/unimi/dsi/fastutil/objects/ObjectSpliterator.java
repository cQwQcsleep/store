package it.unimi.dsi.fastutil.objects;

import java.util.Spliterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ObjectSpliterator<K> extends Spliterator<K> {
    @Override // java.util.Spliterator
    ObjectSpliterator<K> trySplit();
}
