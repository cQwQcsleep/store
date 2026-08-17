package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2207nq {
    public static final void a(AbstractC2083mQ abstractC2083mQ) {
        C2549rq c2549rq = AbstractC2805uq.b;
        KB.b(c2549rq, "HAS_ANNOTATIONS");
        C2464qq c2464qq = new C2464qq(c2549rq, 1);
        if (c2464qq.b == 1 && c2464qq.c == 1) {
            return;
        }
        b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
    }

    public static final void b(AbstractC2083mQ abstractC2083mQ) {
        KB.b(AbstractC2805uq.d, "MODALITY");
        C3059xn c3059xn = EnumC1146bP.c;
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) c3059xn));
        C1363e0 c1363e0 = new C1363e0(c3059xn);
        while (c1363e0.hasNext()) {
            arrayList.add(((EnumC1146bP) c1363e0.next()).b);
        }
    }

    public static final void c(AbstractC2083mQ abstractC2083mQ) {
        KB.b(AbstractC2805uq.c, "VISIBILITY");
        C3059xn c3059xn = EnumC2716tm0.c;
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) c3059xn));
        C1363e0 c1363e0 = new C1363e0(c3059xn);
        while (c1363e0.hasNext()) {
            arrayList.add(((EnumC2716tm0) c1363e0.next()).b);
        }
    }

    public static final void a(C2464qq c2464qq) {
        int i = C1609gq.i;
        if (c2464qq.b == 1 && c2464qq.c == 1) {
            return;
        }
        b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
    }

    public static final void b(C2464qq c2464qq) {
        int i = C1780iq.i;
        if (c2464qq.b == 1 && c2464qq.c == 1) {
            return;
        }
        b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
    }
}
