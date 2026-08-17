package com.android.tools.r8.internal;

import defpackage.dye;
import defpackage.eye;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UF extends K2 {
    public final C2516rW c;
    public final InterfaceC1221cG d;
    public final C2329pF e;
    public final C2585sF f;
    public final C1475fG g;

    public UF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer) {
        super(c2516rW);
        this.e = new C2329pF();
        C2585sF c2585sF = new C2585sF();
        this.f = c2585sF;
        this.g = new C1475fG();
        this.c = c2516rW;
        this.d = interfaceC1221cG;
        consumer.accept(c2585sF);
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        C3030xW c3030xWB = this.c.b(str);
        if (str.equals("bindings")) {
            return new OF(c3030xWB, this.g);
        }
        if (str.equals("preconditions")) {
            C2329pF c2329pF = this.e;
            Objects.requireNonNull(c2329pF);
            return new WF(c3030xWB, new dye(c2329pF), this.g);
        }
        if (!str.equals("consequences")) {
            super.a(str);
            throw null;
        }
        C2329pF c2329pF2 = this.e;
        Objects.requireNonNull(c2329pF2);
        return new QF(c3030xWB, new eye(c2329pF2), this.g);
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

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        InterfaceC1221cG interfaceC1221cG = this.d;
        C2329pF c2329pF = this.e;
        c2329pF.a = this.f.a();
        c2329pF.b = this.g.a.a();
        interfaceC1221cG.accept(c2329pF.a());
    }
}
