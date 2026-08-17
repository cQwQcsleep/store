package it.unimi.dsi.fastutil.objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ObjectIterable<K> extends Iterable<K> {
    @Override // java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectCollection, it.unimi.dsi.fastutil.objects.ObjectIterable
    ObjectIterator<K> iterator();

    @Override // it.unimi.dsi.fastutil.objects.ObjectCollection, it.unimi.dsi.fastutil.objects.ObjectIterable
    /* JADX INFO: renamed from: spliterator, reason: merged with bridge method [inline-methods] */
    default ObjectSpliterator<K> mo3spliterator() {
        return ObjectSpliterators.asSpliteratorUnknownSize(iterator(), 0);
    }
}
