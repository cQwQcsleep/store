package com.android.tools.r8.internal;

import com.android.tools.r8.graph.InterfaceC0265o0;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2116mm {
    public static AbstractC2116mm a(com.android.tools.r8.shaking.R2 r2) {
        return (r2 == null || r2.v.b()) ? new C0776Qm() : new PR(r2.v);
    }

    public abstract void a(C2752uB c2752uB);

    public abstract boolean a(com.android.tools.r8.graph.I2 i2);

    public final boolean a(InterfaceC0265o0 interfaceC0265o0) {
        return a(interfaceC0265o0.z());
    }
}
