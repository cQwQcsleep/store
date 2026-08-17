package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.P0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.yta;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class P0 extends K {
    public static final /* synthetic */ boolean i = true;
    public final byte f;
    public final byte g;
    public final short h;

    public P0(int i2, int i3, int i4) {
        boolean z = i;
        if (!z && (i2 < 0 || i2 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i3 < 0 || i3 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (-32768 > i4 || i4 > 32767)) {
            x1f.a();
            throw null;
        }
        this.f = (byte) i2;
        this.g = (byte) i3;
        this.h = (short) i4;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        byte b = this.f;
        byte b2 = this.g;
        return a("v" + ((int) b) + ", v" + ((int) b2) + ", " + Wf0.a((int) this.h, 4) + "  # " + ((int) this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        return b("v" + ((int) this.f) + ", v" + ((int) this.g) + ", #" + ((int) this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((this.h << 8) | (this.f << 4)) | this.g);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        yta ytaVar = new yta();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        ytaVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public P0(int i2, A1 a1) {
        super(a1);
        this.f = (byte) (i2 & 15);
        this.g = (byte) ((i2 >> 4) & 15);
        this.h = (short) a1.b();
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: aua
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((P0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: cua
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((P0) obj).g;
            }
        }).a(new ToIntFunction() { // from class: eua
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((P0) obj).h;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.g, this.f, shortBuffer, r());
        shortBuffer.put(this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (P0) abstractC0138z1, new yta());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }
}
