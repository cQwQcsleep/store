package com.android.tools.r8.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MW extends OW {
    public static final /* synthetic */ boolean j = true;
    public final C1476fH e;
    public final ME f;
    public final NW g;
    public final List h;
    public final Map i;

    public MW(C3097yF c3097yF, C1647hH c1647hH, TG tg, Map map, List list, NW nw) {
        super(c3097yF, tg, C1902kH.a);
        if (!j && nw.equals(NW.c)) {
            x1f.a();
            throw null;
        }
        ME me = c1647hH.a;
        this.e = me.a;
        this.f = me;
        this.g = nw;
        this.i = map;
        this.h = list;
    }

    @Override // com.android.tools.r8.internal.OW
    public final void b(StringBuilder sb, HE he) {
        AbstractC3035xa0.a((FG) this.i.get(he), new C2949wa0(sb), this.c);
    }

    @Override // com.android.tools.r8.internal.OW
    public final void c(StringBuilder sb) {
        AbstractC3035xa0.a(sb, this.f, OW.a(this.e));
        if (this.h.isEmpty()) {
            NW nw = this.g;
            nw.getClass();
            if (nw != NW.f) {
                sb.append(" { void finalize(); }");
            }
        }
    }

    @Override // com.android.tools.r8.internal.OW
    public final String b() {
        return this.g.b;
    }

    @Override // com.android.tools.r8.internal.OW
    public final List c() {
        return this.h;
    }
}
