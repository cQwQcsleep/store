package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ReferenceSets {
    public static final EmptySet EMPTY_SET = new EmptySet();
    static final ReferenceSet UNMODIFIABLE_EMPTY_SET = unmodifiable(new ReferenceArraySet(ObjectArrays.EMPTY_ARRAY));

    public static class EmptySet<K> extends ReferenceCollections.EmptyCollection<K> implements ReferenceSet<K>, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ReferenceSets.EMPTY_SET;
        }

        public Object clone() {
            return ReferenceSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.EmptyCollection, java.util.Collection
        public boolean equals(Object obj) {
            return (obj instanceof Set) && ((Set) obj).isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    public static <K> ReferenceSet<K> unmodifiable(ReferenceSet<? extends K> referenceSet) {
        return new UnmodifiableSet(referenceSet);
    }

    public static class UnmodifiableSet<K> extends ReferenceCollections.UnmodifiableCollection<K> implements ReferenceSet<K>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        public UnmodifiableSet(ReferenceSet<? extends K> referenceSet) {
            super(referenceSet);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            return super.add(obj);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
            return super.addAll(collection);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean contains(Object obj) {
            return super.contains(obj);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean containsAll(Collection collection) {
            return super.containsAll(collection);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return this.collection.equals(obj);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.lang.Iterable
        public /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
            super.forEach(consumer);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public int hashCode() {
            return this.collection.hashCode();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean isEmpty() {
            return super.isEmpty();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, it.unimi.dsi.fastutil.objects.ReferenceCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
        public /* bridge */ /* synthetic */ ObjectIterator iterator() {
            return super.iterator();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ Stream parallelStream() {
            return super.parallelStream();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
            return super.removeAll(collection);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean removeIf(Predicate predicate) {
            return super.removeIf(predicate);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
            return super.retainAll(collection);
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, it.unimi.dsi.fastutil.objects.ReferenceCollection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ ObjectSpliterator mo3spliterator() {
            return super.mo3spliterator();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ Stream stream() {
            return super.stream();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ Object[] toArray() {
            return super.toArray();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceCollections.UnmodifiableCollection, java.util.Collection
        public /* bridge */ /* synthetic */ Object[] toArray(Object[] objArr) {
            return super.toArray(objArr);
        }
    }
}
