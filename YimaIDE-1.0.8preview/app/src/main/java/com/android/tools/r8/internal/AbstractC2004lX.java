package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.utils.structural.A;
import defpackage.ljh;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2004lX implements com.android.tools.r8.utils.structural.x<AbstractC2004lX> {
    public static final /* synthetic */ boolean g = true;
    public final int b;
    public final C0322w2 c;
    public final AbstractC2004lX d;
    public final boolean e;
    public final boolean f;

    public AbstractC2004lX(int i, C0322w2 c0322w2, AbstractC2004lX abstractC2004lX, boolean z, boolean z2) {
        if (!g && abstractC2004lX != null && z2) {
            x01.a("Synthetic positions should always be outermost");
            throw null;
        }
        this.b = i;
        this.c = c0322w2;
        this.d = abstractC2004lX;
        this.e = z;
        this.f = z2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a2) {
        a2.a(new ToIntFunction() { // from class: ojh
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((AbstractC2004lX) obj).d();
            }
        }).a(new ToIntFunction() { // from class: pjh
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((AbstractC2004lX) obj).f();
            }
        }).j(new Function() { // from class: qjh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AbstractC2004lX) obj).g();
            }
        }).j(new Function() { // from class: rjh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AbstractC2004lX) obj).c();
            }
        }).b((Predicate) new ljh()).b(new Predicate() { // from class: sjh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC2004lX) obj).m();
            }
        });
    }

    public static AbstractC2004lX r() {
        return b.i;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public abstract a b();

    public final AbstractC2004lX c() {
        return this.d;
    }

    public abstract int d();

    public com.android.tools.r8.graph.H2 e() {
        return null;
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public int f() {
        return this.b;
    }

    public final C0322w2 g() {
        return this.c;
    }

    public final AbstractC2004lX h() {
        while (true) {
            AbstractC2004lX abstractC2004lX = this.d;
            if (abstractC2004lX == null) {
                return this;
            }
            this = abstractC2004lX;
        }
    }

    public final int hashCode() {
        boolean z = com.android.tools.r8.utils.structural.l.c;
        return com.android.tools.r8.utils.structural.l.a(this, o());
    }

    public C0322w2 i() {
        return null;
    }

    public C0867Tz j() {
        return null;
    }

    public final boolean k() {
        return this.d != null;
    }

    public boolean l() {
        return false;
    }

    public final boolean m() {
        return this.f;
    }

    public boolean n() {
        return this.b == -1;
    }

    public boolean p() {
        return this instanceof C1918kX;
    }

    public final boolean q() {
        return this.e;
    }

    public final String toString() {
        if (n()) {
            return "--";
        }
        StringBuilder sb = new StringBuilder();
        if (l()) {
            sb.append(e());
            sb.append(":");
        }
        sb.append("#");
        sb.append(this.b);
        if (this.c != null && this.d != null) {
            sb.append(":");
            sb.append(this.c.g);
        }
        AbstractC2004lX abstractC2004lX = this.d;
        if (abstractC2004lX != null) {
            while (abstractC2004lX != null) {
                sb.append(";");
                sb.append(abstractC2004lX.b);
                sb.append(":");
                sb.append(abstractC2004lX.c.g);
                abstractC2004lX = abstractC2004lX.d;
            }
        }
        if (p()) {
            sb.append(", isOutline = true");
        }
        if (i() != null) {
            sb.append(", outlineCallee = ");
            sb.append(i());
        }
        if (j() != null) {
            sb.append(", outlineCallerPositions = ");
            sb.append(j());
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.lX$a */
    public static abstract class a<P extends AbstractC2004lX, B extends a<P, B>> {
        public int a = -1;
        public C0322w2 b;
        public AbstractC2004lX c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;

        public B a(int i) {
            this.a = i;
            return (B) c();
        }

        public abstract AbstractC2004lX a();

        public B b() {
            this.g = true;
            return (B) c();
        }

        public abstract a c();

        public B a(C0322w2 c0322w2) {
            this.b = c0322w2;
            return (B) c();
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.lX$c */
    public static class c extends AbstractC2004lX {
        public static final c h = new c(-1, null, null, false, false);

        /* JADX INFO: renamed from: com.android.tools.r8.internal.lX$c$a */
        public static class a extends a<c, a> {
            public static final /* synthetic */ boolean h = true;

            @Override // com.android.tools.r8.internal.AbstractC2004lX.a
            public final a c() {
                return this;
            }

            @Override // com.android.tools.r8.internal.AbstractC2004lX.a
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public c a() {
                boolean z = h;
                if (!z && !this.f && this.a < 0) {
                    x1f.a();
                    return null;
                }
                if (z || this.g || this.b != null) {
                    return new c(this.a, this.b, this.c, this.d, this.e);
                }
                x1f.a();
                return null;
            }
        }

        public c(int i, C0322w2 c0322w2, AbstractC2004lX abstractC2004lX, boolean z, boolean z2) {
            super(i, c0322w2, abstractC2004lX, z, z2);
        }

        public static a s() {
            return new a();
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final a b() {
            a aVarA = s().a(this.b).a(this.c);
            aVarA.c = this.d;
            a aVar = (a) aVarA.c();
            aVar.d = this.e;
            a aVar2 = (a) aVar.c();
            aVar2.e = this.f;
            return aVar2.c();
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final int d() {
            return 2;
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.y o() {
            return new com.android.tools.r8.utils.structural.y() { // from class: wjh
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a2) {
                    AbstractC2004lX.a(a2);
                }
            };
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.lX$b */
    public static class b extends AbstractC2004lX {
        public static final b i = new b(-1, null, null, false, false, null);
        public static final /* synthetic */ boolean j = true;
        public final com.android.tools.r8.graph.H2 h;

        /* JADX INFO: renamed from: com.android.tools.r8.internal.lX$b$a */
        public static class a extends a<b, a> {
            public static final /* synthetic */ boolean i = true;
            public com.android.tools.r8.graph.H2 h;

            @Override // com.android.tools.r8.internal.AbstractC2004lX.a
            public final a c() {
                return this;
            }

            @Override // com.android.tools.r8.internal.AbstractC2004lX.a
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public b a() {
                boolean z = i;
                if (!z && !this.f && this.a < 0) {
                    x1f.a();
                    return null;
                }
                if (z || this.g || this.b != null) {
                    return new b(this.a, this.b, this.c, this.d, this.e, this.h);
                }
                x1f.a();
                return null;
            }
        }

        public b(int i2, C0322w2 c0322w2, AbstractC2004lX abstractC2004lX, boolean z, boolean z2, com.android.tools.r8.graph.H2 h2) {
            super(i2, c0322w2, abstractC2004lX, z, z2);
            this.h = h2;
            if (j || abstractC2004lX == null || abstractC2004lX.c != null) {
                return;
            }
            x1f.a();
            throw null;
        }

        public static void c(com.android.tools.r8.utils.structural.A a2) {
            com.android.tools.r8.utils.structural.y yVar = new com.android.tools.r8.utils.structural.y() { // from class: tjh
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a3) {
                    AbstractC2004lX.a(a3);
                }
            };
            a2.getClass();
            yVar.a(a2);
            a2.a().j(new Function() { // from class: ujh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((AbstractC2004lX) obj).e();
                }
            });
        }

        public static a s() {
            return new a();
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final a b() {
            a aVarA = s().a(this.b);
            aVarA.h = this.h;
            a aVarA2 = aVarA.a(this.c);
            aVarA2.c = this.d;
            a aVar = (a) aVarA2.c();
            aVar.d = this.e;
            a aVar2 = (a) aVar.c();
            aVar2.e = this.f;
            return aVar2.c();
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final int d() {
            return 1;
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final com.android.tools.r8.graph.H2 e() {
            return this.h;
        }

        @Override // com.android.tools.r8.internal.AbstractC2004lX
        public final boolean l() {
            return this.h != null;
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.y o() {
            return new com.android.tools.r8.utils.structural.y() { // from class: vjh
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a2) {
                    AbstractC2004lX.b.c(a2);
                }
            };
        }
    }

    public C1749iX a() {
        return null;
    }

    public final AbstractC2004lX a(Predicate predicate, boolean z) {
        AbstractC2004lX abstractC2004lXA;
        if (k() && (abstractC2004lXA = this.d.a(predicate, true)) != null) {
            return abstractC2004lXA;
        }
        if (z && predicate.test(this)) {
            return this;
        }
        return null;
    }

    public final AbstractC2004lX a(AbstractC2004lX abstractC2004lX) {
        a aVarB = b();
        if (k()) {
            abstractC2004lX = this.d.a(abstractC2004lX);
        }
        aVarB.c = abstractC2004lX;
        return aVarB.c().a();
    }

    public final AbstractC2004lX a(AbstractC2004lX abstractC2004lX, AbstractC2004lX abstractC2004lX2) {
        if (this == abstractC2004lX) {
            return abstractC2004lX2;
        }
        if (!k()) {
            return this;
        }
        a aVarB = b();
        aVarB.c = this.d.a(abstractC2004lX, abstractC2004lX2);
        return aVarB.c().a();
    }
}
