package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Size64;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ObjectSet<K> extends ObjectCollection<K>, Set<K> {
    @Override // it.unimi.dsi.fastutil.objects.ObjectCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable
    ObjectIterator<K> iterator();

    @Override // it.unimi.dsi.fastutil.objects.ObjectCollection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable
    /* JADX INFO: renamed from: spliterator */
    default ObjectSpliterator<K> mo3spliterator() {
        return ObjectSpliterators.asSpliterator(iterator(), Size64.sizeOf(this), 65);
    }
}
