package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3080y30 extends AbstractC1281d1 {
    public final /* synthetic */ B30 b;

    public C3080y30(B30 b30) {
        this.b = b30;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        B30 b30;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Long)) {
            Object key = entry.getKey();
            long jLongValue = ((Long) entry.getValue()).longValue();
            B30 b31 = this.b;
            if (key == null) {
                return b31.e && b31.c[b31.f] == jLongValue;
            }
            Object[] objArr = b31.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(key));
            B30 b32 = this.b;
            int i = iA & b32.d;
            Object obj3 = objArr[i];
            if (obj3 == null) {
                return false;
            }
            if (key == obj3) {
                return b32.c[i] == jLongValue;
            }
            do {
                b30 = this.b;
                i = (i + 1) & b30.d;
                obj2 = objArr[i];
                if (obj2 == null) {
                    return false;
                }
            } while (key != obj2);
            if (b30.c[i] == jLongValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2738u30(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Long)) {
            return false;
        }
        Object key = entry.getKey();
        long jLongValue = ((Long) entry.getValue()).longValue();
        B30 b30 = this.b;
        if (key == null) {
            if (b30.e) {
                long[] jArr = b30.c;
                int i = b30.f;
                if (jArr[i] == jLongValue) {
                    b30.e = false;
                    b30.b[i] = null;
                    int i2 = b30.h - 1;
                    b30.h = i2;
                    if (i2 < b30.g / 4 && i > 16) {
                        b30.d(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = b30.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        B30 b31 = this.b;
        int i3 = iA & b31.d;
        Object obj2 = objArr[i3];
        if (obj2 == null) {
            return false;
        }
        if (obj2 == key) {
            if (b31.c[i3] != jLongValue) {
                return false;
            }
            b31.e(i3);
            return true;
        }
        while (true) {
            B30 b32 = this.b;
            i3 = (i3 + 1) & b32.d;
            Object obj3 = objArr[i3];
            if (obj3 == null) {
                return false;
            }
            if (obj3 == key && b32.c[i3] == jLongValue) {
                b32.e(i3);
                return true;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2738u30(this.b);
    }
}
