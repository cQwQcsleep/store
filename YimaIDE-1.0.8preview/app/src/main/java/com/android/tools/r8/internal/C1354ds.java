package com.android.tools.r8.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ds, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1354ds extends C1526fs {
    public final C1260ck c;
    public final Method d;
    public final Method e;
    public final boolean f;
    public final Method g;
    public final Method h;
    public final Method i;

    public C1354ds(C1856jk c1856jk, String str, Class cls, Class cls2) {
        super(str, cls, cls2);
        this.c = c1856jk.g();
        this.d = AbstractC2209ns.a(this.a, "valueOf", new Class[]{C1515fk.class});
        this.e = AbstractC2209ns.a(this.a, "getValueDescriptor", new Class[0]);
        boolean z = c1856jk.e.f() == 3;
        this.f = z;
        if (z) {
            String strA = C40.a("get", str, "Value");
            Class cls3 = Integer.TYPE;
            this.g = AbstractC2209ns.a(cls, strA, new Class[]{cls3});
            this.h = AbstractC2209ns.a(cls2, C40.a("get", str, "Value"), new Class[]{cls3});
            AbstractC2209ns.a(cls2, C40.a("set", str, "Value"), new Class[]{cls3, cls3});
            this.i = AbstractC2209ns.a(cls2, C40.a("add", str, "Value"), new Class[]{cls3});
        }
    }

    @Override // com.android.tools.r8.internal.C1526fs, com.android.tools.r8.internal.InterfaceC1188bs
    public final void a(AbstractC0911Vr abstractC0911Vr, Object obj) {
        if (this.f) {
            AbstractC2209ns.a(this.i, abstractC0911Vr, new Object[]{Integer.valueOf(((C1515fk) obj).b.g)});
        } else {
            super.a(abstractC0911Vr, AbstractC2209ns.a(this.d, (Object) null, new Object[]{obj}));
        }
    }

    @Override // com.android.tools.r8.internal.C1526fs, com.android.tools.r8.internal.InterfaceC1188bs
    public final Object b(AbstractC2209ns abstractC2209ns) {
        ArrayList arrayList = new ArrayList();
        int iIntValue = ((Integer) AbstractC2209ns.a(this.b.f, abstractC2209ns, new Object[0])).intValue();
        for (int i = 0; i < iIntValue; i++) {
            arrayList.add(this.f ? this.c.c(((Integer) AbstractC2209ns.a(this.g, abstractC2209ns, new Object[]{Integer.valueOf(i)})).intValue()) : AbstractC2209ns.a(this.e, AbstractC2209ns.a(this.b.c, abstractC2209ns, new Object[]{Integer.valueOf(i)}), new Object[0]));
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.android.tools.r8.internal.C1526fs, com.android.tools.r8.internal.InterfaceC1188bs
    public final Object b(AbstractC0911Vr abstractC0911Vr) {
        Object objA;
        ArrayList arrayList = new ArrayList();
        int iIntValue = ((Integer) AbstractC2209ns.a(this.b.g, abstractC0911Vr, new Object[0])).intValue();
        for (int i = 0; i < iIntValue; i++) {
            if (this.f) {
                objA = this.c.c(((Integer) AbstractC2209ns.a(this.h, abstractC0911Vr, new Object[]{Integer.valueOf(i)})).intValue());
            } else {
                objA = AbstractC2209ns.a(this.e, AbstractC2209ns.a(this.b.d, abstractC0911Vr, new Object[]{Integer.valueOf(i)}), new Object[0]);
            }
            arrayList.add(objA);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
