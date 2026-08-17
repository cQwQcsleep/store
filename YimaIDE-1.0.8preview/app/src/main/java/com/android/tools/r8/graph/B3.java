package com.android.tools.r8.graph;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.graph.B3;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.origin.Origin;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class B3 {
    public static final P40 a;
    public static final P40 b;
    public static final P40 c;
    public static final P40 d;
    public static final c e;

    public interface d<T extends AbstractC0175b1> {
        default boolean a() {
            return !b();
        }

        boolean b();
    }

    public static abstract class e extends i implements d<C0210g1> {
        public final k a;

        public e(k kVar) {
            this.a = kVar;
        }

        public static e p() {
            return B3.e;
        }

        public abstract e a(k kVar);

        public final String a(AbstractC3345r0 abstractC3345r0, Predicate predicate) {
            if (super.a()) {
                return null;
            }
            P3 p3 = new P3(abstractC3345r0, predicate);
            p3.a((i) this);
            return p3.toString();
        }

        @Override // com.android.tools.r8.graph.B3.d
        public boolean b() {
            return this != B3.e;
        }

        @Override // com.android.tools.r8.graph.B3.i
        public final e d() {
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.i
        public final boolean e() {
            return true;
        }

        public a g() {
            return null;
        }

        public c h() {
            return null;
        }

        public j i() {
            return null;
        }

        public k j() {
            return this.a;
        }

        public final boolean k() {
            return this.a != k.b;
        }

        public boolean l() {
            return false;
        }

        public boolean m() {
            return false;
        }

        public boolean n() {
            return false;
        }

        public boolean o() {
            return false;
        }

        public String toString() {
            return a(AbstractC3345r0.a(), MX.b);
        }
    }

    public static class h {
        public static final h b = new h(null);
        final i a;

        public h(i iVar) {
            this.a = iVar;
        }

        public boolean a() {
            return this.a == null;
        }

        public i b() {
            return this.a;
        }
    }

    public static abstract class i {
        public C3 c() {
            return null;
        }

        public e d() {
            return null;
        }

        public boolean e() {
            return false;
        }

        public a f() {
            return null;
        }
    }

    public static class j extends e {
        public static final /* synthetic */ boolean c = true;
        final String b;

        public j(String str, k kVar) {
            super(kVar);
            if (c || str != null) {
                this.b = str;
            } else {
                x1f.a();
                throw null;
            }
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final e a(k kVar) {
            if (c || kVar != k.b) {
                return new j(this.b, kVar);
            }
            x1f.a();
            return null;
        }

        @Override // com.android.tools.r8.graph.B3.i
        public final a f() {
            return new a(this, k.b);
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final j i() {
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final boolean o() {
            return true;
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class k {
        public static final k b = new k(0, "NOT_AN_ARGUMENT");
        public static final k c = new k(1, "NONE");
        public static final k d = new k(2, "NEGATIVE");
        public static final k e = new k(3, "POSITIVE");

        public k(int i, String str) {
            super(str, i);
        }
    }

    static {
        int i2 = AbstractC0551Hu.c;
        P40 p40 = P40.e;
        a = p40;
        b = p40;
        c = p40;
        d = p40;
        e = new c(B1.e6, p40, null, k.b);
    }

    public static b a(String str, String str2, Origin origin, B1 b1, DiagnosticsHandler diagnosticsHandler) {
        if (str2 == null || str2.isEmpty()) {
            return b.f();
        }
        try {
            return new E3(b1).a(str2);
        } catch (GenericSignatureFormatError e2) {
            diagnosticsHandler.warning(N3.a(str2, "class", str, origin, e2));
            return b.f();
        }
    }

    public static e b(String str, String str2, Origin origin, B1 b1, DiagnosticsHandler diagnosticsHandler) {
        if (str2 == null || str2.isEmpty()) {
            return e;
        }
        try {
            return new E3(b1).b(str2);
        } catch (GenericSignatureFormatError e2) {
            diagnosticsHandler.warning(N3.a(str2, "field", str, origin, e2));
            return e;
        }
    }

    public static g c(String str, String str2, Origin origin, B1 b1, DiagnosticsHandler diagnosticsHandler) {
        if (str2 == null || str2.isEmpty()) {
            return g.e;
        }
        try {
            return new E3(b1).c(str2);
        } catch (GenericSignatureFormatError e2) {
            diagnosticsHandler.warning(N3.a(str2, "method", str, origin, e2));
            return g.e;
        }
    }

    public static class a extends e {
        public static final /* synthetic */ boolean c = true;
        final i b;

        public a(i iVar, k kVar) {
            super(kVar);
            if (c || iVar != null) {
                this.b = iVar;
            } else {
                x1f.a();
                throw null;
            }
        }

        public final a a(V3 v3) {
            i iVarA = v3.a(this.b);
            if (iVarA == null) {
                return null;
            }
            return this.b == iVarA ? this : new a(iVarA, j());
        }

        @Override // com.android.tools.r8.graph.B3.i
        public final a f() {
            return new a(this, k.b);
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final a g() {
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final boolean l() {
            return true;
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final e a(k kVar) {
            if (c || kVar != k.b) {
                return new a(this.b, kVar);
            }
            x1f.a();
            return null;
        }
    }

    public static class f {
        public static final /* synthetic */ boolean d = true;
        final String a;
        final e b;
        final List<e> c;

        public f(String str, e eVar, List list) {
            this.a = str;
            this.b = eVar;
            this.c = list;
            boolean z = d;
            if (!z && eVar == null) {
                x1f.a();
                throw null;
            }
            if (z || list != null) {
                return;
            }
            x1f.a();
            throw null;
        }

        public final f a(V3 v3) {
            e eVarA = v3.a(this.b);
            List<e> listA = v3.a(this.c);
            if (this.b == eVarA && this.c == listA) {
                return this;
            }
            String str = this.a;
            if (eVarA == null) {
                eVarA = e.p();
            }
            return new f(str, eVarA, listA);
        }

        public final String a() {
            return this.a;
        }
    }

    public static class g implements d<C0231j1> {
        public static final g e;
        public static final /* synthetic */ boolean f = true;
        final List<f> a;
        final List<i> b;
        final h c;
        public final List d;

        static {
            P40 p40 = B3.a;
            P40 p41 = B3.d;
            e = new g(p40, p41, h.b, p41);
        }

        public g(List list, List list2, h hVar, List list3) {
            boolean z = f;
            if (!z && list == null) {
                x1f.a();
                throw null;
            }
            if (!z && list2 == null) {
                x1f.a();
                throw null;
            }
            if (!z && hVar == null) {
                x1f.a();
                throw null;
            }
            if (!z && list3 == null) {
                x1f.a();
                throw null;
            }
            this.a = list;
            this.b = list2;
            this.c = hVar;
            this.d = list3;
        }

        public static g d() {
            return e;
        }

        public final g a(V3 v3) {
            if (!super.a()) {
                List<f> listC = v3.c(this.a);
                List<i> listD = v3.d(this.b);
                h hVarA = v3.a(this.c);
                List listB = v3.b(this.d);
                if (this.a != listC || this.b != listD || this.c != hVarA || this.d != listB) {
                    return new g(listC, listD, hVarA, listB);
                }
            }
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.d
        public boolean b() {
            return this != e;
        }

        public final List c() {
            return this.a;
        }

        public h e() {
            return this.c;
        }

        public String toString() {
            return a(AbstractC3345r0.a(), MX.b);
        }

        public i a(int i) {
            if (this.b.isEmpty() || i < 0 || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public final String a(AbstractC3345r0 abstractC3345r0, Predicate predicate) {
            if (super.a()) {
                return null;
            }
            P3 p3 = new P3(abstractC3345r0, predicate);
            p3.a(this);
            return p3.toString();
        }
    }

    public static class b implements d<E0> {
        public static final b d = new b(B3.a, null, B3.c);
        public static final /* synthetic */ boolean e = true;
        public final List a;
        public final c b;
        public final List c;

        public b(List list, c cVar, List list2) {
            boolean z = e;
            if (!z && list == null) {
                x1f.a();
                throw null;
            }
            if (!z && list2 == null) {
                x1f.a();
                throw null;
            }
            this.a = list;
            this.b = cVar;
            this.c = list2;
        }

        public static b f() {
            return d;
        }

        public final List a(B1 b1, I2 i2) {
            if (!e && !b()) {
                x1f.a();
                return null;
            }
            c cVar = this.b;
            if (cVar == null) {
                cVar = new c(b1.a2);
            }
            if (cVar.b == i2) {
                return cVar.c;
            }
            for (c cVar2 : this.c) {
                if (cVar2.b == i2) {
                    return cVar2.c;
                }
            }
            return null;
        }

        @Override // com.android.tools.r8.graph.B3.d
        public boolean b() {
            return this != f();
        }

        public List<f> c() {
            return this.a;
        }

        public c d() {
            return this.b;
        }

        public List<c> e() {
            return this.c;
        }

        public String toString() {
            return a(AbstractC3345r0.a(), MX.b);
        }

        public final String a(AbstractC3345r0 abstractC3345r0, Predicate predicate) {
            if (super.a()) {
                return null;
            }
            P3 p3 = new P3(abstractC3345r0, predicate);
            p3.a(this);
            return p3.toString();
        }

        public final b a(V3 v3, B1 b1) {
            if (!super.a()) {
                List listC = v3.c(this.a);
                c cVarA = v3.a(this.b);
                List listE = v3.e(this.c);
                if (this.a != listC || this.b != cVarA || this.c != listE) {
                    D3 d3 = new D3();
                    d3.a.addAll(listC);
                    d3.b = cVarA;
                    d3.c.addAll(listE);
                    return d3.a(b1);
                }
            }
            return this;
        }
    }

    public static class c extends e {
        public static final /* synthetic */ boolean e = true;
        final I2 b;
        final List<e> c;
        final c d;

        public c(I2 i2, List list, c cVar, k kVar) {
            super(kVar);
            boolean z = e;
            if (!z && i2 == null) {
                x1f.a();
                throw null;
            }
            if (!z && list == null) {
                x1f.a();
                throw null;
            }
            this.b = i2;
            this.c = list;
            this.d = cVar;
            if (!z && i2 == B1.e6 && kVar != k.b) {
                x1f.a();
                throw null;
            }
            if (!z && !list.stream().allMatch(new Predicate() { // from class: hl0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((B3.e) obj).k();
                }
            })) {
                x1f.a();
                throw null;
            }
            if (z || list.stream().allMatch(new Predicate() { // from class: il0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((B3.e) obj).b();
                }
            })) {
                return;
            }
            x1f.a();
            throw null;
        }

        public final c a(V3 v3) {
            if (!a()) {
                I2 i2A = v3.a(this.b);
                if (i2A == null) {
                    return null;
                }
                List<e> listA = v3.a(this.b, i2A, this.c);
                c cVar = this.d;
                c cVarA = cVar != null ? v3.a(cVar, this) : null;
                if (this.b != i2A || this.c != listA || this.d != cVarA) {
                    return new c(i2A, listA, cVarA, j());
                }
            }
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final c a(k kVar) {
            boolean z = e;
            if (!z && kVar == k.b) {
                x1f.a();
                return null;
            }
            if (z || b()) {
                return new c(this.b, this.c, this.d, kVar);
            }
            x1f.a();
            return null;
        }

        @Override // com.android.tools.r8.graph.B3.i
        public final a f() {
            return new a(this, k.b);
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final c h() {
            return this;
        }

        @Override // com.android.tools.r8.graph.B3.e
        public final boolean m() {
            return true;
        }

        public final I2 q() {
            return this.b;
        }

        public List<e> r() {
            return this.c;
        }

        public c(I2 i2) {
            this(i2, B3.b, null, k.b);
        }
    }
}
