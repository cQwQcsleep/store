package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S extends P implements InterfaceC2045lz {
    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public abstract boolean a(int i);

    @Override // java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
        return c();
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
    public void putAll(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof InterfaceC2045lz) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
                a(interfaceC1959kz.a(), interfaceC1959kz.getValue());
                size = i;
            }
        } else {
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                put((Integer) entry.getKey(), entry.getValue());
                size = i2;
            }
        }
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
            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
            sb.append(String.valueOf(interfaceC1959kz.a()));
            sb.append("=>");
            if (this == interfaceC1959kz.getValue()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(interfaceC1959kz.getValue()));
            }
            size = i;
        }
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return values();
    }
}
