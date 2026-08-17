package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2159nH;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QF extends K2 {
    public static final /* synthetic */ boolean g = true;
    public final C3030xW c;
    public final InterfaceC1221cG d;
    public final RE e;
    public final C1475fG f;

    public QF(C3030xW c3030xW, InterfaceC1221cG interfaceC1221cG, C1475fG c1475fG) {
        super(c3030xW);
        this.e = new RE();
        this.c = c3030xW;
        this.d = interfaceC1221cG;
        this.f = c1475fG;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (!g && str != null) {
            x1f.a();
            return null;
        }
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepTarget;")) {
            super.a(str, str2);
            throw null;
        }
        C3030xW c3030xW = this.c;
        c3030xW.getClass();
        C2516rW c2516rW = new C2516rW(c3030xW, str2);
        final RE re = this.e;
        Objects.requireNonNull(re);
        return new XF(c2516rW, new InterfaceC1221cG() { // from class: fzb
            @Override // com.android.tools.r8.internal.InterfaceC1221cG
            public final void accept(Object obj) {
                re.a((C2159nH) obj);
            }
        }, this.f);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        this.d.accept(this.e.a());
    }
}
