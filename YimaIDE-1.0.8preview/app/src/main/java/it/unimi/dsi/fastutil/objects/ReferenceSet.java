package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Size64;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ReferenceSet<K> extends ReferenceCollection<K>, Set<K> {
    @Override // it.unimi.dsi.fastutil.objects.ReferenceCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    ObjectIterator<K> iterator();

    @Override // it.unimi.dsi.fastutil.objects.ReferenceCollection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    /* JADX INFO: renamed from: spliterator */
    default ObjectSpliterator<K> mo3spliterator() {
        return ObjectSpliterators.asSpliterator(iterator(), Size64.sizeOf(this), 65);
    }
}
