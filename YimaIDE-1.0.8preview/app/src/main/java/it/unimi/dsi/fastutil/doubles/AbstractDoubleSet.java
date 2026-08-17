package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractDoubleSet extends AbstractDoubleCollection implements DoubleSet, Cloneable {
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
        return set instanceof DoubleSet ? containsAll((DoubleCollection) set) : containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int size = size();
        DoubleIterator it2 = iterator();
        int iDouble2int = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iDouble2int;
            }
            iDouble2int += HashCommon.double2int(it2.nextDouble());
            size = i;
        }
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.doubles.DoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleIterable, it.unimi.dsi.fastutil.doubles.DoubleSet, java.util.Set
    public abstract DoubleIterator iterator();

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleCollection
    @Deprecated
    public boolean rem(double d) {
        return remove(d);
    }

    @Override // it.unimi.dsi.fastutil.doubles.DoubleSet
    public boolean remove(double d) {
        return super.rem(d);
    }
}
