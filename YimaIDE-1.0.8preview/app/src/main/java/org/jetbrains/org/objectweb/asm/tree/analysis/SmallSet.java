package org.jetbrains.org.objectweb.asm.tree.analysis;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class SmallSet<T> extends AbstractSet<T> {
    private final T element1;
    private final T element2;

    public static class IteratorImpl<T> implements Iterator<T> {
        private T firstElement;
        private T secondElement;

        public IteratorImpl(T t, T t2) {
            this.firstElement = t;
            this.secondElement = t2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.firstElement != null;
        }

        @Override // java.util.Iterator
        public T next() {
            T t = this.firstElement;
            if (t == null) {
                z0e.a();
                return null;
            }
            this.firstElement = this.secondElement;
            this.secondElement = null;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public SmallSet() {
        this.element1 = null;
        this.element2 = null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<T> iterator() {
        return new IteratorImpl(this.element1, this.element2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        if (this.element1 == null) {
            return 0;
        }
        return this.element2 == null ? 1 : 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0029, code lost:
    
        if (r0 != r2) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Set<T> union(SmallSet<T> smallSet) {
        T t;
        T t2 = smallSet.element1;
        T t3 = this.element1;
        if ((t2 != t3 || smallSet.element2 != this.element2) && ((t2 != (t = this.element2) || smallSet.element2 != t3) && t2 != null)) {
            if (t3 != null) {
                T t4 = smallSet.element2;
                if (t4 == null) {
                    if (t == null) {
                        return new SmallSet(t3, t2);
                    }
                    if (t2 != t3) {
                    }
                }
                if (t != null || (t3 != t2 && t3 != t4)) {
                    HashSet hashSet = new HashSet(4);
                    hashSet.add(this.element1);
                    T t5 = this.element2;
                    if (t5 != null) {
                        hashSet.add(t5);
                    }
                    hashSet.add(smallSet.element1);
                    T t6 = smallSet.element2;
                    if (t6 != null) {
                        hashSet.add(t6);
                    }
                    return hashSet;
                }
            }
            return smallSet;
        }
        return this;
    }

    public SmallSet(T t) {
        this.element1 = t;
        this.element2 = null;
    }

    private SmallSet(T t, T t2) {
        this.element1 = t;
        this.element2 = t2;
    }
}
