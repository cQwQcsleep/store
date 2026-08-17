package com.android.tools.r8.internal;

import defpackage.w36;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HF extends K2 {
    public final InterfaceC1221cG c;
    public final ArrayList d;

    public HF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG) {
        super(c2516rW);
        this.d = new ArrayList();
        this.c = interfaceC1221cG;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        if (this.d.isEmpty()) {
            defpackage.l0.a("Invalid extracted annotation set, expected non-empty.");
            return;
        }
        ArrayList arrayList = this.d;
        InterfaceC1221cG interfaceC1221cG = this.c;
        Objects.requireNonNull(interfaceC1221cG);
        arrayList.forEach(new w36(interfaceC1221cG));
        super.a();
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        if (str.equals("value")) {
            C3030xW c3030xWB = this.b.b(str);
            return new GF(this, c3030xWB, c3030xWB);
        }
        super.a(str);
        throw null;
    }
}
