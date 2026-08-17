package com.android.tools.r8.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CB {
    public final CB a = null;
    public final int b = 0;
    public final HashMap c = new HashMap();

    public final Integer a(Lg0 lg0) {
        Integer numA;
        CB cb = this.a;
        boolean z = cb == null || cb.c.size() + this.a.b == this.b;
        if (!Zm0.a || z) {
            CB cb2 = this.a;
            return (cb2 == null || (numA = cb2.a(lg0)) == null) ? (Integer) this.c.get(lg0) : numA;
        }
        x01.a("Parent changed in parallel with child: indexes will be wrong");
        return null;
    }

    public final boolean a() {
        if (!this.c.isEmpty()) {
            return false;
        }
        CB cb = this.a;
        return cb == null || cb.a();
    }
}
