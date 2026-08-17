package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class W0 implements SortedMap, InterfaceC2294or, Serializable, Map {
    public Object b;

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract W0 headMap(Object obj);

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract W0 subMap(Object obj, Object obj2);

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public abstract W0 tailMap(Object obj);

    @Override // java.util.Map
    public abstract void clear();

    @Override // java.util.SortedMap
    public abstract Comparator comparator();

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public abstract boolean containsKey(Object obj);

    @Override // java.util.SortedMap, java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public abstract NU keySet();

    @Override // java.util.SortedMap, java.util.Map
    public final Set entrySet() {
        return i();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        return i().containsAll(map.entrySet());
    }

    @Override // java.util.Map
    public int hashCode() {
        int size = size();
        BU it = i().iterator();
        int iHashCode = 0;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) it.next()).hashCode();
            size = i;
        }
    }

    public abstract NU i();

    @Override // java.util.Map
    public abstract Object put(Object obj, Object obj2);

    @Override // java.util.Map
    public void putAll(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof W0) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                OT ot = (OT) it.next();
                put(ot.getKey(), ot.getValue());
                size = i;
            }
        } else {
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                put(entry.getKey(), entry.getValue());
                size = i2;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        BU it = i().iterator();
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
            OT ot = (OT) it.next();
            if (this == ot.getKey()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(ot.getKey()));
            }
            sb.append("=>");
            if (this == ot.getValue()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(ot.getValue()));
            }
            size = i;
        }
    }
}
