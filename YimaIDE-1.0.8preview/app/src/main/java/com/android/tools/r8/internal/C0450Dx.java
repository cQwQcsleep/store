package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0450Dx extends L implements Cloneable {
    public C0450Dx d;
    public C0450Dx e;
    public int f;

    public C0450Dx(int i, Object obj) {
        super(i, obj);
        this.f = -1073741824;
    }

    public final void a(boolean z) {
        int i = this.f;
        if (z) {
            this.f = 1073741824 | i;
        } else {
            this.f = (-1073741825) & i;
        }
    }

    public final C0450Dx b() {
        C0450Dx c0450Dx = this.e;
        if ((this.f & Integer.MIN_VALUE) == 0) {
            while ((c0450Dx.f & 1073741824) == 0) {
                c0450Dx = c0450Dx.d;
            }
        }
        return c0450Dx;
    }

    public final boolean c() {
        return (this.f & 1073741824) != 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0450Dx m9clone() {
        try {
            C0450Dx c0450Dx = (C0450Dx) super.clone();
            c0450Dx.b = this.b;
            c0450Dx.c = this.c;
            c0450Dx.f = this.f;
            return c0450Dx;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public final C0450Dx d() {
        C0450Dx c0450Dx = this.d;
        if ((this.f & 1073741824) == 0) {
            while ((c0450Dx.f & Integer.MIN_VALUE) == 0) {
                c0450Dx = c0450Dx.e;
            }
        }
        return c0450Dx;
    }

    public final boolean e() {
        return (this.f & Integer.MIN_VALUE) != 0;
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

    public C0450Dx() {
        super(0, null);
    }

    public final void a(C0450Dx c0450Dx) {
        this.f |= 1073741824;
        this.d = c0450Dx;
    }

    public final void a(int i) {
        this.f = (i & 255) | (this.f & (-256));
    }

    public final void b(C0450Dx c0450Dx) {
        this.f |= Integer.MIN_VALUE;
        this.e = c0450Dx;
    }

    public final void b(boolean z) {
        int i = this.f;
        if (z) {
            this.f = Integer.MIN_VALUE | i;
        } else {
            this.f = Integer.MAX_VALUE & i;
        }
    }
}
