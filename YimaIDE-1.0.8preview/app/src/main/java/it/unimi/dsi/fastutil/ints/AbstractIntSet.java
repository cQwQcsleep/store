package it.unimi.dsi.fastutil.ints;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractIntSet extends AbstractIntCollection implements IntSet, Cloneable {
    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != size()) {
            return false;
        }
        return set instanceof IntSet ? containsAll((IntCollection) set) : containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int size = size();
        IntIterator it2 = iterator();
        int iNextInt = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iNextInt;
            }
            iNextInt += it2.nextInt();
            size = i;
        }
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.ints.IntCollection, it.unimi.dsi.fastutil.ints.IntIterable, it.unimi.dsi.fastutil.ints.IntSet, java.util.Set
    public abstract IntIterator iterator();

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntCollection, it.unimi.dsi.fastutil.ints.IntCollection
    @Deprecated
    public boolean rem(int i) {
        return remove(i);
    }

    @Override // it.unimi.dsi.fastutil.ints.IntSet
    public boolean remove(int i) {
        return super.rem(i);
    }
}
