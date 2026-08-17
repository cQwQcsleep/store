package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.C1819jJ;
import defpackage.pah;
import defpackage.x0g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jJ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1819jJ {
    public static final C1819jJ j = new C1819jJ(null, null, null, null, null, null, null, null, null);
    public static final /* synthetic */ boolean k = true;
    public final String a;
    public final C0322w2 b;
    public final com.android.tools.r8.graph.E2 c;
    public final C0336y2 d;
    public final List e;
    public final Set f;
    public final com.android.tools.r8.graph.K2 g;
    public final com.android.tools.r8.graph.F4 h;
    public final com.android.tools.r8.graph.I2 i;

    public C1819jJ(C0333y c0333y, C0229j c0229j, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.D0 d0, com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.E2 e2, com.android.tools.r8.graph.E2 e3, C0336y2 c0336y2, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.K2 k2) {
        boolean z = k;
        if (!z && c0229j == null) {
            x1f.a();
            throw null;
        }
        if (!z && d0 == null) {
            x1f.a();
            throw null;
        }
        if (!z && h2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && e2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && e3 == null) {
            x1f.a();
            throw null;
        }
        if (!z && c0336y2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && i2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && k2 == null) {
            x1f.a();
            throw null;
        }
        this.b = c0229j.a().a(i2, e2, h2);
        d0.getClass();
        com.android.tools.r8.graph.C0 c0 = new com.android.tools.r8.graph.C0(d0);
        try {
            c0.a = new ByteArrayOutputStream();
            c0.b = new ObjectOutputStream(c0.a);
            c0.a(d0.e);
            c0.a(d0.f);
            c0.a(d0.g);
            c0.a(d0.h);
            c0.b.close();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(c0.a.toByteArray());
            C2912w5 c2912w5 = AbstractC3083y5.a;
            this.a = (c2912w5.c == null ? c2912w5 : c2912w5.a(c2912w5.b)).a(messageDigest.digest());
            this.c = e3;
            this.d = c0336y2;
            this.g = k2;
            this.f = AbstractC2780ub0.c();
            ArrayList arrayList = new ArrayList();
            this.e = arrayList;
            arrayList.add(i2);
            com.android.tools.r8.graph.H0 h0A = b5 == null ? null : a(c0333y, c0229j, b5);
            if (h0A == null) {
                this.h = null;
                this.i = null;
            } else {
                com.android.tools.r8.graph.F4 accessFlags = h0A.getAccessFlags();
                this.h = new com.android.tools.r8.graph.F4(accessFlags.b, accessFlags.c);
                this.i = h0A.s();
            }
        } catch (IOException | NoSuchAlgorithmException unused) {
            x0g.a("Cannot get SHA-1 message digest");
            throw null;
        }
    }

    public static C1819jJ a(com.android.tools.r8.graph.D0 d0, C0333y c0333y, C0229j c0229j, com.android.tools.r8.graph.B5 b5) {
        int i;
        if (!a(d0, c0229j)) {
            return j;
        }
        com.android.tools.r8.graph.B1 b1A = c0229j.a();
        C0322w2 c0322w2P0 = d0.g.p0();
        com.android.tools.r8.graph.H2 h2 = d0.e;
        int i2 = 0;
        com.android.tools.r8.graph.U2 u2 = (com.android.tools.r8.graph.U2) a(d0.h, 0, com.android.tools.r8.graph.U2.class);
        C0336y2 c0336y2 = (C0336y2) ((com.android.tools.r8.graph.T2) a(d0.h, 1, com.android.tools.r8.graph.T2.class)).c;
        com.android.tools.r8.graph.U2 u3 = (com.android.tools.r8.graph.U2) a(d0.h, 2, com.android.tools.r8.graph.U2.class);
        com.android.tools.r8.graph.E2 e2 = (com.android.tools.r8.graph.E2) u3.c;
        com.android.tools.r8.graph.E2 e3 = (com.android.tools.r8.graph.E2) u2.c;
        C0322w2 c0322w2P1 = c0336y2.p0();
        com.android.tools.r8.graph.I2 i3 = e2.e;
        if (a(b1A, i3, e3.e) ? true : i3.T0() ? AbstractC2333pJ.a(b1A, c0322w2P1.D0(), i3) : false) {
            com.android.tools.r8.graph.I2[] i2Arr = e2.f.b;
            com.android.tools.r8.graph.I2[] i2Arr2 = e3.f.b;
            int length = i2Arr.length;
            if (length == i2Arr2.length) {
                for (int i4 = 0; i4 < length; i4++) {
                    if (a(b1A, i2Arr[i4], i2Arr2[i4])) {
                    }
                }
                com.android.tools.r8.graph.E2 e4 = d0.f;
                final C1819jJ c1819jJ = new C1819jJ(c0333y, c0229j, b5, d0, h2, (com.android.tools.r8.graph.E2) u2.c, (com.android.tools.r8.graph.E2) u3.c, c0336y2, e4.e, e4.f);
                boolean zA = b1A.U5.a(c0322w2P0);
                List list = d0.h;
                if (!zA) {
                    Consumer consumer = new Consumer() { // from class: jdh
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            C1819jJ.a(this.b, (I2) obj);
                        }
                    };
                    final Set set = c1819jJ.f;
                    Objects.requireNonNull(set);
                    Consumer consumer2 = new Consumer() { // from class: kdh
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            set.add((E2) obj);
                        }
                    };
                    int i5 = ((com.android.tools.r8.graph.O2.g) a(list, 3, com.android.tools.r8.graph.O2.g.class)).c;
                    if (!k && (i5 & (-8)) != 0) {
                        x1f.a();
                        return null;
                    }
                    if ((i5 & 2) != 0) {
                        int i6 = ((com.android.tools.r8.graph.O2.g) a(list, 4, com.android.tools.r8.graph.O2.g.class)).c;
                        i = 5;
                        int i7 = 0;
                        while (i7 < i6) {
                            consumer.accept((com.android.tools.r8.graph.I2) ((com.android.tools.r8.graph.O2.k) a(list, i, com.android.tools.r8.graph.O2.k.class)).c);
                            i7++;
                            i++;
                        }
                    } else {
                        i = 4;
                    }
                    if ((i5 & 1) != 0) {
                        consumer.accept(b1A.G5);
                    }
                    if ((i5 & 4) != 0) {
                        int i8 = i + 1;
                        int i9 = ((com.android.tools.r8.graph.O2.g) a(list, i, com.android.tools.r8.graph.O2.g.class)).c;
                        while (true) {
                            i = i8;
                            if (i2 >= i9) {
                                break;
                            }
                            i8 = i + 1;
                            consumer2.accept((com.android.tools.r8.graph.E2) ((com.android.tools.r8.graph.U2) a(list, i, com.android.tools.r8.graph.U2.class)).c);
                            i2++;
                        }
                    }
                    if (list.size() != i) {
                        x0g.a("Unexpected number of metafactory method arguments in DexCallSite");
                        return null;
                    }
                } else if (list.size() != 3) {
                    throw new Kk0("Unexpected number of metafactory method arguments in " + d0.toString());
                }
                return c1819jJ;
            }
        }
        throw new Kk0("Enforced and erased signatures are inconsistent in " + d0.toString());
    }

    public static C1819jJ b(com.android.tools.r8.graph.D0 d0, C0333y c0333y, C0229j c0229j, com.android.tools.r8.graph.B5 b5) {
        C1819jJ c1819jJA = a(d0, c0333y, c0229j, b5);
        if (c1819jJA == j) {
            return null;
        }
        return c1819jJA;
    }

    public C1819jJ(String str, C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, C0336y2 c0336y2, ArrayList arrayList, Set set, com.android.tools.r8.graph.K2 k2, com.android.tools.r8.graph.F4 f4, com.android.tools.r8.graph.I2 i2) {
        this.a = str;
        this.b = c0322w2;
        this.c = e2;
        this.d = c0336y2;
        this.e = arrayList;
        this.f = set;
        this.g = k2;
        this.h = f4;
        this.i = i2;
    }

    public final com.android.tools.r8.graph.I2 a() {
        com.android.tools.r8.graph.I2[] i2Arr = this.c.f.b;
        com.android.tools.r8.graph.I2[] i2Arr2 = this.g.b;
        if (k || i2Arr2.length > 0 || i2Arr.length > 0) {
            return i2Arr2.length > 0 ? i2Arr2[0] : i2Arr[0];
        }
        x1f.a();
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bd, code lost:
    
        if (a(r3) != false) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final com.android.tools.r8.graph.H0 a(C0333y c0333y, C0229j c0229j, com.android.tools.r8.graph.B5 b5) {
        boolean z = k;
        if (!z && b5 == null) {
            x1f.a();
            return null;
        }
        C0322w2 c0322w2P0 = this.d.p0();
        switch (this.d.e.ordinal()) {
            case 4:
                com.android.tools.r8.graph.H0 h0B = c0229j.b(c0322w2P0, b5, c0333y, c0229j);
                if (z || h0B == null || h0B.getAccessFlags().n()) {
                    return h0B;
                }
                x1f.a();
                return null;
            case XmlPullParser.CDSECT /* 5 */:
            case 7:
                com.android.tools.r8.graph.H0 h0P = c0229j.b(c0322w2P0, a(), this.d.g).p();
                if (h0P == null) {
                    h0P = c0229j.a(c0322w2P0, b5, c0333y, c0229j);
                }
                if (!z && h0P != null && ((!this.d.e.d() || !a(h0P)) && (!this.d.e.c() || !h0P.getAccessFlags().i() || !a(h0P)))) {
                    if (this.d.e.c()) {
                        C0231j1 c0231j1E = h0P.e();
                        c0231j1E.O0();
                        if (c0231j1E.g.j()) {
                        }
                        break;
                    }
                    x1f.a();
                    return null;
                }
                return h0P;
            case XmlPullParser.ENTITY_REF /* 6 */:
                com.android.tools.r8.graph.H0 h0A = c0229j.a(c0322w2P0, b5, c0333y, c0229j);
                if (z || h0A == null || h0A.getAccessFlags().L()) {
                    return h0A;
                }
                x1f.a();
                return null;
            case 8:
                com.android.tools.r8.graph.H0 h0P2 = c0229j.c(a(), c0322w2P0).p();
                if (z || h0P2 == null || a(h0P2)) {
                    return h0P2;
                }
                x1f.a();
                return null;
            default:
                pah.a("Unexpected method handle kind in ", this.d);
                return null;
        }
    }

    public static boolean a(com.android.tools.r8.graph.H0 h0) {
        return (h0.getAccessFlags().L() || h0.getAccessFlags().n()) ? false : true;
    }

    public static boolean a(com.android.tools.r8.graph.D0 d0, InterfaceC0189d1 interfaceC0189d1) {
        if (!d0.g.e.f()) {
            return false;
        }
        com.android.tools.r8.graph.B1 b1A = interfaceC0189d1.a();
        C0322w2 c0322w2P0 = d0.g.p0();
        return c0322w2P0 == b1A.U5 || c0322w2P0 == b1A.V5;
    }

    public final void a(BiConsumer biConsumer) {
        com.android.tools.r8.graph.E2 e2C0 = this.b.C0();
        biConsumer.accept(e2C0.r0(), this.c.r0());
        for (int i = 0; i < this.c.p0(); i++) {
            biConsumer.accept(e2C0.f.b[i], this.c.f.b[i]);
        }
    }

    public static /* synthetic */ void a(C1819jJ c1819jJ, com.android.tools.r8.graph.I2 i2) {
        if (c1819jJ.e.contains(i2)) {
            return;
        }
        c1819jJ.e.add(i2);
    }

    public static com.android.tools.r8.graph.O2 a(List list, int i, Class cls) {
        if (list.size() >= i) {
            com.android.tools.r8.graph.O2 o2 = (com.android.tools.r8.graph.O2) list.get(i);
            if (cls.isAssignableFrom(o2.getClass())) {
                return o2;
            }
            throw new Kk0("Unexpected type of bootstrap arguments #" + i + " in DexCallSite");
        }
        throw new Kk0("Expected to find at least " + i + " bootstrap arguments in DexCallSite");
    }

    public static boolean a(com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        i2.getClass();
        if (com.android.tools.r8.graph.I2.a(i2, i3) || (i2.M0() && i3.M0())) {
            return true;
        }
        if (!i2.I0()) {
            return false;
        }
        if (i3.I0()) {
            return a(b1, i2.a(1, b1), i3.a(1, b1));
        }
        return com.android.tools.r8.graph.I2.a(i3, b1.a2);
    }

    public final C1819jJ a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2, final RJ rj) {
        String str = this.a;
        C0322w2 c0322w2D = abstractC3148ys.d(abstractC3148ys2, this.b);
        com.android.tools.r8.graph.E2 e2A = rj.a(this.c);
        C0336y2 c0336y2A = rj.a(this.d, 1, this.b);
        ArrayList arrayList = new ArrayList(C1755ib0.b(this.e, new Function() { // from class: gdh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.c(abstractC3148ys2, (I2) obj);
            }
        }));
        Set setA = C1755ib0.a(this.f, new Function() { // from class: hdh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return rj.a((E2) obj);
            }
        });
        com.android.tools.r8.graph.K2 k2N0 = this.g;
        Function function = new Function() { // from class: idh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.c(abstractC3148ys2, (I2) obj);
            }
        };
        if (k2N0.isEmpty()) {
            k2N0 = com.android.tools.r8.graph.K2.n0();
        } else {
            com.android.tools.r8.graph.I2[] i2Arr = (com.android.tools.r8.graph.I2[]) R3.a((Object[]) k2N0.b, function, (Object[]) com.android.tools.r8.graph.I2.h);
            if (i2Arr != k2N0.b) {
                k2N0 = i2Arr.length == 0 ? com.android.tools.r8.graph.K2.n0() : new com.android.tools.r8.graph.K2(i2Arr);
            }
        }
        com.android.tools.r8.graph.K2 k2 = k2N0;
        com.android.tools.r8.graph.F4 f4 = this.h;
        com.android.tools.r8.graph.I2 i2 = this.i;
        return new C1819jJ(str, c0322w2D, e2A, c0336y2A, arrayList, setA, k2, f4, i2 != null ? abstractC3148ys.c(abstractC3148ys2, i2) : null);
    }
}
