package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2780ub0;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A4 {
    public static final /* synthetic */ boolean f = true;
    public final IdentityHashMap a = new IdentityHashMap();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final Set d = AbstractC2780ub0.c();
    public int e;

    public final void a(InterfaceC0331x4 interfaceC0331x4) {
        if (!f) {
            interfaceC0331x4.getClass();
        }
        this.a.putIfAbsent(interfaceC0331x4.f().getReference(), interfaceC0331x4);
    }

    public final A4 a(C0231j1 c0231j1) {
        this.c.add(c0231j1);
        return this;
    }

    public final A4 a(I2 i2) {
        this.d.add(i2);
        return this;
    }

    public final A4 a(int i) {
        this.e = i;
        return this;
    }

    public final B4.a a() {
        return new B4.a(this.a, this.b, this.c, this.e);
    }
}
