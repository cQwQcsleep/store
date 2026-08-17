package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.Z5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C1727iB;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.nio.ShortBuffer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.z1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0138z1 implements InterfaceC0022c, com.android.tools.r8.utils.structural.x<AbstractC0138z1> {
    public static final AbstractC0138z1[] c = new AbstractC0138z1[0];
    public static final int[] d = new int[0];
    public static final /* synthetic */ boolean e = true;
    public int b;

    public AbstractC0138z1(InterfaceC0012a interfaceC0012a) {
        this.b = ((A1) interfaceC0012a).d - 1;
    }

    public static String c(int i) {
        return Wf0.a(i, 2);
    }

    public static short d(int i, int i2) {
        return (short) (((i & 255) << 8) | (i2 & 255));
    }

    public boolean A() {
        return false;
    }

    public boolean B() {
        return this instanceof C0040f2;
    }

    public boolean C() {
        return false;
    }

    public boolean D() {
        return this instanceof C0127x0;
    }

    public boolean F() {
        return !D() && (this instanceof P2);
    }

    public boolean H() {
        return false;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public abstract int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a);

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        AbstractC0138z1 abstractC0138z1 = (AbstractC0138z1) xVar;
        int iA = abstractC3519a.a(k(), abstractC0138z1.k());
        if (iA != 0) {
            return iA;
        }
        int iA2 = abstractC3519a.a(q(), abstractC0138z1.q());
        return iA2 != 0 ? iA2 : a(abstractC0138z1, abstractC3519a);
    }

    public abstract String a(C1581ga0 c1581ga0);

    public abstract void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer);

    public abstract void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj);

    public abstract void a(C0602Jt c0602Jt);

    public abstract String b(C1581ga0 c1581ga0);

    public final String b(String str) {
        StringBuilder sb = new StringBuilder();
        Wf0.a(6, Wf0.a(q(), 2), sb);
        sb.append(": ");
        Wf0.b(20, m(), sb);
        if (str == null) {
            str = XmlPullParser.NO_NAMESPACE;
        }
        sb.append(str);
        return sb.toString();
    }

    public abstract void b(com.android.tools.r8.utils.structural.o oVar);

    public final String e(int i) {
        return Wf0.a(q() + i, 2) + " (" + b(i) + ")";
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public void f(int i) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.H
    public final boolean g() {
        return i();
    }

    public C0245l1 getField() {
        return null;
    }

    public C0133y1 h() {
        return null;
    }

    public abstract int hashCode();

    public boolean i() {
        return false;
    }

    public com.android.tools.r8.graph.D0 j() {
        return null;
    }

    public int k() {
        return r();
    }

    public C0322w2 l() {
        return null;
    }

    public abstract String m();

    @Override // com.android.tools.r8.dex.code.InterfaceC0022c
    public final AbstractC3175z9 n() {
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.dex.code.InterfaceC0022c
    public final AbstractC0138z1 p() {
        return this;
    }

    public int q() {
        return this.b;
    }

    public abstract int r();

    public int s() {
        return 0;
    }

    public abstract int t();

    public String toString() {
        return b(C1581ga0.b);
    }

    public abstract String u();

    public int[] w() {
        return null;
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return false;
    }

    public boolean z() {
        return false;
    }

    public X f() {
        return null;
    }

    public Z c() {
        return null;
    }

    public C0028d0 d() {
        return null;
    }

    public AbstractC0138z1() {
        this.b = -1;
    }

    public void a(Z5 z5) {
    }

    public static char a(InterfaceC0012a interfaceC0012a) {
        return (char) (((A1) interfaceC0012a).b() & 65535);
    }

    public static int a(A1 a1) {
        return ((((char) (a1.b() & 65535)) << 16) & (-65536)) | (((char) (a1.b() & 65535)) & 65535);
    }

    public C0033e0 e() {
        return null;
    }

    public static void a(int i, int i2, ShortBuffer shortBuffer) {
        shortBuffer.put((short) (((i & 255) << 8) | (i2 & 255)));
    }

    public static int e(int i, int i2) {
        return ((i & 15) << 4) | (i2 & 15);
    }

    public static void a(int i, int i2, ShortBuffer shortBuffer, int i3) {
        shortBuffer.put((short) (((i & 15) << 12) | ((i2 & 15) << 8) | (i3 & 255)));
    }

    public static void a(long j, ShortBuffer shortBuffer) {
        shortBuffer.put((short) (j & 65535));
        shortBuffer.put((short) ((j >> 16) & 65535));
    }

    public static String b(int i) {
        if (i < 0) {
            return Integer.toString(i);
        }
        return "+" + i;
    }

    public static void a(com.android.tools.r8.graph.X3 x3, ShortBuffer shortBuffer, C0284q5 c0284q5) {
        int iA = x3.a(c0284q5);
        if (e || iA == (65535 & iA)) {
            shortBuffer.put((short) iA);
        } else {
            x1f.a();
        }
    }

    public O b() {
        return null;
    }

    public final String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        if (str != null) {
            Wf0.b(20, u(), sb);
            sb.append(str);
        } else {
            sb.append(u());
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.a.a(k());
        qVar.a.a(q());
        b(oVar);
    }

    public String a(AbstractC0138z1 abstractC0138z1) {
        throw new C1727iB("Instruction " + abstractC0138z1 + " is not a payload user");
    }

    public String a(C1581ga0 c1581ga0, AbstractC0138z1 abstractC0138z1) {
        throw new C1727iB("Instruction " + abstractC0138z1 + " is not a payload user");
    }
}
