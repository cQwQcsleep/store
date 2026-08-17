package com.android.tools.r8.graph;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.AbstractC2856vU;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2427qS;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.le6;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class I2 extends F2 implements InterfaceC0221h5 {
    public static final I2[] h = new I2[0];
    public static final AbstractC0551Hu i;
    public static final /* synthetic */ boolean j = true;
    public final H2 f;
    public String g = null;

    static {
        Object[] objArrA = AbstractC2856vU.a(9, new Object[]{"$r8$backportedMethods$utility", "$r8$java8methods$utility", "$r8$twr$utility", "$-DC", "$$ServiceLoaderMethods", "com.android.tools.r8.GeneratedOutlineSupport", "-$$Nest$Constructor", "-$$Lambda$", "-$$LambdaGroup$"});
        i = AbstractC0551Hu.b(objArrA.length, objArrA);
    }

    public I2(H2 h2) {
        if (j || !h2.toString().contains(".")) {
            this.f = h2;
            return;
        }
        throw new AssertionError("Malformed descriptor: " + h2.toString());
    }

    public String A0() {
        if (j || M0() || I0()) {
            return C0929Wj.a(Z0());
        }
        x1f.a();
        return null;
    }

    public String B0() {
        return T0() ? m0() : a(false);
    }

    public final int C0() {
        int i2 = 0;
        while (this.f.f[i2] == 91) {
            i2++;
        }
        return i2;
    }

    public String D0() {
        return a(true);
    }

    public String E0() {
        return C0929Wj.p(Y0());
    }

    public final int F0() {
        if (j || !W0()) {
            return X0() ? 2 : 1;
        }
        x1f.a();
        return 0;
    }

    public final String G0() {
        if (j || M0()) {
            return C0929Wj.v(Z0());
        }
        x1f.a();
        return null;
    }

    public String H0() {
        return m0();
    }

    public boolean I0() {
        return ((char) this.f.f[0]) == '[';
    }

    public boolean J0() {
        return ((char) this.f.f[0]) == 'Z';
    }

    public final boolean K0() {
        return ((char) this.f.f[0]) == 'B';
    }

    public final boolean L0() {
        return ((char) this.f.f[0]) == 'C';
    }

    public boolean M0() {
        return ((char) this.f.f[0]) == 'L';
    }

    public boolean N0() {
        return ((char) this.f.f[0]) == 'D';
    }

    public final boolean O0() {
        return ((char) this.f.f[0]) == 'F';
    }

    public boolean P0() {
        return ((char) this.f.f[0]) == 'I';
    }

    public boolean Q0() {
        return ((char) this.f.f[0]) == 'J';
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean R0() {
        boolean z = ((char) this.f.f[0]) == 'N';
        if (j || !z || a(this, B1.e6)) {
            return z;
        }
        x1f.a();
        return false;
    }

    public final boolean S0() {
        if (I0()) {
            return C0929Wj.a((char) this.f.f[1]);
        }
        return false;
    }

    public final boolean T0() {
        return C0929Wj.a((char) this.f.f[0]);
    }

    public boolean U0() {
        boolean z = I0() || M0() || R0();
        if (j || z != T0() || W0()) {
            return z;
        }
        x1f.a();
        return false;
    }

    public final boolean V0() {
        return ((char) this.f.f[0]) == 'S';
    }

    public boolean W0() {
        return ((char) this.f.f[0]) == 'V';
    }

    public final boolean X0() {
        return N0() || Q0();
    }

    public final String Y0() {
        String strZ0 = Z0();
        if (j || (strZ0.length() > 1 && strZ0.charAt(0) == 'L' && strZ0.charAt(strZ0.length() - 1) == ';')) {
            return AbstractC0005a.a(1, 1, strZ0);
        }
        x1f.a();
        return null;
    }

    public String Z0() {
        return this.f.toString();
    }

    public final I2 a(int i2, B1 b1) {
        if (!j) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte[] bArr = this.f.f;
                if (i4 >= bArr.length) {
                    break;
                }
                if (bArr[i4] != 91) {
                    i3 = i4;
                    break;
                }
                i4++;
            }
            if (i3 < i2) {
                x1f.a();
                return null;
            }
        }
        H2 h2 = this.f;
        int i5 = h2.e - i2;
        byte[] bArr2 = h2.f;
        return b1.c(b1.a(i5, Arrays.copyOfRange(bArr2, i2, bArr2.length)));
    }

    public final char a1() {
        char c = (char) this.f.f[0];
        if (c == '[') {
            return 'L';
        }
        return c;
    }

    public final I2 b(String str, B1 b1) {
        if (!j && !M0()) {
            x1f.a();
            return null;
        }
        String strZ0 = Z0();
        String str2 = "L";
        if (!str.isEmpty()) {
            str2 = "L" + str + "/";
        }
        return b1.e(str2 + C0929Wj.v(strZ0) + ";");
    }

    public final I2 c(String str, B1 b1) {
        if (j || M0()) {
            return b1.e(C0929Wj.c(Z0(), str));
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String l0() {
        return Z0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        if (this.g == null) {
            if (B1.g6.containsKey(this)) {
                this.g = this.f.toString();
            } else {
                this.g = C0929Wj.b(Z0());
            }
        }
        return this.g;
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return this.f.hashCode();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.graph.F2
    public final I2 r0() {
        return this;
    }

    public String toString() {
        return m0();
    }

    public ClassReference w0() {
        return Reference.classFromDescriptor(Z0());
    }

    public TypeReference x0() {
        return Reference.typeFromDescriptor(Z0());
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 2;
    }

    public final int y0() {
        if (!j && !S0()) {
            x1f.a();
            return 0;
        }
        H2 h2 = this.f;
        byte b = h2.f[1];
        if (b == 70) {
            return 4;
        }
        if (b == 83) {
            return 2;
        }
        if (b != 90) {
            if (b == 73) {
                return 4;
            }
            if (b == 74) {
                return 8;
            }
            switch (b) {
                case 66:
                    break;
                case 67:
                    return 2;
                case 68:
                    return 8;
                default:
                    le6.a("Not array of primitives '", h2, "'");
                    return 0;
            }
        }
        return 1;
    }

    @Override // com.android.tools.r8.graph.F2
    public final I2 z() {
        return this;
    }

    public H2 z0() {
        return this.f;
    }

    public final boolean c(I2 i2) {
        return D0().equals(i2.D0());
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof I2) {
            return this.f.equals(((I2) obj).f);
        }
        return false;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (I2) xVar);
    }

    public final boolean a(I2 i2) {
        return a(this, i2);
    }

    public final AbstractC2624sj0 a(C0333y c0333y, C2427qS c2427qS) {
        return AbstractC2624sj0.a(this, c2427qS, (C0333y<?>) c0333y);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        oVar.a(this);
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (I2) uk);
    }

    public final boolean a(C0333y c0333y, InterfaceC0332x5 interfaceC0332x5) {
        E0 e0D = c0333y.d(this);
        return e0D == null || e0D.a(c0333y, interfaceC0332x5);
    }

    public final boolean a(C0333y c0333y) {
        D2 d2B;
        if (!M0() || (d2B = D2.b(c0333y.d(this))) == null) {
            return false;
        }
        if (!d2B.isInterface() || c0333y.K.a(d2B)) {
            return !((C3403i) c0333y.g()).b(d2B);
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.F2
    public final Object a(Function function, Function function2, Function function3) {
        return function.apply(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer.accept(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        if (m.a(this)) {
            H2 h2C = c0333y.w().c(this);
            h2C.getClass();
            m.a(h2C);
        }
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.g);
    }

    public AbstractC2624sj0 b(C0333y<?> c0333y) {
        return AbstractC2624sj0.a(this, C2427qS.h(), c0333y);
    }

    public I2 a(B1 b1) {
        int iC0 = C0();
        if (iC0 == 0) {
            return this;
        }
        H2 h2 = this.f;
        int i2 = h2.e - iC0;
        byte[] bArr = h2.f;
        return b1.c(b1.a(i2, Arrays.copyOfRange(bArr, iC0, bArr.length)));
    }

    @Override // com.android.tools.r8.graph.F2
    public final int b(F2 f2) {
        f2.getClass();
        if (f2 instanceof I2) {
            return compareTo(f2.r0());
        }
        int iA = compareTo(f2.z());
        if (iA != 0) {
            return iA;
        }
        return -1;
    }

    public final I2 a(B1 b1, I2 i2) {
        boolean z = j;
        if (!z && !I0()) {
            x1f.a();
            return null;
        }
        if (z || !i2.I0()) {
            return b1.c(i2.f.a(C0(), b1));
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        oVar.a(this);
    }

    public final boolean b(I2 i2) {
        return !a(this, i2);
    }

    public final I2 a(String str, B1 b1) {
        if (!j && !M0()) {
            x1f.a();
            return null;
        }
        return b1.e(AbstractC0005a.a(1, 0, Z0()) + str + ";");
    }

    public static boolean a(I2 i2, I2 i3) {
        return i2 == i3;
    }

    public final String a(boolean z) {
        if (!j && !M0()) {
            x1f.a();
            return null;
        }
        String strZ0 = Z0();
        int iLastIndexOf = strZ0.lastIndexOf(47);
        if (iLastIndexOf == -1) {
            if (z) {
                return XmlPullParser.NO_NAMESPACE;
            }
            return AbstractC0005a.a(1, 1, strZ0);
        }
        if (z) {
            return strZ0.substring(1, iLastIndexOf);
        }
        return AbstractC0005a.a(1, iLastIndexOf + 1, strZ0);
    }
}
