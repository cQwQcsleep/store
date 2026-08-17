package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L20 extends AbstractC2392q1 implements J20, Cloneable {
    public final Object b;
    public final boolean c = true;
    public transient LU d;
    public transient X30 e;
    public transient V6 f;

    public L20(Object obj) {
        this.b = obj;
    }

    @Override // com.android.tools.r8.internal.H20
    public final boolean a(Object obj) {
        if (this.b == obj) {
            return this.c;
        }
        return false;
    }

    public final Object clone() {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.b == obj;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return ((Boolean) obj).booleanValue() == this.c;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return g();
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
        if (map.size() != 1) {
            return false;
        }
        return ((Map.Entry) map.entrySet().iterator().next()).equals(g().iterator().next());
    }

    @Override // com.android.tools.r8.internal.J20
    public final JU g() {
        if (this.d == null) {
            C2476r1 c2476r1 = new C2476r1(this.b, this.c);
            KU ku = MU.a;
            this.d = new LU(c2476r1);
        }
        return this.d;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return (this.c ? 1231 : 1237) ^ System.identityHashCode(this.b);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Map
    public final V30 keySet() {
        if (this.e == null) {
            Object obj = this.b;
            W30 w30 = Y30.a;
            this.e = new X30(obj);
        }
        return this.e;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return 1;
    }

    public final String toString() {
        return "{" + this.b + "=>" + this.c + "}";
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.f == null) {
            U6 u6 = W6.a;
            this.f = new V6();
        }
        return this.f;
    }
}
