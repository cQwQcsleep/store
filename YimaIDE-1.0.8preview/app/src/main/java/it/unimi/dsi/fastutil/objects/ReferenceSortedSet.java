package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Size64;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ReferenceSortedSet<K> extends ObjectBidirectionalIterable<K>, ReferenceSet<K>, SortedSet<K> {
    @Override // java.util.SortedSet
    ReferenceSortedSet<K> headSet(K k);

    @Override // it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterable, it.unimi.dsi.fastutil.objects.ObjectIterable, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    ObjectBidirectionalIterator<K> iterator();

    @Override // it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    /* JADX INFO: renamed from: spliterator */
    default ObjectSpliterator<K> mo3spliterator() {
        return ObjectSpliterators.asSpliteratorFromSorted(iterator(), Size64.sizeOf(this), 85, comparator());
    }

    @Override // java.util.SortedSet
    ReferenceSortedSet<K> subSet(K k, K k2);

    @Override // java.util.SortedSet
    ReferenceSortedSet<K> tailSet(K k);
}
