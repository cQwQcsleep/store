package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1475fG {
    public final GE a;
    public final HashMap b;

    public C1475fG() {
        IE ie = IE.b;
        this.a = new GE();
        this.b = new HashMap();
    }

    public final HE a(String str) {
        HashMap map = this.b;
        final GE ge = this.a;
        Objects.requireNonNull(ge);
        return (HE) map.computeIfAbsent(str, new Function() { // from class: ivg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ge.a((String) obj);
            }
        });
    }

    public final void a(String str, AbstractC2671tG abstractC2671tG) {
        this.a.a(a(str), abstractC2671tG);
    }

    public final IE a() {
        return this.a.a();
    }
}
