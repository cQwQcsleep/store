package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2689tY;
import com.android.tools.r8.internal.C2274of;
import com.android.tools.r8.internal.InterfaceC2603sY;
import com.android.tools.r8.internal.Wd0;
import com.android.tools.r8.internal.Y3;
import defpackage.b83;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.of, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2274of extends AbstractC2775uY {
    public static final /* synthetic */ boolean d = true;
    public final List a;
    public final C1975l7 b;
    public boolean c;

    public C2274of(AbstractC1543g4 abstractC1543g4, Vd0 vd0) {
        this.c = false;
        this.a = new ArrayList();
        if (abstractC1543g4.b()) {
            Iterator it = abstractC1543g4.a().c.iterator();
            while (it.hasNext()) {
                this.a.add(new Y3((X3) it.next()));
            }
            if (!d && this.a.isEmpty()) {
                x1f.a();
                throw null;
            }
        }
        this.b = new C1975l7(vd0.a() ? null : new Wd0(vd0));
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final void a(C0333y c0333y) {
        boolean z = d;
        if (!z && this.c) {
            x1f.a();
            return;
        }
        if (e()) {
            if (!z && !e()) {
                x1f.a();
                return;
            }
            ArrayList arrayList = new ArrayList(this.a.size());
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                arrayList.add((X3) ((Y3) it.next()).b());
            }
            c0333y.d = new MR(arrayList);
        }
        if (f()) {
            if (!d && !f()) {
                x1f.a();
                return;
            }
            c0333y.q = (Vd0) ((Wd0) this.b.a()).b();
        }
        this.c = true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C2274of a(final Function function) {
        ArrayList arrayList = new ArrayList(this.a.size());
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            arrayList.add((Y3) ((Y3) it.next()).a(function));
        }
        C1975l7 c1975l7 = this.b;
        return new C2274of(c1975l7.b() ? new C1975l7(new InterfaceC2022lh0() { // from class: myh
            @Override // com.android.tools.r8.internal.InterfaceC2022lh0
            public final Object apply(Object obj) {
                return C2274of.a(function, (Wd0) obj);
            }
        }.apply(c1975l7.a())) : new C1975l7(), arrayList);
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final void d() {
        if (d || this.c) {
            return;
        }
        x1f.a();
    }

    public final boolean e() {
        return AbstractC3179zC.b(this.a, new EX() { // from class: jyh
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((Y3) obj).e();
            }
        });
    }

    public final boolean f() {
        return this.b.a(new Predicate() { // from class: oyh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Wd0) obj).e();
            }
        });
    }

    public final void b(final com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6) {
        a(b6.getReference(), new Consumer() { // from class: hyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2274of.a(b5, (InterfaceC2603sY) obj);
            }
        });
    }

    public static void b(com.android.tools.r8.graph.B5 b5, InterfaceC2603sY interfaceC2603sY) {
        interfaceC2603sY.getClass();
        interfaceC2603sY.a((com.android.tools.r8.graph.F2) b5.getReference());
    }

    public C2274of(C1975l7 c1975l7, ArrayList arrayList) {
        this.c = false;
        this.a = arrayList;
        this.b = c1975l7;
    }

    public final void a(Consumer consumer) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            consumer.accept((Y3) it.next());
        }
        this.b.a(consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final void a(final com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6) {
        a(b6.getReference(), new Consumer() { // from class: kyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2274of.b(b5, (InterfaceC2603sY) obj);
            }
        });
    }

    public final void a(final com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.H0 h0) {
        h0.getClass();
        if (h0 instanceof com.android.tools.r8.graph.B5) {
            a(b5, h0.c0());
        } else {
            a(new Consumer() { // from class: gyh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C2274of.a(b5, (AbstractC2689tY) obj);
                }
            });
        }
    }

    public static void a(com.android.tools.r8.graph.B5 b5, AbstractC2689tY abstractC2689tY) {
        b83 b83Var = new b83();
        abstractC2689tY.getClass();
        abstractC2689tY.a(b5.getReference(), b83Var);
    }

    public static void a(com.android.tools.r8.graph.B5 b5, InterfaceC2603sY interfaceC2603sY) {
        interfaceC2603sY.getClass();
        interfaceC2603sY.a((com.android.tools.r8.graph.F2) b5.getReference()).a(b5.a().getReference());
    }

    public final void a(final Consumer consumer, final com.android.tools.r8.graph.D2 d2) {
        a(new Consumer() { // from class: lyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC2689tY abstractC2689tY = (AbstractC2689tY) obj;
                abstractC2689tY.a(d2.getType(), consumer);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final void a(final C0322w2 c0322w2, final Consumer consumer) {
        a(new Consumer() { // from class: nyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC2689tY) obj).b(c0322w2, consumer);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final C2274of a() {
        return this;
    }

    public static /* synthetic */ Wd0 a(Function function, Wd0 wd0) {
        return (Wd0) wd0.a(function);
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final AbstractC2775uY a(AbstractC1543g4 abstractC1543g4) {
        if (abstractC1543g4.b()) {
            Iterator it = abstractC1543g4.a().c.iterator();
            Iterator it2 = this.a.iterator();
            while (it2.hasNext()) {
                ((Y3) it2.next()).a = (X3) it.next();
            }
        } else {
            boolean z = d;
            if (!z && !this.a.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.b.b()) {
                x1f.a();
                return null;
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2775uY
    public final AbstractC2775uY a(final Vd0 vd0) {
        this.b.a(new Consumer() { // from class: iyh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2274of.a(vd0, (Wd0) obj);
            }
        });
        return this;
    }

    public static void a(Vd0 vd0, Wd0 wd0) {
        wd0.a = vd0;
    }
}
