package com.android.tools.r8.internal;

import com.android.tools.r8.shaking.K0;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0961Xp extends C1641hB {
    public C0961Xp(final K0 k0) {
        super(k0.a, new Predicate() { // from class: t0g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return k0.a((String) obj);
            }
        });
    }
}
