package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ReferenceSortedSets {
    public static final EmptySet EMPTY_SET = new EmptySet();

    public static class EmptySet<K> extends ReferenceSets.EmptySet<K> implements ReferenceSortedSet<K>, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ReferenceSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceSets.EmptySet
        public Object clone() {
            return ReferenceSortedSets.EMPTY_SET;
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return null;
        }

        @Override // java.util.SortedSet
        public K first() {
            throw new NoSuchElementException();
        }

        @Override // java.util.SortedSet
        public K last() {
            throw new NoSuchElementException();
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceSortedSet, java.util.SortedSet
        public ReferenceSortedSet<K> headSet(K k) {
            return ReferenceSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceSortedSet, java.util.SortedSet
        public ReferenceSortedSet<K> subSet(K k, K k2) {
            return ReferenceSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ReferenceSortedSet, java.util.SortedSet
        public ReferenceSortedSet<K> tailSet(K k) {
            return ReferenceSortedSets.EMPTY_SET;
        }
    }
}
