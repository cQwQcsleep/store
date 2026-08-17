package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0226i3;
import com.android.tools.r8.graph.C0233j3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.SJ;
import defpackage.m7h;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.i3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0226i3 implements InterfaceC0219h3 {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public C0226i3() {
        this.a = new IdentityHashMap();
    }

    public static C0233j3 a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys, Ch0 ch0, C0245l1 c0245l1, C0233j3 c0233j3) {
        c0233j3.getClass();
        ch0.a("Rewrite FieldAccessInfoImpl");
        AbstractC0187d abstractC0187dA = c0233j3.c.a(interfaceC0189d1, abstractC3148ys);
        AbstractC0187d abstractC0187dA2 = c0233j3.d.a(interfaceC0189d1, abstractC3148ys);
        if (!abstractC3148ys.b(AbstractC3148ys.g())) {
            c0233j3 = new C0233j3(abstractC3148ys.e((AbstractC3148ys) null, c0233j3.a), c0233j3.b, abstractC0187dA, abstractC0187dA2);
        } else if (abstractC0187dA != c0233j3.c || abstractC0187dA2 != c0233j3.d) {
            c0233j3 = new C0233j3(c0233j3.a, c0233j3.b, abstractC0187dA, abstractC0187dA2);
        }
        ch0.b();
        return c0233j3;
    }

    public static C0233j3 c(C0245l1 c0245l1, C0233j3 c0233j3, C0233j3 c0233j4) {
        C0245l1 c0245l2 = c0233j3.a;
        C0173b c0173b = C0173b.a;
        C0233j3 c0233j5 = new C0233j3(c0245l2, 0, c0173b, c0173b);
        c0233j5.b = c0233j3.b | c0233j4.b;
        c0233j5.c = c0233j3.c.a(c0233j4.c);
        c0233j5.d = c0233j3.d.a(c0233j4.d);
        return c0233j5;
    }

    public final void b() {
        this.a.values().forEach(new Consumer() { // from class: k7h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C0233j3) obj).c();
            }
        });
    }

    public C0226i3(Map map) {
        this.a = map;
    }

    public static /* synthetic */ C0233j3 b(C0245l1 c0245l1, C0233j3 c0233j3, C0233j3 c0233j4) {
        return c0233j4;
    }

    public final C0233j3 b(C0245l1 c0245l1) {
        return (C0233j3) this.a.get(c0245l1);
    }

    public final C0233j3 a(C0245l1 c0245l1) {
        return (C0233j3) this.a.get(c0245l1);
    }

    public final C0233j3 a(C0245l1 c0245l1, C0233j3 c0233j3) {
        if (b || !this.a.containsKey(c0245l1)) {
            this.a.put(c0245l1, c0233j3);
            return c0233j3;
        }
        x1f.a();
        return null;
    }

    public final void a(Consumer consumer) {
        boolean z = b;
        if (z || z || this.a.values().size() == C1755ib0.a(this.a.values()).size()) {
            this.a.values().forEach(consumer);
        } else {
            x1f.a();
        }
    }

    public final void a(final BiPredicate biPredicate) {
        this.a.entrySet().removeIf(new Predicate() { // from class: l7h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                return biPredicate.test((C0245l1) entry.getKey(), (C0233j3) entry.getValue());
            }
        });
    }

    public static /* synthetic */ boolean a(InterfaceC0189d1 interfaceC0189d1, C0245l1 c0245l1, C0233j3 c0233j3) {
        return !interfaceC0189d1.a((AbstractC0287r2) c0245l1).a0();
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1) {
        a(new BiPredicate() { // from class: r7h
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return C0226i3.a(interfaceC0189d1, (C0245l1) obj, (C0233j3) obj2);
            }
        });
    }

    public static C0245l1 a(C0245l1 c0245l1, C0233j3 c0233j3, C0233j3 c0233j4) {
        return c0233j4.a;
    }

    public final C0226i3 a(final C0313v0 c0313v0, final AbstractC3148ys abstractC3148ys, final Ch0 ch0) {
        ch0.a("Rewrite FieldAccessInfoCollectionImpl");
        Map mapA = SJ.a(this.a, new m7h(), new BiFunction() { // from class: n7h
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C0226i3.a(c0313v0, abstractC3148ys, ch0, (C0245l1) obj, (C0233j3) obj2);
            }
        }, new InterfaceC1938ki0() { // from class: o7h
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C0226i3.a((C0245l1) obj, (C0233j3) obj2, (C0233j3) obj3);
            }
        }, new InterfaceC1938ki0() { // from class: p7h
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C0226i3.b((C0245l1) obj, (C0233j3) obj2, (C0233j3) obj3);
            }
        }, new InterfaceC1938ki0() { // from class: q7h
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C0226i3.c((C0245l1) obj, (C0233j3) obj2, (C0233j3) obj3);
            }
        });
        if (mapA != this.a) {
            this = new C0226i3(mapA);
        }
        ch0.b();
        return this;
    }

    public final void a() {
        this.a.values().forEach(new Consumer() { // from class: s7h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C0233j3) obj).b();
            }
        });
    }
}
