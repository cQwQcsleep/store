package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3122yc0 extends B1 implements InterfaceC2385pw {
    public static final /* synthetic */ boolean a = true;

    @Override // com.android.tools.r8.internal.B1
    public final boolean F() {
        return true;
    }

    public abstract boolean Q();

    public abstract boolean a(C0333y c0333y);

    public abstract boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5);

    public abstract AbstractC0890Uw[] a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, InterfaceC2714tl0 interfaceC2714tl0, InterfaceC2507rN interfaceC2507rN);

    public final AbstractC0890Uw[] a(C0333y c0333y, C0705Nt c0705Nt, C2422qN c2422qN) {
        return a(c0333y, c0705Nt.i(), c0705Nt, c2422qN);
    }

    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        if (!c0333y.o()) {
            return true;
        }
        if (a || c0333y.g().h()) {
            return a(c0333y.U(), b5);
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public abstract AbstractC3122yc0 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2);

    @Override // com.android.tools.r8.internal.B1, com.android.tools.r8.internal.InterfaceC2385pw
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.B1, com.android.tools.r8.internal.InterfaceC2385pw
    public final AbstractC3122yc0 j() {
        return this;
    }
}
