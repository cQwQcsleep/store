package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2177nZ extends YI implements InterfaceC1439er {
    public final /* synthetic */ H50 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2177nZ(H50 h50) {
        super(1);
        this.c = h50;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C2443qe b(J70 j70) {
        List listA;
        int iK = j70.k();
        H50 h50 = this.c;
        if (iK != 0) {
            listA = AbstractC1929ke.a((Object) h50.a().a(j70.k()));
        } else {
            listA = h50.a().a("@" + j70.m());
        }
        return AbstractC1760ie.a(listA);
    }
}
