package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ib, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1754ib extends AbstractC2352pb {
    @Override // java.util.function.Predicate
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public AbstractC2352pb negate() {
        return new C2094mb(this);
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return b(((Character) obj).charValue());
    }
}
