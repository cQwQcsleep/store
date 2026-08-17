package it.unimi.dsi.fastutil.objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractReferenceSortedSet<K> extends AbstractReferenceSet<K> implements ReferenceSortedSet<K> {
    @Override // it.unimi.dsi.fastutil.objects.AbstractReferenceSet, it.unimi.dsi.fastutil.objects.AbstractReferenceCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ReferenceCollection, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    public abstract ObjectBidirectionalIterator<K> iterator();
}
