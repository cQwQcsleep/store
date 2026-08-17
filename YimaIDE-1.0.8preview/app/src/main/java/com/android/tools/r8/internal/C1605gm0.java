package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import defpackage.hih;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gm0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1605gm0 implements ON {
    public static final /* synthetic */ boolean d = true;
    public final InterfaceC1037a6 a;
    public final Y5 b;
    public final Y5 c;

    public C1605gm0(InterfaceC1037a6 interfaceC1037a6, Y5 y5, Y5 y6) {
        this.a = interfaceC1037a6;
        this.b = y5;
        this.c = y6;
    }

    @Override // com.android.tools.r8.internal.ON
    public final void a(C0333y c0333y) {
        for (com.android.tools.r8.graph.I2 i2 : this.a.keySet()) {
            if (!d && !((C3403i) c0333y.g()).l(i2)) {
                hih.a("Expected vertically merged class `", i2.m0(), "` to be absent");
                return;
            }
        }
    }

    public boolean b() {
        return this.a.isEmpty();
    }

    public boolean d(com.android.tools.r8.graph.I2 i2) {
        return this.a.containsKey(i2);
    }

    @Override // com.android.tools.r8.internal.ON
    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        return d(i2);
    }

    public Set<com.android.tools.r8.graph.I2> a() {
        return this.a.keySet();
    }

    @Override // com.android.tools.r8.internal.ON
    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        return !this.a.a(i2).isEmpty();
    }

    @Override // com.android.tools.r8.internal.ON
    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        return (com.android.tools.r8.graph.I2) this.a.getOrDefault(i2, i3);
    }
}
