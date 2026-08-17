package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2180nb extends AbstractC2009lb {
    public static final C2180nb c = new C2180nb();

    public C2180nb() {
        super("CharMatcher.none()");
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final int a(CharSequence charSequence, int i) {
        DX.b(i, charSequence.length());
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c2) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib, java.util.function.Predicate
    public final Predicate negate() {
        return C1668hb.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1754ib
    /* JADX INFO: renamed from: a */
    public final AbstractC2352pb negate() {
        return C1668hb.c;
    }
}
