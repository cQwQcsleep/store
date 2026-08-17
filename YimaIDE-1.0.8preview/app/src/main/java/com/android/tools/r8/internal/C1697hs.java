package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1697hs extends C1867js {
    public final C1260ck f;
    public final Method g;
    public final Method h;
    public final boolean i;
    public final Method j;
    public final Method k;
    public final Method l;

    public C1697hs(C1856jk c1856jk, String str, Class cls, Class cls2, String str2) {
        super(c1856jk, str, cls, cls2, str2);
        this.f = c1856jk.g();
        this.g = AbstractC2209ns.a(this.a, "valueOf", new Class[]{C1515fk.class});
        this.h = AbstractC2209ns.a(this.a, "getValueDescriptor", new Class[0]);
        boolean z = c1856jk.e.f() == 3;
        this.i = z;
        if (z) {
            this.j = AbstractC2209ns.a(cls, C40.a("get", str, "Value"), new Class[0]);
            this.k = AbstractC2209ns.a(cls2, C40.a("get", str, "Value"), new Class[0]);
            this.l = AbstractC2209ns.a(cls2, C40.a("set", str, "Value"), new Class[]{Integer.TYPE});
        }
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final Object b(AbstractC2209ns abstractC2209ns) {
        if (!this.i) {
            return AbstractC2209ns.a(this.h, super.b(abstractC2209ns), new Object[0]);
        }
        return this.f.c(((Integer) AbstractC2209ns.a(this.j, abstractC2209ns, new Object[0])).intValue());
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final Object b(AbstractC0911Vr abstractC0911Vr) {
        if (this.i) {
            return this.f.c(((Integer) AbstractC2209ns.a(this.k, abstractC0911Vr, new Object[0])).intValue());
        }
        return AbstractC2209ns.a(this.h, super.b(abstractC0911Vr), new Object[0]);
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final void b(AbstractC0911Vr abstractC0911Vr, Object obj) {
        if (this.i) {
            AbstractC2209ns.a(this.l, abstractC0911Vr, new Object[]{Integer.valueOf(((C1515fk) obj).b.g)});
        } else {
            super.b(abstractC0911Vr, AbstractC2209ns.a(this.g, (Object) null, new Object[]{obj}));
        }
    }
}
