package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1604gm implements InterfaceC1775im {
    public final Set b;

    public C1604gm(Set set) {
        this.b = set;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1775im
    public final boolean a(H5 h5) {
        return this.b.contains(h5);
    }
}
