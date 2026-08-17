package com.android.tools.r8.internal;

import com.android.tools.r8.internal.SE;
import defpackage.e58;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LF extends K2 {
    public final C2516rW c;
    public final InterfaceC1221cG d;
    public final C2329pF e;
    public final C2585sF f;
    public final C1475fG g;
    public final RE h;

    public LF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, AG ag) {
        super(c2516rW);
        this.e = new C2329pF();
        C2585sF c2585sF = new C2585sF();
        this.f = c2585sF;
        C1475fG c1475fG = new C1475fG();
        this.g = c1475fG;
        RE re = new RE();
        this.h = re;
        this.c = c2516rW;
        this.d = interfaceC1221cG;
        consumer.accept(c2585sF);
        ME meB = ag.a.b();
        c1475fG.a.getClass();
        HE he = new HE("CONTEXT");
        c1475fG.a.a(he, meB);
        NE ne = new NE(new KE(he));
        C1986lF c1986lF = C1986lF.b;
        ME.g();
        EG eg = EG.d;
        re.a.add(new C2159nH(new CG(new AG(ne, ag.b)), c1986lF));
        re.a.add(new C2159nH(ne, c1986lF));
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        InterfaceC1221cG interfaceC1221cG = this.d;
        C2329pF c2329pF = this.e;
        c2329pF.a = this.f.a();
        c2329pF.b = this.g.a.a();
        c2329pF.d = this.h.a();
        interfaceC1221cG.accept(c2329pF.a());
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        if (str.equals("additionalTargets")) {
            return new QF(this.c.b(str), new InterfaceC1221cG() { // from class: cm8
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    this.a.a((SE) obj);
                }
            }, this.g);
        }
        super.a(str);
        throw null;
    }

    public final /* synthetic */ void a(SE se) {
        RE re = this.h;
        Objects.requireNonNull(re);
        se.a(new e58(re));
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("description") && (obj instanceof String)) {
            C2585sF c2585sF = this.f;
            c2585sF.getClass();
            c2585sF.b = new C2841vF((String) obj);
            return;
        }
        super.a(obj, str);
        throw null;
    }
}
