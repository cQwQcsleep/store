package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.shaking.K3;
import defpackage.kw5;
import defpackage.x0g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.t3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3461t3 {
    public final List a;
    public final C3470v2 b;
    public final C3470v2 c;
    public final EnumC3476w3 d;
    public final K3 e;
    public final A3 f;
    public final List g;
    public final C3471v3 h;

    public C3461t3(List list, C3470v2 c3470v2, C3470v2 c3470v3, EnumC3476w3 enumC3476w3, K3 k3, A3 a3, List list2, C3471v3 c3471v3) {
        this.a = list;
        this.b = c3470v2;
        this.c = c3470v3;
        this.d = enumC3476w3;
        this.e = k3;
        this.f = a3;
        this.g = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.h = c3471v3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x007b  */
    /* JADX WARN: Code duplicated, block: B:24:0x0088 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x0097  */
    /* JADX WARN: Code duplicated, block: B:31:0x009b  */
    /* JADX WARN: Code duplicated, block: B:34:0x00aa A[LOOP:0: B:29:0x0098->B:34:0x00aa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:39:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0038  */
    public final boolean a(com.android.tools.r8.graph.H0 h0, C0333y c0333y, Consumer consumer, D d) {
        List<K3> listD;
        com.android.tools.r8.graph.I2[] i2Arr;
        int i;
        K3 k3;
        AbstractC3148ys abstractC3148ysA = c0333y.A();
        C0322w2 reference = h0.getReference();
        abstractC3148ysA.getClass();
        C0322w2 c0322w2A = abstractC3148ysA.a(AbstractC3148ys.g(), reference);
        int i2 = AbstractC3456s3.a[h().ordinal()];
        if (i2 != 1) {
            switch (i2) {
                case 4:
                    if (!h0.e().m1()) {
                        if (b().a(h0.getAccessFlags()) && f().b(h0.getAccessFlags())) {
                            return h4.a(this.a, h0, consumer);
                        }
                    }
                    break;
                case XmlPullParser.CDSECT /* 5 */:
                case XmlPullParser.ENTITY_REF /* 6 */:
                case 7:
                    if (!e().a(d.a(c0322w2A.g)) || !b().a(h0.getAccessFlags()) || !f().b(h0.getAccessFlags()) || !h4.a(this.a, h0, consumer)) {
                        return false;
                    }
                    listD = d();
                    if (listD.size() == 1) {
                        k3 = listD.get(0);
                        k3.getClass();
                        if (k3 instanceof M3) {
                            return true;
                        }
                    }
                    i2Arr = c0322w2A.B0().b;
                    if (i2Arr.length == listD.size()) {
                        for (i = 0; i < i2Arr.length; i++) {
                            if (!listD.get(i).a(c0333y, i2Arr[i])) {
                                return false;
                            }
                        }
                        return true;
                    }
                    break;
                case 8:
                    if (this.e.a(c0333y, c0322w2A.D0())) {
                        if (!e().a(d.a(c0322w2A.g))) {
                            listD = d();
                            if (listD.size() == 1) {
                                k3 = listD.get(0);
                                k3.getClass();
                                if (k3 instanceof M3) {
                                    return true;
                                }
                            }
                            i2Arr = c0322w2A.B0().b;
                            if (i2Arr.length == listD.size()) {
                                while (i < i2Arr.length) {
                                    if (!listD.get(i).a(c0333y, i2Arr[i])) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                    break;
            }
        } else if (b().a(h0.getAccessFlags())) {
            return h4.a(this.a, h0, consumer);
        }
        return false;
    }

    public C3470v2 b() {
        return this.b;
    }

    public List<K3> c() {
        return this.a;
    }

    public List<K3> d() {
        return this.g;
    }

    public A3 e() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C3461t3)) {
            return false;
        }
        C3461t3 c3461t3 = (C3461t3) obj;
        if (!this.a.equals(c3461t3.a) || !this.b.equals(c3461t3.b) || !this.c.equals(c3461t3.c) || this.d != c3461t3.d) {
            return false;
        }
        A3 a3 = this.f;
        A3 a4 = c3461t3.f;
        if (a3 == null ? a4 != null : !a3.equals(a4)) {
            return false;
        }
        K3 k3 = this.e;
        K3 k4 = c3461t3.e;
        if (k3 == null ? k4 != null : !k3.equals(k4)) {
            return false;
        }
        List list = this.g;
        List list2 = c3461t3.g;
        if (list != null) {
            return list.equals(list2);
        }
        return list2 == null;
    }

    public C3470v2 f() {
        return this.c;
    }

    public C3471v3 g() {
        return this.h;
    }

    public EnumC3476w3 h() {
        return this.d;
    }

    public final int hashCode() {
        int iHashCode = ((((this.a.hashCode() * 31) + this.b.a) * 31) + this.c.a) * 31;
        EnumC3476w3 enumC3476w3 = this.d;
        int iHashCode2 = (iHashCode + (enumC3476w3 != null ? enumC3476w3.hashCode() : 0)) * 31;
        K3 k3 = this.e;
        int iHashCode3 = (iHashCode2 + (k3 != null ? k3.hashCode() : 0)) * 31;
        A3 a3 = this.f;
        int iHashCode4 = (iHashCode3 + (a3 != null ? a3.hashCode() : 0)) * 31;
        List list = this.g;
        return iHashCode4 + (list != null ? list.hashCode() : 0);
    }

    public K3 i() {
        return this.e;
    }

    public final Iterable j() {
        ArrayList arrayListA = K3.a(this.a);
        Iterable iterableA = K3.a(this.e);
        Iterable iterableA2 = A3.a(this.f);
        List list = this.g;
        return AbstractC0728Oq.a(arrayListA, iterableA, iterableA2, list == null ? new defpackage.s() : C2753uC.b(list, new kw5()));
    }

    public boolean k() {
        return this.h != null;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x009d  */
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            C3427m3.a(sb, "@", (K3) it.next());
        }
        C3427m3.a(sb, (String) null, this.b);
        C3427m3.a(sb, (String) null, this.c.toString().replace(" ", " !"));
        switch (AbstractC3456s3.a[h().ordinal()]) {
            case 1:
                sb.append("*");
                if (k()) {
                    sb.append(this.h.toString());
                }
                return sb.toString();
            case 2:
                sb.append("<fields>");
                if (k()) {
                    sb.append(this.h.toString());
                }
                return sb.toString();
            case XmlPullParser.END_TAG /* 3 */:
                sb.append(i());
                sb.append(' ');
                sb.append(e());
                if (k()) {
                    sb.append(this.h.toString());
                }
                return sb.toString();
            case 4:
                sb.append("<methods>");
                if (k()) {
                    sb.append(this.h.toString());
                }
                return sb.toString();
            case 8:
                sb.append(i());
                sb.append(' ');
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
                sb.append(e());
                sb.append('(');
                sb.append(Wf0.a(",", (Iterable) d()));
                sb.append(')');
                if (k()) {
                    sb.append(this.h.toString());
                }
                return sb.toString();
            default:
                x0g.a("Unknown kind of member rule");
                return null;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.shaking.t3$a */
    public static class a {
        public static final /* synthetic */ boolean i = true;
        public List a = Collections.EMPTY_LIST;
        public C3470v2 b = new C3470v2();
        public final C3470v2 c = new C3470v2();
        public EnumC3476w3 d;
        public K3 e;
        public A3 f;
        public List g;
        public C3471v3 h;

        public C3461t3 a() {
            if (i || this.d != null) {
                return new C3461t3(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            x1f.a();
            return null;
        }

        public a a(EnumC3476w3 enumC3476w3) {
            this.d = enumC3476w3;
            return this;
        }
    }

    public final boolean a(com.android.tools.r8.graph.F0 f0, C0333y c0333y, Consumer consumer, D d) {
        AbstractC3148ys abstractC3148ysA = c0333y.A();
        C0245l1 reference = f0.getReference();
        abstractC3148ysA.getClass();
        C0245l1 c0245l1A = abstractC3148ysA.a(AbstractC3148ys.g(), reference);
        int i = AbstractC3456s3.a[h().ordinal()];
        if (i == 1 || i == 2) {
            if (b().a(f0.getAccessFlags()) && f().b(f0.getAccessFlags())) {
                return h4.a(this.a, f0, consumer);
            }
            return false;
        }
        if (i != 3) {
            return false;
        }
        if (e().a(d.a(c0245l1A.g)) && b().a(f0.getAccessFlags()) && f().b(f0.getAccessFlags()) && i().a(c0333y, c0245l1A.i)) {
            return h4.a(this.a, f0, consumer);
        }
        return false;
    }

    public static a a() {
        return new a();
    }

    public final C3461t3 a(final com.android.tools.r8.graph.B1 b1) {
        return new C3461t3(K3.a(c(), b1), b(), f(), h(), i() == null ? null : i().a(b1), e() == null ? null : e().b(), d() != null ? (List) d().stream().map(new Function() { // from class: eci
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((K3) obj).a(b1);
            }
        }).collect(Collectors.toList()) : null, g());
    }
}
