package org.antlr.v4.runtime.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class MultiMap<K, V> extends LinkedHashMap<K, List<V>> {
    public List<Pair<K, V>> getPairs() {
        ArrayList arrayList = new ArrayList();
        for (K k : keySet()) {
            Iterator it = ((List) get(k)).iterator();
            while (it.hasNext()) {
                arrayList.add(new Pair(k, it.next()));
            }
        }
        return arrayList;
    }

    public void map(K k, V v) {
        List arrayList = (List) get(k);
        if (arrayList == null) {
            arrayList = new ArrayList();
            super.put(k, arrayList);
        }
        arrayList.add(v);
    }
}
