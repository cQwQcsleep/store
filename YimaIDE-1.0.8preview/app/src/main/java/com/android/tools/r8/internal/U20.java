package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U20 extends T20 implements K6 {
    public final /* synthetic */ V20 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U20(V20 v20) {
        super(v20);
        this.h = v20;
    }

    @Override // com.android.tools.r8.internal.K6
    public final boolean n() {
        return this.h.c[a()];
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Boolean.valueOf(this.h.c[a()]);
    }
}
