package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2823v30 extends AbstractC3164z30 implements BU {
    public final /* synthetic */ B30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2823v30(B30 b30) {
        super(b30);
        this.h = b30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.b[a()];
    }
}
