package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2443qe implements Ua0 {
    public final /* synthetic */ Iterable a;

    public C2443qe(Iterable iterable) {
        this.a = iterable;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        return this.a.iterator();
    }
}
