package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.Q0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2925wD;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0472Et;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C2040lu;
import com.android.tools.r8.internal.C2636ss;
import com.android.tools.r8.internal.EnumC2211nu;
import com.android.tools.r8.internal.Gl0;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.iwb;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Q0 extends K {
    public static final /* synthetic */ boolean i = true;
    public final byte f;
    public final byte g;
    public short h;

    public Q0(int i2, int i3, int i4) {
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

    public abstract Gl0 I();

    public abstract EnumC2211nu J();

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        int iQ = q();
        EnumC2211nu enumC2211nuJ = J();
        Gl0 gl0I = I();
        byte b = this.f;
        byte b2 = this.g;
        int i2 = this.h + iQ;
        int i3 = iQ + 2;
        c0602Jt.getClass();
        if (i2 == i3) {
            if (!C0602Jt.C && i2 != i3) {
                x1f.a();
                return;
            }
            H5 h5 = ((C0472Et) c0602Jt.a.get(i2)).a;
            h5.d();
            c0602Jt.i.g(h5);
            c0602Jt.a(h5, c0602Jt.t.g(i2));
            c0602Jt.a((AbstractC2925wD) new C2636ss());
            return;
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(c0602Jt.b(b, gl0I));
        arrayList.add(c0602Jt.b(b2, gl0I));
        C2040lu c2040lu = new C2040lu(enumC2211nuJ, arrayList);
        H5 h6 = ((C0472Et) c0602Jt.a.get(i2)).a;
        H5 h7 = ((C0472Et) c0602Jt.a.get(i3)).a;
        c0602Jt.i.g(h6);
        c0602Jt.i.g(h7);
        c0602Jt.a(h7, c0602Jt.t.g(i3));
        c0602Jt.a(h6, c0602Jt.t.g(i2));
        c0602Jt.a((AbstractC2925wD) c2040lu);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        byte b = this.f;
        byte b2 = this.g;
        return b("v" + ((int) b) + ", v" + ((int) b2) + ", " + e(this.h));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((this.h << 8) | (this.g << 4)) | this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int[] w() {
        return new int[]{this.h, 2};
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        iwb iwbVar = new iwb();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        iwbVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public Q0(int i2, A1 a1) {
        super(a1);
        this.f = (byte) (i2 & 15);
        this.g = (byte) ((i2 >> 4) & 15);
        this.h = (short) a1.b();
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: fwb
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Q0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: gwb
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Q0) obj).g;
            }
        }).a(new ToIntFunction() { // from class: hwb
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Q0) obj).h;
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
        return abstractC3519a.a(this, (Q0) abstractC0138z1, new iwb());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        byte b = this.f;
        byte b2 = this.g;
        return a("v" + ((int) b) + ", v" + ((int) b2) + ", :label_" + (q() + this.h));
    }
}
