package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0939Wt implements InterfaceC0965Xt {
    public static final C0939Wt a = new C0939Wt();

    @Override // com.android.tools.r8.internal.InterfaceC0965Xt
    public final Iterable a(C2924wC c2924wC) {
        ArrayList arrayListA = AL.a(c2924wC);
        Collections.shuffle(arrayListA);
        return arrayListA;
    }
}
