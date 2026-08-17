package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1222cH;
import com.android.tools.r8.internal.QE;
import defpackage.eye;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1561gG extends K2 {
    public final C2516rW c;
    public final InterfaceC1221cG d;
    public final C2329pF e;
    public final C1052aH f;
    public final C2585sF g;
    public final C1475fG h;

    public C1561gG(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, AbstractC2671tG abstractC2671tG) {
        super(c2516rW);
        this.e = new C2329pF();
        C1052aH c1052aH = new C1052aH();
        this.f = c1052aH;
        C2585sF c2585sF = new C2585sF();
        this.g = c2585sF;
        this.h = new C1475fG();
        this.c = c2516rW;
        this.d = interfaceC1221cG;
        c1052aH.a.add(new QE(abstractC2671tG.f()));
        consumer.accept(c2585sF);
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        C3030xW c3030xWB = this.c.b(str);
        if (str.equals("value")) {
            C2329pF c2329pF = this.e;
            Objects.requireNonNull(c2329pF);
            return new QF(c3030xWB, new eye(c2329pF), this.h);
        }
        if (str.equals("additionalPreconditions")) {
            return new WF(c3030xWB, new InterfaceC1221cG() { // from class: kxg
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    this.a.a((AbstractC1222cH) obj);
                }
            }, this.h);
        }
        super.a(str);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("description") && (obj instanceof String)) {
            C2585sF c2585sF = this.g;
            c2585sF.getClass();
            c2585sF.b = new C2841vF((String) obj);
            return;
        }
        super.a(obj, str);
        throw null;
    }

    public final /* synthetic */ void a(AbstractC1222cH abstractC1222cH) {
        final C1052aH c1052aH = this.f;
        Objects.requireNonNull(c1052aH);
        abstractC1222cH.a(new Consumer() { // from class: lxg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1052aH.a((QE) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        AbstractC1222cH c1138bH;
        InterfaceC1221cG interfaceC1221cG = this.d;
        C2329pF c2329pF = this.e;
        c2329pF.a = this.g.a();
        c2329pF.b = this.h.a.a();
        C1052aH c1052aH = this.f;
        if (c1052aH.a.isEmpty()) {
            c1138bH = ZG.a;
        } else {
            c1138bH = new C1138bH(c1052aH.a);
        }
        c2329pF.c = c1138bH;
        interfaceC1221cG.accept(c2329pF.a());
    }
}
