package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.K0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.v28;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class K0 extends K {
    public static final /* synthetic */ boolean h = true;
    public final short f;
    public final char g;

    public K0(int i, int i2) {
        boolean z = h;
        if (!z && (i < 0 || i > 255)) {
            x1f.a();
            throw null;
        }
        if (!z && (i2 < 0 || i2 > 65535)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i;
        this.g = (char) i2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: w28
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((K0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: x28
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((K0) obj).g;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        v28 v28Var = new v28();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        v28Var.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ ((this.g << '\b') | this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        shortBuffer.put((short) this.g);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (K0) abstractC0138z1, new v28());
    }

    public K0(int i, A1 a1) {
        super(a1);
        this.f = (short) i;
        this.g = (char) (a1.b() & 65535);
    }
}
