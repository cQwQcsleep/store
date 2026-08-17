package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.V0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.w3f;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V0 extends L {
    public static final /* synthetic */ boolean h = true;
    public final short f;
    public final int g;

    public V0(int i, int i2) {
        if (!h && (i < 0 || i > 255)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i;
        this.g = i2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: x3f
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((V0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: y3f
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((V0) obj).g;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        w3f w3fVar = new w3f();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        w3fVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ ((this.g << 8) | this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        AbstractC0138z1.a(this.g, shortBuffer);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (V0) abstractC0138z1, new w3f());
    }

    public V0(int i, A1 a1) {
        super(a1);
        this.f = (short) i;
        this.g = AbstractC0138z1.a(a1);
    }
}
