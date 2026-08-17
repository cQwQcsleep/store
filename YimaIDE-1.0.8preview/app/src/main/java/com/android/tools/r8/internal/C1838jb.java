package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1838jb extends AbstractC1754ib {
    public final char b;

    public C1838jb(char c) {
        this.b = c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib, java.util.function.Predicate
    /* JADX INFO: renamed from: a */
    public final AbstractC2352pb negate() {
        return new C1923kb(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c) {
        return c == this.b;
    }

    public final String toString() {
        return "CharMatcher.is('" + AbstractC2352pb.a(this.b) + "')";
    }
}
