package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.A9;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.B9;
import com.android.tools.r8.internal.C0483Fe;
import com.android.tools.r8.internal.E9;
import com.android.tools.r8.internal.InterfaceC1187br;
import com.android.tools.r8.internal.InterfaceC1467f9;
import com.android.tools.r8.internal.LL;
import com.android.tools.r8.synthesis.S;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0385Bk implements A9 {
    public static final /* synthetic */ boolean f = true;
    public final C0333y a;
    public final Set b;
    public final Set c;
    public final C0956Xk d;
    public final Set e;

    public C0385Bk(C0333y c0333y, AbstractC2554rv abstractC2554rv, Set set) {
        this.a = c0333y;
        c0333y.a();
        this.b = abstractC2554rv;
        this.c = set;
        this.d = new C0956Xk(c0333y);
        if (c0333y.M().u1.t0) {
            this.e = C1755ib0.a();
        } else {
            this.e = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0101  */
    @Override // com.android.tools.r8.internal.A9
    public final C3053xk a(final com.android.tools.r8.graph.B5 b5, AbstractC3175z9 abstractC3175z9) {
        boolean zA;
        com.android.tools.r8.graph.H0 h0P;
        com.android.tools.r8.graph.E0 e0D;
        com.android.tools.r8.graph.H0 h0B;
        if (!abstractC3175z9.H()) {
            return C3053xk.a;
        }
        final G9 g9K = abstractC3175z9.k();
        if (a(b5.s(), this.d, this.a) || this.a.a().h3.contains(b5.s())) {
            zA = false;
        } else {
            G9 g9K2 = g9K.k();
            C0322w2 c0322w2T = g9K2.T();
            C0229j c0229jH = this.a.h();
            if (g9K2.b(b5.s())) {
                C0333y c0333y = this.a;
                c0229jH.getClass();
                h0P = c0229jH.a(c0322w2T, b5.a(), c0333y, c0229jH);
            } else {
                h0P = c0229jH.b(c0322w2T, g9K2.V()).p();
            }
            if (h0P == null) {
                zA = false;
            } else {
                com.android.tools.r8.graph.I2 i2S = h0P.s();
                C0333y c0333y2 = this.a;
                if (c0333y2.z.a(c0333y2, i2S) || i2S.I0() || (e0D = this.a.d(i2S)) == null || !e0D.b0() || ((this.c.contains(h0P.getReference().x0()) && (h0B = this.a.h().b(h0P.a(), h0P.getReference())) != null && this.a.M().K1.c.g().containsKey(h0B.s())) || a(g9K2, b5))) {
                    zA = false;
                } else {
                    zA = this.a.M().K1.h().get(h0P.getReference()) != null ? true : this.a.z.a(h0P.getReference().C0(), this.a);
                }
            }
        }
        return !zA ? C3053xk.a : new C2796uk(AbstractC1630h5.a(new InterfaceC2625sk() { // from class: vw0
            @Override // com.android.tools.r8.internal.InterfaceC2625sk
            public final Collection a(AbstractC2004lX abstractC2004lX, InterfaceC1187br interfaceC1187br, LL ll, InterfaceC1467f9 interfaceC1467f9, E9 e9, B5 b6, C0483Fe c0483Fe, B9 b9, B1 b1) {
                return this.a.a(g9K, b5, abstractC2004lX, interfaceC1187br, ll, interfaceC1467f9, e9, b6, c0483Fe, b9, b1);
            }
        }));
    }

    public static boolean a(com.android.tools.r8.graph.I2 i2, C0956Xk c0956Xk, C0333y c0333y) {
        return c0956Xk.b(i2) || c0333y.a.g().a(i2, new com.android.tools.r8.synthesis.I() { // from class: tw0
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.L;
            }
        });
    }

    public static boolean a(com.android.tools.r8.graph.I2 i2) {
        return i2.f.toString().startsWith("L$-vivified-$/");
    }

    public final boolean a(final G9 g9, final com.android.tools.r8.graph.B5 b5) {
        return AbstractC3179zC.b(this.b, new EX() { // from class: uw0
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((A9) obj).a(b5, g9).a();
            }
        });
    }

    public static C0322w2 a(C0333y c0333y, C0322w2 c0322w2, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2[] i2Arr = (com.android.tools.r8.graph.I2[]) c0322w2.i.f.b.clone();
        int i = 0;
        for (com.android.tools.r8.graph.I2 i3 : c0322w2.i.f.b) {
            if (c0333y.z.a(c0333y, i3)) {
                i2Arr[i] = a(c0333y, i3);
            }
            i++;
        }
        com.android.tools.r8.graph.I2 i2A = c0322w2.i.e;
        if (c0333y.z.a(c0333y, i2A)) {
            i2A = a(c0333y, i2A);
        }
        return c0333y.a().a(i2, c0333y.a().a(i2A, i2Arr), c0322w2.g);
    }

    public final void a() {
        a(this.e, XmlPullParser.NO_NAMESPACE, this.a);
    }

    public static void a(Set set, String str, C0333y c0333y) {
        if (c0333y.M().u1.t0) {
            StringBuilder sb = new StringBuilder("Tracked ");
            sb.append(str);
            sb.append("desugared API conversions: ");
            Iterator it = set.iterator();
            while (it.hasNext()) {
                C0322w2 c0322w2 = (C0322w2) it.next();
                sb.append("\n");
                sb.append(c0322w2);
            }
            c0333y.M().i.warning(new StringDiagnostic(sb.toString()));
            set.clear();
        }
    }

    public static com.android.tools.r8.graph.I2 a(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2D = c0333y.a().d(C0929Wj.I("$-vivified-$." + i2.toString()));
        c0333y.z.a(i2D, i2);
        return i2D;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    public final Collection a(G9 g9, com.android.tools.r8.graph.B5 b5, AbstractC2004lX abstractC2004lX, InterfaceC1187br interfaceC1187br, LL ll, InterfaceC1467f9 interfaceC1467f9, E9 e9, com.android.tools.r8.graph.B5 b6, C0483Fe c0483Fe, B9 b9, com.android.tools.r8.graph.B1 b1) {
        boolean zM;
        com.android.tools.r8.graph.H0 h0P;
        C0322w2 c0322w2T = g9.T();
        Set set = this.e;
        if (set != null) {
            set.add(c0322w2T);
        }
        if (this.a.M().u1.i || g9.b(b5.s()) || g9.T().b(this.a.a())) {
            zM = false;
        } else {
            C0322w2 c0322w2T2 = g9.T();
            C0229j c0229jH = this.a.h();
            if (g9.b(b5.s())) {
                C0333y c0333y = this.a;
                c0229jH.getClass();
                h0P = c0229jH.a(c0322w2T2, b5.a(), c0333y, c0229jH);
            } else {
                h0P = c0229jH.b(c0322w2T2, g9.V()).p();
            }
            if (!f && h0P == null) {
                x1f.a();
                return null;
            }
            if (this.a.M().K1.c.p.contains(h0P.getReference())) {
                zM = false;
            } else {
                zM = h0P.getAccessFlags().m();
            }
        }
        C0956Xk c0956Xk = this.d;
        if (zM) {
            return Collections.singletonList(new G9(184, c0956Xk.d.a(g9, e9, b5, c0483Fe).getReference(), false));
        }
        return c0956Xk.d.a(g9, c0483Fe, interfaceC1187br, ll, e9, b5);
    }
}
