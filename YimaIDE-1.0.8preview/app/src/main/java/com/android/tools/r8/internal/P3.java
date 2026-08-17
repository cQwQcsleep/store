package com.android.tools.r8.internal;

import java.io.IOException;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P3 extends AbstractC3220zi0 {
    public static final O3 b = new O3();
    public final Bi0 a;

    public P3(C0471Es c0471Es, AbstractC3220zi0 abstractC3220zi0, Class cls) {
        this.a = new Bi0(c0471Es, abstractC3220zi0, cls);
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        if (obj == null) {
            c2754uD.i();
            return;
        }
        c2754uD.d();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.a.a(c2754uD, Array.get(obj, i));
        }
        c2754uD.f();
    }
}
