package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.AbstractC0014a1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.hbg;
import java.nio.ShortBuffer;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.a1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0014a1 extends AbstractC0138z1 {
    public static final /* synthetic */ boolean h = true;
    public final short f;
    public final long g;

    public AbstractC0014a1(int i, A1 a1) {
        super(a1);
        this.f = (short) i;
        this.g = ((((((long) ((char) (a1.b() & 65535))) & 65535) << 16) | (((long) ((char) (a1.b() & 65535))) & 65535)) << 32) | (((long) ((char) (a1.b() & 65535))) & 65535) | ((((long) ((char) (a1.b() & 65535))) & 65535) << 16);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(this.f, r(), shortBuffer);
        long j = this.g;
        AbstractC0138z1.a(j, shortBuffer);
        AbstractC0138z1.a(j >> 32, shortBuffer);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        hbg hbgVar = new hbg();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        hbgVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ ((((int) this.g) << 8) | this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int t() {
        return 5;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: ibg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((AbstractC0014a1) obj).f;
            }
        }).a(new ToLongFunction() { // from class: jbg
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((AbstractC0014a1) obj).g;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (AbstractC0014a1) abstractC0138z1, new hbg());
    }

    public AbstractC0014a1(int i, long j) {
        if (!h && (i < 0 || i > 255)) {
            x1f.a();
            throw null;
        }
        this.f = (short) i;
        this.g = j;
    }
}
