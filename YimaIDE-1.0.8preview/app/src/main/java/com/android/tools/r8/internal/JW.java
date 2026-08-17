package com.android.tools.r8.internal;

import defpackage.ab7;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JW extends OW {
    public final ME e;
    public final ME f;
    public final Map g;
    public final List h;
    public final List i;
    public final NW j;

    public JW(C3097yF c3097yF, TG tg, C1647hH c1647hH, C1647hH c1647hH2, Map map, List list, List list2, NW nw) {
        super(c3097yF, tg, C1902kH.a);
        this.e = c1647hH.a;
        this.f = c1647hH2.a;
        this.g = map;
        this.h = list;
        this.i = list2;
        this.j = nw;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void a(StringBuilder sb, HE he) {
        AbstractC3035xa0.a((FG) this.g.get(he), new C2949wa0(sb), this.c);
    }

    @Override // com.android.tools.r8.internal.OW
    public final void b(StringBuilder sb, HE he) {
        AbstractC3035xa0.a((FG) this.g.get(he), new C2949wa0(sb), this.c);
    }

    @Override // com.android.tools.r8.internal.OW
    public final void c(StringBuilder sb) {
        AbstractC3035xa0.a(sb, this.f, new ab7(this));
        if (this.i.isEmpty()) {
            NW nw = this.j;
            nw.getClass();
            if (nw != NW.f) {
                sb.append(" { void finalize(); }");
            }
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final List a() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.OW
    public final String b() {
        return this.j.b;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void a(StringBuilder sb) {
        AbstractC3035xa0.a(sb, this.e, new ab7(this));
    }

    public final void a(StringBuilder sb, C1476fH c1476fH) {
        AbstractC3035xa0.b(new C2949wa0(sb), c1476fH);
    }

    @Override // com.android.tools.r8.internal.OW
    public final List c() {
        return this.i;
    }
}
