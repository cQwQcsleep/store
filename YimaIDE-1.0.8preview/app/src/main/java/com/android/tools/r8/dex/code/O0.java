package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.O0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O0 extends K {
    public static final /* synthetic */ boolean i = true;
    public final byte f;
    public final byte g;
    public final com.android.tools.r8.graph.F2 h;

    public O0(int i2, int i3, com.android.tools.r8.graph.F2 f2) {
        boolean z = i;
        if (!z && (i2 < 0 || i2 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i3 < 0 || i3 > 15)) {
            x1f.a();
            throw null;
        }
        this.f = (byte) i2;
        this.g = (byte) i3;
        this.h = f2;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        byte b = this.f;
        byte b2 = this.g;
        return a("v" + ((int) b) + ", v" + ((int) b2) + ", " + this.h.l0());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        byte b = this.f;
        byte b2 = this.g;
        return b("v" + ((int) b) + ", v" + ((int) b2) + ", " + c1581ga0.a(this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((this.h.hashCode() << 8) | (this.f << 4)) | this.g);
    }

    public O0(int i2, InterfaceC0012a interfaceC0012a, com.android.tools.r8.graph.F2[] f2Arr) {
        super(interfaceC0012a);
        this.f = (byte) (i2 & 15);
        this.g = (byte) ((i2 >> 4) & 15);
        this.h = f2Arr[AbstractC0138z1.a(interfaceC0012a)];
    }

    public static void b(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: cla
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((O0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: dla
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((O0) obj).g;
            }
        }).c(new Function() { // from class: ela
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((O0) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (O0) abstractC0138z1, (com.android.tools.r8.utils.structural.y<O0>) new com.android.tools.r8.utils.structural.y() { // from class: gla
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                O0.b(a);
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.y yVar = new com.android.tools.r8.utils.structural.y() { // from class: fla
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                O0.b(a);
            }
        };
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        yVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }
}
