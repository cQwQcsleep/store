package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0702Nq extends AbstractC0728Oq {
    public final /* synthetic */ Iterable[] b;

    public C0702Nq(Iterable[] iterableArr) {
        this.b = iterableArr;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new KC(new C0676Mq(this, this.b.length));
    }
}
