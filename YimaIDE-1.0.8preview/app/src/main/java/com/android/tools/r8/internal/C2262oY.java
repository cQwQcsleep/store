package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.InterfaceC1963l1;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2262oY implements InterfaceC2603sY {
    public final /* synthetic */ InterfaceC2049m1 a;
    public final /* synthetic */ AbstractC2689tY b;

    public C2262oY(AbstractC2689tY abstractC2689tY, InterfaceC2049m1 interfaceC2049m1) {
        this.b = abstractC2689tY;
        this.a = interfaceC2049m1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2603sY
    public final InterfaceC2603sY a(C0322w2 c0322w2) {
        AbstractC2689tY abstractC2689tY = this.b;
        final InterfaceC2049m1 interfaceC2049m1 = this.a;
        abstractC2689tY.a(c0322w2, new Consumer() { // from class: ayh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceC1963l1) obj).a(interfaceC2049m1);
            }
        });
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2603sY
    public final InterfaceC2603sY a(com.android.tools.r8.graph.I2 i2) {
        this.b.a(i2);
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2603sY
    public final void a(com.android.tools.r8.graph.B5 b5, C0322w2 c0322w2) {
        this.b.a(b5, c0322w2);
    }
}
