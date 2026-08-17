package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ny, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2215ny extends L implements Cloneable {
    public C2215ny d;
    public C2215ny e;
    public int f;

    public C2215ny(int i, Object obj) {
        super(i, obj);
        this.f = -1073741824;
    }

    public final void a(boolean z) {
        int i = this.f;
        if (z) {
            this.f = i | 1;
        } else {
            this.f = i & (-2);
        }
    }

    public final void b(boolean z) {
        int i = this.f;
        if (z) {
            this.f = 1073741824 | i;
        } else {
            this.f = (-1073741825) & i;
        }
    }

    public final C2215ny c() {
        C2215ny c2215ny = this.e;
        if ((this.f & Integer.MIN_VALUE) == 0) {
            while ((c2215ny.f & 1073741824) == 0) {
                c2215ny = c2215ny.d;
            }
        }
        return c2215ny;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C2215ny m17clone() {
        try {
            C2215ny c2215ny = (C2215ny) super.clone();
            c2215ny.b = this.b;
            c2215ny.c = this.c;
            c2215ny.f = this.f;
            return c2215ny;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public final boolean d() {
        return (this.f & 1073741824) != 0;
    }

    public final C2215ny e() {
        C2215ny c2215ny = this.d;
        if ((this.f & 1073741824) == 0) {
            while ((c2215ny.f & Integer.MIN_VALUE) == 0) {
                c2215ny = c2215ny.e;
            }
        }
        return c2215ny;
    }

    @Override // com.android.tools.r8.internal.L, java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (this.b == ((Integer) entry.getKey()).intValue()) {
            Object obj2 = this.c;
            if (obj2 == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (obj2.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return (this.f & Integer.MIN_VALUE) != 0;
    }

    @Override // com.android.tools.r8.internal.L, java.util.Map.Entry
    public final int hashCode() {
        int i = this.b;
        Object obj = this.c;
        return (obj == null ? 0 : obj.hashCode()) ^ i;
    }

    @Override // com.android.tools.r8.internal.L, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.c;
        this.c = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.L
    public final String toString() {
        return this.b + "=>" + this.c;
    }

    public C2215ny() {
        super(0, null);
    }

    public final void a(C2215ny c2215ny) {
        this.f |= 1073741824;
        this.d = c2215ny;
    }

    public final void b(C2215ny c2215ny) {
        this.f |= Integer.MIN_VALUE;
        this.e = c2215ny;
    }

    public final boolean b() {
        return (this.f & 1) != 0;
    }
}
