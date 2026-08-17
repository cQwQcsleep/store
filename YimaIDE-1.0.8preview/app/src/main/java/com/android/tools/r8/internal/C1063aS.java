package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1063aS extends AbstractC0423Cw {
    public static final /* synthetic */ boolean e = true;
    public final int a;
    public final AbstractC2555rw b;
    public final AbstractC2389q c;
    public final C0322w2 d;

    public C1063aS(int i, AbstractC2555rw abstractC2555rw, AbstractC2389q abstractC2389q, C0322w2 c0322w2) {
        boolean z = e;
        if (!z && !z && (i & (-8)) != 0) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = abstractC2555rw;
        this.c = abstractC2389q;
        this.d = c0322w2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final C1063aS a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final AbstractC2555rw b() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final C0322w2 c() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final boolean d() {
        return (this.a & 1) == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final boolean e() {
        return (this.a & 2) == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final AbstractC2389q f() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC0423Cw
    public final boolean g() {
        return (this.a & 4) != 0;
    }

    public final String toString() {
        return "NonTrivialInstanceInitializerInfo(" + this.b + ")";
    }
}
