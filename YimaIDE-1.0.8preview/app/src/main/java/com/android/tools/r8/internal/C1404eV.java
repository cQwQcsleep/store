package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.threading.ThreadingModule;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1404eV extends LO {
    public final KO c;
    public final C0509Ge d;
    public AbstractC1891k8 e;

    public C1404eV(KO ko, C0509Ge c0509Ge, UY uy) {
        this.c = ko;
        this.d = c0509Ge;
        this.a = uy;
    }

    public final void a(final InterfaceC1319dV interfaceC1319dV, ThreadingModule threadingModule, ExecutorService executorService) throws ExecutionException {
        while (!this.a.b.isEmpty()) {
            C1086ah0.a(this.a, new InterfaceC2762uL() { // from class: etg
                @Override // com.android.tools.r8.internal.InterfaceC2762uL
                public final void accept(Object obj, int i) {
                    this.a.a(interfaceC1319dV, (B5) obj, i);
                }
            }, threadingModule, executorService, C1086ah0.a.c);
            g();
        }
    }

    @Override // com.android.tools.r8.internal.LO, com.android.tools.r8.internal.IO
    public final AbstractC1891k8 c() {
        AbstractC1891k8 abstractC1891k8 = this.e;
        return abstractC1891k8 != null ? abstractC1891k8 : C1806j8.a;
    }

    @Override // com.android.tools.r8.internal.IO
    public final KO d() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.IO
    public final boolean c(com.android.tools.r8.graph.B5 b5) {
        return true;
    }

    public final /* synthetic */ void a(InterfaceC1319dV interfaceC1319dV, com.android.tools.r8.graph.B5 b5, int i) {
        interfaceC1319dV.a(b5, this.d.a(b5));
    }
}
