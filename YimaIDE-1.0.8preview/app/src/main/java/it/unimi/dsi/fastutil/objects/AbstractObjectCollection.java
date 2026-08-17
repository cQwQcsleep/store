package it.unimi.dsi.fastutil.objects;

import java.util.AbstractCollection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractObjectCollection<K> extends AbstractCollection<K> implements ObjectCollection<K> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.objects.ObjectCollection, it.unimi.dsi.fastutil.objects.ObjectIterable
    public abstract ObjectIterator<K> iterator();

    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        ObjectIterator<K> it2 = iterator();
        int size = size();
        boolean z = true;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            K next = it2.next();
            if (this == next) {
                sb.append("(this collection)");
            } else {
                sb.append(String.valueOf(next));
            }
            size = i;
        }
    }
}
