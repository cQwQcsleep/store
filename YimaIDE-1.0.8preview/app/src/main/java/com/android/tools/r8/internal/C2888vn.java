package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C2888vn;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2888vn {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC0706Nu a;
    public final InterfaceC2045lz b;
    public final AbstractC0706Nu c;
    public final AbstractC2554rv d;
    public final int e;

    public C2888vn(AbstractC0706Nu abstractC0706Nu, InterfaceC2045lz interfaceC2045lz, AbstractC0706Nu abstractC0706Nu2, AbstractC2554rv abstractC2554rv, int i) {
        this.a = abstractC0706Nu;
        this.b = interfaceC2045lz;
        this.c = abstractC0706Nu2;
        this.d = abstractC2554rv;
        this.e = i;
    }

    public final C2525rc0 a(C1 c1, final com.android.tools.r8.graph.I2 i2) {
        if (!f && this.b.values().stream().filter(new Predicate() { // from class: mmi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2888vn.a(i2, (I2) obj);
            }
        }).count() > 1) {
            x1f.a();
            return null;
        }
        BU it = this.b.c().iterator();
        while (it.hasNext()) {
            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
            if (interfaceC1959kz.getValue() == i2) {
                return c1.a(interfaceC1959kz.a() + 1, AbstractC2624sj0.k());
            }
        }
        return null;
    }

    public static /* synthetic */ boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        return i3 == i2;
    }
}
