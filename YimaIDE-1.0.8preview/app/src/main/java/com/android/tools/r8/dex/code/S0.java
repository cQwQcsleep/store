package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.S0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.kmc;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S0 extends K {
    public static final /* synthetic */ boolean i = true;
    public final short f;
    public final short g;
    public final short h;

    public S0(int i2, int i3, int i4) {
        boolean z = i;
        if (!z && (i2 < 0 || i2 > 255)) {
            x1f.a();
            throw null;
        }
        if (!z && (i3 < 0 || i3 > 255)) {
            x1f.a();
            throw null;
        }
        if (!z && (i4 < 0 || i4 > 255)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i2;
        this.g = (short) i3;
        this.h = (short) i4;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        return a("v" + ((int) this.f) + ", v" + ((int) this.g) + ", v" + ((int) this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        return b("v" + ((int) this.f) + ", v" + ((int) this.g) + ", v" + ((int) this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((this.f << 16) | (this.g << 8)) | this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        kmc kmcVar = new kmc();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        kmcVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: gmc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((S0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: imc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((S0) obj).g;
            }
        }).a(new ToIntFunction() { // from class: jmc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((S0) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        shortBuffer.put(AbstractC0138z1.d(this.h, this.g));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (S0) abstractC0138z1, new kmc());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    public S0(int i2, InterfaceC0012a interfaceC0012a) {
        super(interfaceC0012a);
        this.f = (short) i2;
        A1 a1 = (A1) interfaceC0012a;
        this.h = (short) a1.a();
        this.g = (short) a1.a();
    }
}
