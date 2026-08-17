package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.C0889Uv;
import com.android.tools.r8.shaking.C3403i;
import defpackage.m7h;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0889Uv {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final Map b;

    public C0889Uv(C0333y c0333y, Map map) {
        this.a = c0333y;
        this.b = map;
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 b(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, i2);
        if (i2C.T0()) {
            return null;
        }
        return i2C;
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.B5 b5) {
        if (!c && b5.e().z0()) {
            x1f.a();
            return false;
        }
        C0229j c0229j = (C0229j) this.a.g();
        if (c0229j.i()) {
            C3403i c3403iM = c0229j.m();
            if (c3403iM.v.a((InterfaceC0332x5) d2).d(c3403iM.j())) {
                return false;
            }
        }
        if (c0229j.c((com.android.tools.r8.graph.I2) this.b.getOrDefault(b5.s(), this.a.a().a2), d2.e)) {
            return !d2.isInterface();
        }
        return false;
    }

    public final C0889Uv a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        return new C0889Uv(this.a, IM.a(this.b, new m7h(), new Function() { // from class: u2f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0889Uv.a(abstractC3148ys, abstractC3148ys2, (I2) obj);
            }
        }, new Function() { // from class: v2f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0889Uv.b(abstractC3148ys, abstractC3148ys2, (I2) obj);
            }
        }, new InterfaceC1938ki0() { // from class: w2f
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return this.a.a((I2) obj, (I2) obj2, (I2) obj3);
            }
        }));
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, i2);
        if (i2C.T0()) {
            return null;
        }
        return i2C;
    }

    public final /* synthetic */ com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3, com.android.tools.r8.graph.I2 i4) {
        return C2441qd.a((C0229j) this.a.g(), i3, i4);
    }
}
