package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2935wN;
import com.android.tools.r8.naming.C3331k;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2935wN {
    public final C3331k.c a;
    public final com.android.tools.r8.naming.V b;

    public C2935wN(com.android.tools.r8.naming.V v, C3331k.c cVar) {
        this.b = v;
        this.a = cVar;
    }

    public final List a() {
        return this.a.a(0, true);
    }

    public final List b() {
        return C2847vL.a((Collection) this.a.a(), new Predicate() { // from class: boi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2935wN.a((C3331k.b) obj);
            }
        });
    }

    public static /* synthetic */ boolean a(C3331k.b bVar) {
        return bVar.b == null;
    }
}
