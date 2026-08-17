package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.W0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.aef;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class W0 extends L {
    public static final /* synthetic */ boolean h = true;
    public final short f;
    public int g;

    public W0(int i) {
        if (!h && (i < 0 || i > 255)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i;
        this.g = -1;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        if (h || (q() + this.g) % 2 == 0) {
            AbstractC0138z1.a(this.g, shortBuffer);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        short s = this.f;
        return b("v" + ((int) s) + ", " + e(this.g));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ ((this.g << 8) | this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int s() {
        return this.g;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean x() {
        return true;
    }

    public W0(int i, A1 a1) {
        super(a1);
        this.f = (short) i;
        this.g = AbstractC0138z1.a(a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        aef aefVar = new aef();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        aefVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: cef
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((W0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: eef
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((W0) obj).g;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (W0) abstractC0138z1, new aef());
    }
}
