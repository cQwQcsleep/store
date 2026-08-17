package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1629h40 extends YI implements InterfaceC1439er {
    public static final C1629h40 c = new C1629h40();

    public C1629h40() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        C1552g80 c1552g80 = (C1552g80) obj;
        R60 r60K = c1552g80.k();
        J70 j70L = c1552g80.l();
        KB.b(j70L, "it.key");
        Q60 q60D = R60.h.d();
        q60D.g = j70L;
        q60D.p();
        q60D.f = 1;
        R60 r60I = q60D.i();
        if (r60I.a()) {
            return Wa0.a(r60K, r60I);
        }
        throw H0.c(r60I);
    }
}
