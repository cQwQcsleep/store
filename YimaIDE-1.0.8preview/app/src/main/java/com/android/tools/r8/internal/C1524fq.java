package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1524fq {
    public final int a;
    public final int b;
    public final int c;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.fq$a */
    public static final class a {
        public static final C1524fq a;

        static {
            C2549rq c2549rq = AbstractC2805uq.G;
            KB.b(c2549rq, "DECLARES_DEFAULT_VALUE");
            a = new C1524fq(c2549rq, 1);
            C2549rq c2549rq2 = AbstractC2805uq.H;
            KB.b(c2549rq2, "IS_CROSSINLINE");
            new C1524fq(c2549rq2, 1);
            C2549rq c2549rq3 = AbstractC2805uq.I;
            KB.b(c2549rq3, "IS_NOINLINE");
            new C1524fq(c2549rq3, 1);
        }
    }

    static {
        C2549rq c2549rq = AbstractC2805uq.b;
        KB.b(c2549rq, "HAS_ANNOTATIONS");
        new C1524fq(c2549rq, 1);
        C2634sq c2634sq = AbstractC2805uq.c;
        KB.b(c2634sq, "VISIBILITY");
        new C1524fq(c2634sq, 0);
        new C1524fq(c2634sq, 1);
        new C1524fq(c2634sq, 2);
        new C1524fq(c2634sq, 3);
        new C1524fq(c2634sq, 4);
        new C1524fq(c2634sq, 5);
        C2634sq c2634sq2 = AbstractC2805uq.d;
        KB.b(c2634sq2, "MODALITY");
        new C1524fq(c2634sq2, 0);
        new C1524fq(c2634sq2, 1);
        new C1524fq(c2634sq2, 2);
        new C1524fq(c2634sq2, 3);
    }

    public C1524fq(AbstractC2720tq abstractC2720tq, int i) {
        KB.c(abstractC2720tq, "field");
        int i2 = abstractC2720tq.a;
        int i3 = abstractC2720tq.b;
        this.a = i2;
        this.b = i3;
        this.c = i;
    }

    public final boolean a(int i) {
        return ((i >>> this.a) & ((1 << this.b) - 1)) == this.c;
    }
}
