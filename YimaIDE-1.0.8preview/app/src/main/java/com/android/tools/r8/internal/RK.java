package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2490r8;
import com.android.tools.r8.internal.InterfaceC1959kz;
import com.android.tools.r8.internal.RK;
import com.android.tools.r8.utils.structural.A;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RK implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean c = true;
    public final C2986wz b;

    public RK(InterfaceC2045lz interfaceC2045lz) {
        if (c || !interfaceC2045lz.isEmpty()) {
            this.b = new C2986wz(interfaceC2045lz);
        } else {
            x1f.a();
            throw null;
        }
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        Function function = new Function() { // from class: m5c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RK) obj).b;
            }
        };
        QK qk = new QK();
        a.getClass();
        com.android.tools.r8.utils.structural.z zVar = new com.android.tools.r8.utils.structural.z(qk, qk);
        a.a(function, zVar, zVar);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: n5c
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                RK.a(a);
            }
        };
    }

    public final RK a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        InterfaceC2045lz interfaceC2045lzA = AbstractC0778Qo.a(this.b, new InterfaceC2665tA() { // from class: o5c
            @Override // com.android.tools.r8.internal.InterfaceC2665tA
            public final Object a(int i, Object obj) {
                return ((C2490r8) obj).a(abstractC3148ys, abstractC3148ys2);
            }
        });
        return interfaceC2045lzA != null ? new RK(interfaceC2045lzA) : this;
    }

    public final boolean a(final InterfaceC2580sA interfaceC2580sA) {
        return AbstractC3179zC.b(this.b.c(), new EX() { // from class: p5c
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) obj;
                return interfaceC2580sA.a(interfaceC1959kz.a(), (C2490r8) interfaceC1959kz.getValue());
            }
        });
    }
}
