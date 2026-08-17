package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class J implements InterfaceC2386px, InterfaceC1445ex, Serializable {
    public int b;

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public abstract int b(int i, int i2);

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(((Integer) obj).intValue());
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return d(((Integer) obj).intValue());
    }

    public abstract boolean d(int i);

    @Override // java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
        return h();
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

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (a(iIntValue)) {
            return Integer.valueOf(get(iIntValue));
        }
        return null;
    }

    @Override // java.util.Map
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

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        boolean zA = a(iIntValue);
        int iB = b(iIntValue, ((Integer) obj2).intValue());
        if (zA) {
            return Integer.valueOf(iB);
        }
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof InterfaceC2386px) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                InterfaceC2300ox interfaceC2300ox = (InterfaceC2300ox) it.next();
                b(interfaceC2300ox.a(), interfaceC2300ox.getIntValue());
                size = i;
            }
        } else {
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                Integer num = (Integer) entry.getKey();
                Integer num2 = (Integer) entry.getValue();
                int iIntValue = num.intValue();
                a(iIntValue);
                b(iIntValue, num2.intValue());
                size = i2;
            }
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public abstract int remove(int i);

    @Override // java.util.Map
    public final Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int iIntValue = ((Integer) obj).intValue();
        boolean zA = a(iIntValue);
        int iRemove = remove(iIntValue);
        if (zA) {
            return Integer.valueOf(iRemove);
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
            InterfaceC2300ox interfaceC2300ox = (InterfaceC2300ox) it.next();
            sb.append(String.valueOf(interfaceC2300ox.a()));
            sb.append("=>");
            sb.append(String.valueOf(interfaceC2300ox.getIntValue()));
            size = i;
        }
    }
}
