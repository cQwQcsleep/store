package it.unimi.dsi.fastutil.objects;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractReferenceSet<K> extends AbstractReferenceCollection<K> implements ReferenceSet<K>, Cloneable {
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
        return containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int size = size();
        ObjectIterator<K> it2 = iterator();
        int iIdentityHashCode = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iIdentityHashCode;
            }
            iIdentityHashCode += System.identityHashCode(it2.next());
            size = i;
        }
    }

    @Override // it.unimi.dsi.fastutil.objects.AbstractReferenceCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ReferenceCollection, it.unimi.dsi.fastutil.objects.ObjectIterable, it.unimi.dsi.fastutil.objects.ObjectCollection
    public abstract ObjectIterator<K> iterator();
}
