package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Size64;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ReferenceCollection<K> extends ObjectIterable<K>, Collection<K> {
    @Override // java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ReferenceCollection, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    ObjectIterator<K> iterator();

    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.objects.ReferenceCollection, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    /* JADX INFO: renamed from: spliterator */
    default ObjectSpliterator<K> mo3spliterator() {
        return ObjectSpliterators.asSpliterator(iterator(), Size64.sizeOf(this), 64);
    }
}
