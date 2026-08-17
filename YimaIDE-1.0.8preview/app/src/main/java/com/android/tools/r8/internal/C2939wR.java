package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0217h1;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2939wR extends AbstractC1757ic0 {
    public static final /* synthetic */ boolean b = true;

    public static void b(com.android.tools.r8.graph.D2 d2) {
        if (b || De0.a(d2.B1()).allMatch(new Predicate() { // from class: hoi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0217h1) obj).W().c();
            }
        })) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        boolean z = b;
        if (z) {
            return true;
        }
        if (!z && !d2.x.c()) {
            x1f.a();
            return false;
        }
        if (z) {
            return true;
        }
        b(d2);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoKotlinMetadata";
    }
}
