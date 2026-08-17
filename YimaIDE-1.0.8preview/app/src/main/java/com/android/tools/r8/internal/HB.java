package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3159z1;
import com.android.tools.r8.internal.H;
import defpackage.v36;
import java.util.IdentityHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class HB {
    public static final /* synthetic */ boolean i = true;
    public final C0333y a;
    public final AbstractC3159z1 b;
    public final InterfaceC1004Zg c;
    public final A1 d;
    public final IdentityHashMap e = new IdentityHashMap();
    public final IdentityHashMap f = new IdentityHashMap();
    public final IdentityHashMap g = new IdentityHashMap();
    public final IB h;

    public HB(C0333y c0333y, AbstractC3159z1 abstractC3159z1, InterfaceC1004Zg interfaceC1004Zg, A1 a1, IB ib) {
        this.a = c0333y;
        this.b = abstractC3159z1;
        this.c = interfaceC1004Zg;
        this.d = a1;
        this.h = ib;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final AbstractC0486Fh a(Sm0 sm0, Ch0 ch0) {
        while (sm0.b()) {
            final Object objD = sm0.d();
            sm0.b.remove(objD);
            AbstractC3159z1 abstractC3159z1 = (AbstractC3159z1) ch0.a("Compute block entry state", new InterfaceC2706th0() { // from class: t36
                @Override // com.android.tools.r8.internal.InterfaceC2706th0
                public final Object get() {
                    return this.a.d(objD);
                }
            });
            Vh0 vh0C = this.d.c(objD, abstractC3159z1);
            if (vh0C.b()) {
                return this.d.a((Object) null, (Vh0) abstractC3159z1);
            }
            AbstractC3159z1 abstractC3159z1A = vh0C.a();
            ch0.a("Compute transfers");
            Object obj = null;
            do {
                final boolean zD = this.c.d(objD);
                AbstractC1597gi0 abstractC1597gi0A = this.c.a(objD, new BiFunction() { // from class: u36
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj2, Object obj3) {
                        return this.b.a(zD, objD, (H) obj2, (AbstractC3159z1) obj3);
                    }
                }, abstractC3159z1A);
                if (abstractC1597gi0A.c()) {
                    ch0.b();
                    return (AbstractC0486Fh) abstractC1597gi0A.a().e();
                }
                abstractC3159z1A = (AbstractC3159z1) abstractC1597gi0A.b().e();
                if (this.c.b(objD) && c(this.c.a(objD))) {
                    objD = this.c.a(objD);
                } else {
                    obj = objD;
                    objD = null;
                }
            } while (objD != null);
            ch0.b();
            boolean z = i;
            if (!z && this.c.b(obj) && c(this.c.a(obj))) {
                x1f.a();
                return null;
            }
            AbstractC3159z1 abstractC3159z2 = (AbstractC3159z1) this.f.put(obj, abstractC3159z1A);
            if (!z && abstractC3159z2 != null && !abstractC3159z1A.a(this.a, abstractC3159z2)) {
                x1f.a();
                return null;
            }
            if (!abstractC3159z1A.equals(abstractC3159z2)) {
                this.c.c(new v36(sm0), obj);
            }
            a(obj, abstractC3159z1A);
        }
        return new C0460Eh(this.f);
    }

    public final AbstractC3159z1 b(final Object obj) {
        return ((AbstractC3159z1) this.c.a(obj, this.b, new BiFunction() { // from class: q36
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj2, Object obj3) {
                return this.b.a(obj, obj2, (AbstractC3159z1) obj3);
            }
        }).b().e()).mo16clone();
    }

    public final boolean c(Object obj) {
        if (!this.h.a) {
            return false;
        }
        InterfaceC1004Zg interfaceC1004Zg = this.c;
        return interfaceC1004Zg.c(obj) && interfaceC1004Zg.b(interfaceC1004Zg.e(obj)) && obj != this.c.a() && !this.c.f(obj);
    }

    public boolean e(final Object obj) {
        return AbstractC1683hi0.a(new Consumer() { // from class: s36
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.a(obj, (Function) obj2);
            }
        });
    }

    public final AbstractC1597gi0 a(boolean z, Object obj, H h, AbstractC3159z1 abstractC3159z1) {
        if (h.g() && z) {
            this.d.getClass();
            a(obj, h, abstractC3159z1);
        }
        Vh0 vh0A = this.d.a((Object) h, abstractC3159z1);
        if (vh0A.b()) {
            return new C1341di0(this.d.a(h, vh0A));
        }
        if (i || vh0A.c()) {
            return new C1512fi0(vh0A.a());
        }
        x1f.a();
        return null;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final AbstractC3159z1 d(Object obj) {
        AbstractC3159z1 abstractC3159z1B;
        if (obj == this.c.a()) {
            AbstractC3159z1 abstractC3159z1B2 = this.d.b(obj, this.b);
            C0333y c0333y = this.a;
            if (e(obj)) {
                abstractC3159z1B = ((AbstractC3159z1) this.e.getOrDefault(obj, this.b)).mo16clone();
            } else {
                abstractC3159z1B = b(obj);
            }
            return abstractC3159z1B2.b(c0333y, abstractC3159z1B);
        }
        if (this.c.f(obj)) {
            return ((AbstractC3159z1) this.g.getOrDefault(obj, this.b)).mo16clone();
        }
        if (e(obj)) {
            return ((AbstractC3159z1) this.e.getOrDefault(obj, this.b)).mo16clone();
        }
        return b(obj);
    }

    public final AbstractC1597gi0 a(Object obj, Object obj2, AbstractC3159z1 abstractC3159z1) {
        return new C1512fi0(abstractC3159z1.b(this.a, this.d.a(obj, obj2, ((AbstractC3159z1) this.f.getOrDefault(obj2, this.b)).mo16clone())));
    }

    public final void a(final Object obj, final AbstractC3159z1 abstractC3159z1) {
        this.c.b(new Consumer() { // from class: p36
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.a(obj, abstractC3159z1, obj2);
            }
        }, obj);
    }

    public final void a(Object obj, AbstractC3159z1 abstractC3159z1, Object obj2) {
        if (e(obj2)) {
            AbstractC3159z1 abstractC3159z1A = this.d.a(obj2, obj, abstractC3159z1);
            IdentityHashMap identityHashMap = this.e;
            identityHashMap.put(obj2, ((AbstractC3159z1) identityHashMap.getOrDefault(obj2, this.b)).b(this.a, abstractC3159z1A));
        }
    }

    public final void a(final Object obj, final H h, final AbstractC3159z1 abstractC3159z1) {
        this.c.a(obj, new BiConsumer() { // from class: r36
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj2, Object obj3) {
                this.a.a(obj, h, abstractC3159z1, obj2, (I2) obj3);
            }
        });
    }

    public final void a(Object obj, H h, AbstractC3159z1 abstractC3159z1, Object obj2, com.android.tools.r8.graph.I2 i2) {
        AbstractC3159z1 abstractC3159z1A = this.d.a(obj2, i2, obj, h, abstractC3159z1);
        IdentityHashMap identityHashMap = this.g;
        identityHashMap.put(obj2, ((AbstractC3159z1) identityHashMap.getOrDefault(obj2, this.b)).b(this.a, abstractC3159z1A));
    }

    public final /* synthetic */ void a(Object obj, Function function) {
        this.c.i(obj, function);
    }
}
