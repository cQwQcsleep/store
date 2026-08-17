package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1701hw {
    public static final /* synthetic */ boolean g = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B5 b;
    public final QJ c;
    public final AS d;
    public final IO e;
    public final IdentityHashMap f;

    public C1701hw(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C0705Nt c0705Nt, QJ qj, IO io2) {
        this.f = new IdentityHashMap();
        this.a = c0333y;
        this.b = b5;
        this.c = qj;
        this.d = c0705Nt.e;
        this.e = io2;
    }

    public void a(AbstractC1047aC abstractC1047aC, C0705Nt c0705Nt) {
        C0705Nt c0705Nt2 = (C0705Nt) this.f.put(abstractC1047aC, c0705Nt);
        if (g || c0705Nt2 == null) {
            return;
        }
        x1f.a();
    }

    public C0705Nt b(com.android.tools.r8.graph.B5 b5, AbstractC1047aC abstractC1047aC) {
        C0705Nt c0705Nt = (C0705Nt) this.f.remove(abstractC1047aC);
        if (c0705Nt != null) {
            return c0705Nt;
        }
        com.android.tools.r8.graph.B5 b6 = this.b;
        C0333y c0333y = this.a;
        AS as = this.d;
        boolean z = AbstractC2004lX.g;
        AbstractC2004lX position = abstractC1047aC.getPosition();
        if (position.c == null) {
            if (!AbstractC2004lX.g && !position.n()) {
                x1f.a();
                return null;
            }
            position = AbstractC2004lX.b.s().a(b6.getReference()).a();
        }
        AbstractC2004lX abstractC2004lX = position;
        if (!AbstractC2004lX.g && !b6.getReference().a(abstractC2004lX.h().c)) {
            x1f.a();
            return null;
        }
        IO io2 = this.e;
        AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
        AbstractC3148ys abstractC3148ysA = c0333y.A();
        com.android.tools.r8.graph.proto.j jVarE = com.android.tools.r8.graph.proto.j.d;
        if (io2.c(b5)) {
            abstractC3148ysA = b5.e().U0().a(c0333y);
            jVarE = c0333y.A().e((AbstractC3148ys) null, b5.getReference());
        }
        C0705Nt c0705NtA = abstractC0223i0U0.a(b6, b5, c0333y, abstractC3148ysA, as, abstractC2004lX, jVarE);
        if (this.c != null && this.e.c(b5)) {
            this.c.a(b5, c0705NtA, this.e);
        }
        return c0705NtA;
    }

    public C0705Nt a(com.android.tools.r8.graph.B5 b5, AbstractC1047aC abstractC1047aC) {
        C0705Nt c0705NtB = b(b5, abstractC1047aC);
        a(abstractC1047aC, c0705NtB);
        return c0705NtB;
    }

    public C1701hw() {
        this.f = new IdentityHashMap();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
}
