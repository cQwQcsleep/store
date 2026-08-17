package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1923kb extends AbstractC1754ib {
    public final char b;

    public C1923kb(char c) {
        this.b = c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib, java.util.function.Predicate
    /* JADX INFO: renamed from: a */
    public final AbstractC2352pb negate() {
        return new C1838jb(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c) {
        return c != this.b;
    }

    public final String toString() {
        return "CharMatcher.isNot('" + AbstractC2352pb.a(this.b) + "')";
    }
}
