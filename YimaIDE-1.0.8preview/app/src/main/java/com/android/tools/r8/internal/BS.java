package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BS extends Fb0 {
    public static final /* synthetic */ boolean d = true;
    public final long c;

    public BS(int i, long j) {
        super(i);
        this.c = j;
    }

    @Override // com.android.tools.r8.internal.Gb0
    public final Gb0 a(C0333y c0333y, com.android.tools.r8.graph.proto.c cVar, Lb0 lb0) {
        if (!cVar.a(this.b).c()) {
            if (!d) {
                if (cVar.a.a(this.b)) {
                    x1f.a();
                    return null;
                }
            }
            return a(cVar.b(this.b), lb0);
        }
        com.android.tools.r8.graph.proto.g gVarA = cVar.a(this.b).a();
        if (gVarA.g()) {
            AbstractC3122yc0 abstractC3122yc0 = gVarA.b;
            abstractC3122yc0.getClass();
            return ((abstractC3122yc0 instanceof C2525rc0) && a(abstractC3122yc0.e().b)) ? C1624h2.b : EQ.b;
        }
        if (d) {
            return EQ.b;
        }
        x1f.a();
        return null;
    }

    public abstract boolean a(long j);

    @Override // com.android.tools.r8.internal.Gb0
    public final boolean a(AbstractC1047aC abstractC1047aC) {
        C2543rl0 c2543rl0H = abstractC1047aC.b(this.b).h();
        return c2543rl0H.c(new defpackage.nl0()) && a(c2543rl0H.p().F().P2());
    }
}
