package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3111yT extends CT implements BU {
    public final /* synthetic */ ET h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3111yT(ET et) {
        super(et);
        this.h = et;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.b[a()];
    }
}
