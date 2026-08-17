package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class I0 extends K {
    public static final /* synthetic */ boolean g = true;
    public short f;

    public I0(int i) {
        if (g || (-32768 <= i && i <= 32767)) {
            this.f = (short) i;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        return a(":label_" + (q() + this.f));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        short s = this.f;
        return b(((int) s) + " " + e(s));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ this.f;
    }

    public I0(A1 a1) {
        super(a1);
        this.f = (short) a1.b();
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a((int) this.f, (int) ((I0) abstractC0138z1).f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        AbstractC0138z1.a(0, 41, shortBuffer);
        shortBuffer.put(this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a((int) this.f);
    }
}
