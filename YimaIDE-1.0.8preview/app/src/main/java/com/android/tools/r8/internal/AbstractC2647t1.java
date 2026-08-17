package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2647t1 implements Serializable, Map, InterfaceC2294or {
    public int b;

    public abstract int b(int i, Object obj);

    public abstract int b(Object obj);

    public abstract int c(Object obj);

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return d(((Integer) obj).intValue());
    }

    public abstract boolean d(int i);

    @Override // java.util.Map, java.util.SortedMap
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
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
        return entrySet().containsAll(map.entrySet());
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return Integer.valueOf(b(obj));
        }
        return null;
    }

    public int hashCode() {
        int size = size();
        BU it = entrySet().iterator();
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

    public abstract JU i();

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        boolean zContainsKey = containsKey(obj);
        int iB = b(((Integer) obj2).intValue(), obj);
        if (zContainsKey) {
            return Integer.valueOf(iB);
        }
        return null;
    }

    public void putAll(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof AbstractC2647t1) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                InterfaceC1713i30 interfaceC1713i30 = (InterfaceC1713i30) it.next();
                b(interfaceC1713i30.getIntValue(), interfaceC1713i30.getKey());
                size = i;
            }
        } else {
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Integer num = (Integer) entry.getValue();
                containsKey(key);
                b(num.intValue(), key);
                size = i2;
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        boolean zContainsKey = containsKey(obj);
        int iC = c(obj);
        if (zContainsKey) {
            return Integer.valueOf(iC);
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        BU it = entrySet().iterator();
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
            InterfaceC1713i30 interfaceC1713i30 = (InterfaceC1713i30) it.next();
            if (this == interfaceC1713i30.getKey()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(interfaceC1713i30.getKey()));
            }
            sb.append("=>");
            sb.append(String.valueOf(interfaceC1713i30.getIntValue()));
            size = i;
        }
    }
}
