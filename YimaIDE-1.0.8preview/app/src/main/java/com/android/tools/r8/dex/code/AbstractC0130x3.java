package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.RJ;
import java.nio.ShortBuffer;
import java.util.function.Function;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: renamed from: com.android.tools.r8.dex.code.x3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0130x3 extends J0<C0245l1> {
    public AbstractC0130x3(int i, InterfaceC0012a interfaceC0012a, C0245l1[] c0245l1Arr) {
        super(i, interfaceC0012a, c0245l1Arr);
    }

    public static /* synthetic */ C0245l1 b(J0 j0) {
        return (C0245l1) j0.g;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        C0245l1 c0245l1E = abstractC3148ys.e(abstractC3148ys2, getField());
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        AbstractC0138z1.a(c0245l1E, shortBuffer, c0284q5);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public C0245l1 getField() {
        return (C0245l1) this.g;
    }

    public AbstractC0130x3(int i, C0245l1 c0245l1) {
        super(i, c0245l1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
        c0333y.A().e(abstractC3148ys, getField()).a(c0333y, m);
    }

    @Override // com.android.tools.r8.dex.code.J0
    public final void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: com.android.tools.r8.dex.code.l5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC0130x3.b((J0) obj);
            }
        });
    }
}
