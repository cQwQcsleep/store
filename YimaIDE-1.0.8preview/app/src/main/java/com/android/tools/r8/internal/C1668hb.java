package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1668hb extends AbstractC2009lb {
    public static final C1668hb c = new C1668hb();

    public C1668hb() {
        super("CharMatcher.any()");
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        DX.b(i, length);
        if (i == length) {
            return -1;
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c2) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib, java.util.function.Predicate
    public final Predicate negate() {
        return C2180nb.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib
    /* JADX INFO: renamed from: a */
    public final AbstractC2352pb negate() {
        return C2180nb.c;
    }
}
