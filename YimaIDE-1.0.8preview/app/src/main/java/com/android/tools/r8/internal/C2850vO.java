package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2850vO extends AbstractC2849vN {
    public static final /* synthetic */ boolean e = true;
    public final EnumC2326pC c;
    public final com.android.tools.r8.graph.proto.j d;

    public C2850vO(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, com.android.tools.r8.graph.proto.j jVar) {
        super(c0322w2, c0322w3);
        this.c = enumC2326pC;
        this.d = jVar;
    }

    public final C2850vO a(AbstractC3148ys abstractC3148ys) {
        boolean z = e;
        if (!z && this.a == null) {
            x1f.a();
            return null;
        }
        if (z || abstractC3148ys.l() || (abstractC3148ys instanceof Y2) || !abstractC3148ys.d().b.o() || ((C0322w2) this.a).w0().I0() || b() || (abstractC3148ys instanceof C0907Vn) || (abstractC3148ys instanceof LS) || (abstractC3148ys instanceof C2916w7)) {
            return this;
        }
        x01.a(abstractC3148ys);
        return null;
    }

    public final com.android.tools.r8.graph.proto.j c() {
        return this.d;
    }

    public final EnumC2326pC d() {
        return this.c;
    }
}
