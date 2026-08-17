package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractLongSet extends AbstractLongCollection implements LongSet, Cloneable {
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
        return set instanceof LongSet ? containsAll((LongCollection) set) : containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int size = size();
        LongIterator it2 = iterator();
        int iLong2int = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iLong2int;
            }
            iLong2int += HashCommon.long2int(it2.nextLong());
            size = i;
        }
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.longs.LongCollection, it.unimi.dsi.fastutil.longs.LongIterable, it.unimi.dsi.fastutil.longs.LongSet, java.util.Set
    public abstract LongIterator iterator();

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongCollection, it.unimi.dsi.fastutil.longs.LongCollection
    @Deprecated
    public boolean rem(long j) {
        return remove(j);
    }

    @Override // it.unimi.dsi.fastutil.longs.LongSet
    public boolean remove(long j) {
        return super.rem(j);
    }
}
