package com.android.tools.r8.graph;

import com.android.tools.r8.internal.C2752uB;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B {
    public static B a(C2752uB c2752uB) {
        C0340z c0340z = new C0340z();
        return (!c2752uB.o0() || c2752uB.u1.s0) ? c0340z : new A(c2752uB.s());
    }

    public abstract I2 a(I2 i2);

    public abstract String a(String str);

    public abstract I2 b(I2 i2);
}
