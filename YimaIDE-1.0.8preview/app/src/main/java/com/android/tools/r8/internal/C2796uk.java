package com.android.tools.r8.internal;

import com.android.tools.r8.internal.InterfaceC2968wk;
import java.util.Collection;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2796uk extends C3053xk {
    public final /* synthetic */ C2882vk b;

    public C2796uk(C2882vk c2882vk) {
        this.b = c2882vk;
    }

    @Override // com.android.tools.r8.internal.C3053xk
    public final Collection a(AbstractC2004lX abstractC2004lX, InterfaceC1187br interfaceC1187br, LL ll, InterfaceC1467f9 interfaceC1467f9, E9 e9, com.android.tools.r8.graph.B5 b5, C0483Fe c0483Fe, B9 b9, com.android.tools.r8.graph.B1 b1) {
        InterfaceC2625sk interfaceC2625sk = this.b.b;
        if (interfaceC2625sk == null) {
            return null;
        }
        return interfaceC2625sk.a(abstractC2004lX, interfaceC1187br, ll, interfaceC1467f9, e9, b5, c0483Fe, b9, b1);
    }

    @Override // com.android.tools.r8.internal.C3053xk
    public final void b() {
        this.b.a.forEach(new Consumer() { // from class: vhi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceC2968wk) obj).a();
            }
        });
    }

    @Override // com.android.tools.r8.internal.C3053xk
    public final boolean a() {
        return this.b.b != null;
    }
}
