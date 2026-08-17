package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Az, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0374Az extends Q implements Cloneable {
    public C0374Az d;
    public C0374Az e;
    public int f;

    public C0374Az(int i, Object obj) {
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

    public final C0374Az c() {
        C0374Az c0374Az = this.e;
        if ((this.f & Integer.MIN_VALUE) == 0) {
            while ((c0374Az.f & 1073741824) == 0) {
                c0374Az = c0374Az.d;
            }
        }
        return c0374Az;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final C0374Az m8clone() {
        try {
            C0374Az c0374Az = (C0374Az) super.clone();
            c0374Az.b = this.b;
            c0374Az.c = this.c;
            c0374Az.f = this.f;
            return c0374Az;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public final boolean d() {
        return (this.f & 1073741824) != 0;
    }

    public final C0374Az e() {
        C0374Az c0374Az = this.d;
        if ((this.f & 1073741824) == 0) {
            while ((c0374Az.f & Integer.MIN_VALUE) == 0) {
                c0374Az = c0374Az.e;
            }
        }
        return c0374Az;
    }

    @Override // com.android.tools.r8.internal.Q, java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.b == ((Integer) entry.getKey()).intValue() && this.c == entry.getValue();
    }

    public final boolean f() {
        return (this.f & Integer.MIN_VALUE) != 0;
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

    public C0374Az() {
        super(0, null);
    }

    public final void a(C0374Az c0374Az) {
        this.f |= 1073741824;
        this.d = c0374Az;
    }

    public final void b(C0374Az c0374Az) {
        this.f |= Integer.MIN_VALUE;
        this.e = c0374Az;
    }

    public final boolean b() {
        return (this.f & 1) != 0;
    }
}
