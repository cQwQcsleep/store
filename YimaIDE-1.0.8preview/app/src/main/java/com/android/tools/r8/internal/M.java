package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class M extends K implements InterfaceC0969Xx {
    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public abstract boolean a(int i);

    @Override // java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
        return b();
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
        if (map instanceof InterfaceC0969Xx) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
                a(interfaceC0943Wx.a(), interfaceC0943Wx.getValue());
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
                Object value = entry.getValue();
                int iIntValue = num.intValue();
                a(iIntValue);
                a(iIntValue, value);
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
            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
            sb.append(String.valueOf(interfaceC0943Wx.a()));
            sb.append("=>");
            if (this == interfaceC0943Wx.getValue()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(interfaceC0943Wx.getValue()));
            }
            size = i;
        }
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return values();
    }
}
