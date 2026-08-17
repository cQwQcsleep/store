package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ObjectSortedSets {
    public static final EmptySet EMPTY_SET = new EmptySet();

    public static class EmptySet<K> extends ObjectSets.EmptySet<K> implements ObjectSortedSet<K>, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ObjectSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSets.EmptySet
        public Object clone() {
            return ObjectSortedSets.EMPTY_SET;
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

        @Override // it.unimi.dsi.fastutil.objects.ObjectSortedSet, java.util.SortedSet
        public ObjectSortedSet<K> headSet(K k) {
            return ObjectSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSortedSet, java.util.SortedSet
        public ObjectSortedSet<K> subSet(K k, K k2) {
            return ObjectSortedSets.EMPTY_SET;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSortedSet, java.util.SortedSet
        public ObjectSortedSet<K> tailSet(K k) {
            return ObjectSortedSets.EMPTY_SET;
        }
    }
}
