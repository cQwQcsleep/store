package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ST implements Cloneable, OT {
    public Object b;
    public Object c;
    public ST d;
    public ST e;
    public int f;

    public ST(Object obj, Object obj2) {
        this.b = obj;
        this.c = obj2;
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

    public final ST c() {
        ST st = this.e;
        if ((this.f & Integer.MIN_VALUE) == 0) {
            while ((st.f & 1073741824) == 0) {
                st = st.d;
            }
        }
        return st;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final ST m14clone() {
        try {
            ST st = (ST) super.clone();
            st.b = this.b;
            st.c = this.c;
            st.f = this.f;
            return st;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public final boolean d() {
        return (this.f & 1073741824) != 0;
    }

    public final ST e() {
        ST st = this.d;
        if ((this.f & 1073741824) == 0) {
            while ((st.f & Integer.MIN_VALUE) == 0) {
                st = st.e;
            }
        }
        return st;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.b;
        if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
            Object obj3 = this.c;
            if (obj3 == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (obj3.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return (this.f & Integer.MIN_VALUE) != 0;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        int iHashCode = this.b.hashCode();
        Object obj = this.c;
        return (obj == null ? 0 : obj.hashCode()) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.c;
        this.c = obj;
        return obj2;
    }

    public final String toString() {
        return this.b + "=>" + this.c;
    }

    public ST() {
        this.b = null;
        this.c = null;
    }

    public final void a(ST st) {
        this.f |= 1073741824;
        this.d = st;
    }

    public final void b(ST st) {
        this.f |= Integer.MIN_VALUE;
        this.e = st;
    }

    public final boolean b() {
        return (this.f & 1) != 0;
    }
}
