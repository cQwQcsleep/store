package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Iy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0581Iy extends Q implements Cloneable {
    public C0581Iy d;
    public C0581Iy e;
    public int f;

    public C0581Iy(int i, Object obj) {
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

    public final C0581Iy b() {
        C0581Iy c0581Iy = this.e;
        if ((this.f & Integer.MIN_VALUE) == 0) {
            while ((c0581Iy.f & 1073741824) == 0) {
                c0581Iy = c0581Iy.d;
            }
        }
        return c0581Iy;
    }

    public final boolean c() {
        return (this.f & 1073741824) != 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0581Iy m12clone() {
        try {
            C0581Iy c0581Iy = (C0581Iy) super.clone();
            c0581Iy.b = this.b;
            c0581Iy.c = this.c;
            c0581Iy.f = this.f;
            return c0581Iy;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public final C0581Iy d() {
        C0581Iy c0581Iy = this.d;
        if ((this.f & 1073741824) == 0) {
            while ((c0581Iy.f & Integer.MIN_VALUE) == 0) {
                c0581Iy = c0581Iy.e;
            }
        }
        return c0581Iy;
    }

    public final boolean e() {
        return (this.f & Integer.MIN_VALUE) != 0;
    }

    @Override // com.android.tools.r8.internal.Q, java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.b == ((Integer) entry.getKey()).intValue() && this.c == entry.getValue();
    }

    @Override // com.android.tools.r8.internal.Q, java.util.Map.Entry
    public final int hashCode() {
        int i = this.b;
        Object obj = this.c;
        return (obj == null ? 0 : System.identityHashCode(obj)) ^ i;
    }

    @Override // com.android.tools.r8.internal.Q, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.c;
        this.c = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.Q
    public final String toString() {
        return this.b + "=>" + this.c;
    }

    public C0581Iy() {
        super(0, null);
    }

    public final void a(C0581Iy c0581Iy) {
        this.f |= 1073741824;
        this.d = c0581Iy;
    }

    public final void a(int i) {
        this.f = (i & 255) | (this.f & (-256));
    }

    public final void b(C0581Iy c0581Iy) {
        this.f |= Integer.MIN_VALUE;
        this.e = c0581Iy;
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
