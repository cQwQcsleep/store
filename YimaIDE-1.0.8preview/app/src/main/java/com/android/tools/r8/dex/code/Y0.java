package com.android.tools.r8.dex.code;

import com.android.tools.r8.dex.code.Y0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.h1g;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Y0 extends L {
    public static final /* synthetic */ boolean m = true;
    public final byte f;
    public final byte g;
    public final byte h;
    public final byte i;
    public final byte j;
    public final byte k;
    public final com.android.tools.r8.graph.X3 l;

    public Y0(int i, com.android.tools.r8.graph.X3 x3, int i2, int i3, int i4, int i5, int i6) {
        boolean z = m;
        if (!z && (i < 0 || i > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i2 < 0 || i2 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i3 < 0 || i3 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i4 < 0 || i4 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i5 < 0 || i5 > 15)) {
            x1f.a();
            throw null;
        }
        if (!z && (i6 < 0 || i6 > 15)) {
            x1f.a();
            throw null;
        }
        this.f = (byte) i;
        this.l = x3;
        this.g = (byte) i2;
        this.h = (byte) i3;
        this.i = (byte) i4;
        this.j = (byte) i5;
        this.k = (byte) i6;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        StringBuilder sb = new StringBuilder("{ ");
        int[] iArr = {this.g, this.h, this.i, this.j, this.k};
        for (int i = 0; i < this.f; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append("v");
            sb.append(iArr[i]);
        }
        sb.append(" }, ");
        sb.append(this.l.l0());
        return a(sb.toString());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        StringBuilder sb = new StringBuilder("{ ");
        int[] iArr = {this.g, this.h, this.i, this.j, this.k};
        for (int i = 0; i < this.f; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append("v");
            sb.append(iArr[i]);
        }
        sb.append(" } ");
        sb.append(c1581ga0.a(this.l));
        return b(sb.toString());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int hashCode() {
        return getClass().hashCode() ^ (((((((this.l.hashCode() << 24) | (this.f << 20)) | (this.g << 16)) | (this.h << 12)) | (this.i << 8)) | (this.j << 4)) | this.k);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: i1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: j1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).g;
            }
        }).a(new ToIntFunction() { // from class: k1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).h;
            }
        }).a(new ToIntFunction() { // from class: l1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).i;
            }
        }).a(new ToIntFunction() { // from class: m1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).j;
            }
        }).a(new ToIntFunction() { // from class: n1g
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Y0) obj).k;
            }
        }).e(new Function() { // from class: o1g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Y0) obj).l;
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        h1g h1gVar = new h1g();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        h1gVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (Y0) abstractC0138z1, new h1g());
    }

    public Y0(int i, A1 a1, com.android.tools.r8.graph.X3[] x3Arr) {
        super(a1);
        this.k = (byte) (i & 15);
        this.f = (byte) ((i >> 4) & 15);
        this.l = x3Arr[(char) (a1.b() & 65535)];
        short sA = (short) a1.a();
        this.i = (byte) (sA & 15);
        this.j = (byte) ((sA >> 4) & 15);
        short sA2 = (short) a1.a();
        this.g = (byte) (sA2 & 15);
        this.h = (byte) ((sA2 >> 4) & 15);
    }
}
