package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sJ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2589sJ extends C1975l7 {
    public final C0705Nt b;

    public C2589sJ(C0705Nt c0705Nt) {
        this.b = c0705Nt;
    }

    @Override // com.android.tools.r8.internal.C1975l7, com.android.tools.r8.internal.AbstractC2061m7
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2031lm a() {
        return (C2031lm) a(new Supplier() { // from class: qai
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.d();
            }
        });
    }

    public final /* synthetic */ C2031lm d() {
        return new C2031lm(this.b, 2);
    }
}
