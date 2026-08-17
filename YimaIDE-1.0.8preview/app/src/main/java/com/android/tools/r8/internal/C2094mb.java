package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2094mb extends AbstractC2352pb {
    public final AbstractC2352pb b;

    public C2094mb(AbstractC2352pb abstractC2352pb) {
        abstractC2352pb.getClass();
        this.b = abstractC2352pb;
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return b(((Character) obj).charValue());
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c) {
        return !this.b.b(c);
    }

    @Override // java.util.function.Predicate
    public final Predicate negate() {
        return this.b;
    }

    public final String toString() {
        return this.b + ".negate()";
    }
}
