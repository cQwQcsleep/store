package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.EnumC0238k1;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.XV;
import com.android.tools.r8.ir.optimize.C3278x;
import com.android.tools.r8.shaking.C3400h1;
import com.android.tools.r8.synthesis.N;
import com.android.tools.r8.synthesis.S;
import defpackage.l73;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XV extends KV {
    public static final /* synthetic */ boolean g = true;
    public FV a;
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final C0333y d;
    public final com.android.tools.r8.graph.B1 e;
    public final com.android.tools.r8.ir.optimize.W f;

    public XV(C0333y c0333y) {
        this.d = c0333y;
        this.e = c0333y.a();
        this.f = new com.android.tools.r8.ir.optimize.W(c0333y);
    }

    public static void c(com.android.tools.r8.graph.B5 b5) {
        C0231j1 c0231j1E = b5.e();
        c0231j1E.O0();
        c0231j1E.l = EnumC0238k1.b;
    }

    public final ArrayList a(IV iv) {
        final C0509Ge c0509GeM = this.d.m();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        ArrayList arrayList = new ArrayList();
        if (!g && this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        ArrayList<OV> arrayList2 = new ArrayList();
        for (Map.Entry entry : this.b.entrySet()) {
            if (((List) entry.getValue()).size() >= this.d.M().Z.d) {
                arrayList2.add((OV) entry.getKey());
            }
        }
        arrayList2.sort(Comparator.naturalOrder());
        for (final OV ov : arrayList2) {
            List list = (List) this.b.get(ov);
            if (!g && list.isEmpty()) {
                x1f.a();
                return null;
            }
            final com.android.tools.r8.graph.B5 b5 = (com.android.tools.r8.graph.B5) list.get(0);
            for (int i = 1; i < list.size(); i++) {
                com.android.tools.r8.graph.B5 b6 = (com.android.tools.r8.graph.B5) list.get(i);
                if (b6.getReference().compareTo(b5.getReference()) < 0) {
                    b5 = b6;
                }
            }
            com.android.tools.r8.graph.B5 b5B = this.d.a.g().b(new com.android.tools.r8.synthesis.I() { // from class: tyf
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.J;
                }
            }, ((C0483Fe) identityHashMap.computeIfAbsent(b5.getReference(), new Function() { // from class: syf
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return c0509GeM.a(b5);
                }
            })).a(), this.d, new Consumer() { // from class: uyf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(ov, b5, (N) obj);
                }
            });
            if (!g && b5B.getReference().A0() > 255) {
                x1f.a();
                return null;
            }
            iv.a(list, b5B);
            this.c.put(ov, b5B.getReference());
            arrayList.add(b5B);
        }
        return arrayList;
    }

    public final void b(final C0705Nt c0705Nt) {
        final com.android.tools.r8.graph.B5 b5I = c0705Nt.i();
        boolean z = g;
        if (!z) {
            AbstractC0223i0 abstractC0223i0U0 = b5I.e().U0();
            abstractC0223i0U0.getClass();
            if (abstractC0223i0U0 instanceof PV) {
                x1f.a();
                return;
            }
        }
        if (z || !C2098md.a(b5I.a(), this.d)) {
            a(this.d, c0705Nt, new Consumer() { // from class: oyf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(b5I, c0705Nt, (List) obj);
                }
            });
        } else {
            x1f.a();
        }
    }

    public final void b(C0835St c0835St, C0705Nt c0705Nt) {
        a(c0705Nt);
        C0835St.a(c0705Nt, "IR after outlining (SSA)", (String) null, c0835St.j);
        c0835St.l.a(c0705Nt);
        new C2937wP(this.d).a(c0705Nt, Ch0.a());
        c0835St.a(c0705Nt, com.android.tools.r8.ir.optimize.info.B.b, Ch0.a());
    }

    @Override // com.android.tools.r8.internal.KV
    public final void b(com.android.tools.r8.graph.B5 b5) {
        a(b5);
    }

    @Override // com.android.tools.r8.internal.KV
    public final void a(AbstractC3148ys abstractC3148ys) {
        boolean z = g;
        if (!z && this.d.A() != abstractC3148ys) {
            x1f.a();
        } else if (z || this.a == null) {
            this.a = new FV(abstractC3148ys);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.KV
    public final void a(final C0835St c0835St, com.android.tools.r8.ir.optimize.info.z zVar, ExecutorService executorService, Ch0 ch0) throws ExecutionException {
        if (!g) {
            zVar.b();
        }
        c0835St.b("Outlining");
        ch0.a("IR conversion phase 3");
        FV fv = this.a;
        C0333y c0333y = this.d;
        fv.getClass();
        UY uyC = UY.c();
        for (List list : fv.a(c0333y).values()) {
            if (list.size() >= c0333y.M().Z.d) {
                uyC.addAll(list);
            }
        }
        this.a = null;
        if (!uyC.b.isEmpty()) {
            C0333y c0333y2 = this.d;
            IV ey = HV.a;
            AbstractC2775uY abstractC2775uYB = AbstractC2775uY.b(c0333y2);
            abstractC2775uYB.getClass();
            if (!(abstractC2775uYB instanceof C1233cS)) {
                ey = new EY(abstractC2775uYB.a());
            }
            a(c0835St, uyC, new Consumer() { // from class: wyf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0835St, (C0705Nt) obj);
                }
            }, executorService);
            ArrayList arrayListA = a(ey);
            a(arrayListA);
            c0835St.a(arrayListA, JO.a, AbstractC2166nO.c(this.d), executorService);
            zVar.c();
            a(c0835St, uyC, new Consumer() { // from class: lyf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b(c0835St, (C0705Nt) obj);
                }
            }, executorService);
            zVar.c();
            if (!g) {
                for (OV ov : this.c.keySet()) {
                    if (!g && !((List) this.b.get(ov)).isEmpty()) {
                        x01.a((List) this.b.get(ov));
                        return;
                    }
                }
            }
            arrayListA.forEach(new Consumer() { // from class: myf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    XV.c((B5) obj);
                }
            });
            ey.a(this.d);
        }
        this.d.getClass();
        ch0.b();
    }

    public final void a(C0835St c0835St, C0705Nt c0705Nt) {
        C0835St.a(c0705Nt, "IR before outlining (SSA)", (String) null, c0835St.j);
        b(c0705Nt);
    }

    public final void a(final ArrayList arrayList) {
        if (this.d.Q().M) {
            return;
        }
        this.d.t().a(new Consumer() { // from class: kyf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XV.a(arrayList, (C3400h1) obj);
            }
        });
    }

    public static /* synthetic */ void a(List list, C3400h1 c3400h1) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.B5 b5 = (com.android.tools.r8.graph.B5) it.next();
            c3400h1.a(b5);
            c3400h1.a(new l73(), b5);
        }
    }

    public final void a(final C0835St c0835St, UY uy, final Consumer consumer, ExecutorService executorService) throws ExecutionException {
        if (g || !this.d.M().Y0) {
            C1086ah0.a(uy, new Consumer() { // from class: vyf
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0835St, consumer, (B5) obj);
                }
            }, this.d.M().N(), executorService);
        } else {
            x1f.a();
        }
    }

    public final void a(C0835St c0835St, Consumer consumer, com.android.tools.r8.graph.B5 b5) {
        C0705Nt c0705NtA = b5.a(this.d);
        boolean z = g;
        if (!z && c0705NtA == null) {
            x1f.a();
            return;
        }
        if (!z) {
            AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
            abstractC0223i0U0.getClass();
            if (abstractC0223i0U0 instanceof PV) {
                x1f.a();
                return;
            }
        }
        new C2937wP(this.d).a(c0705NtA, Ch0.a());
        c0835St.y.a(c0705NtA, Ch0.a());
        C3278x.a((C0333y<?>) this.d, c0705NtA);
        consumer.accept(c0705NtA);
    }

    @Override // com.android.tools.r8.internal.KV
    public final void a() {
        this.a.a(this.d.A());
    }

    @Override // com.android.tools.r8.internal.KV
    public final void a(final C0705Nt c0705Nt, Ch0 ch0) {
        if (this.a == null) {
            return;
        }
        final com.android.tools.r8.graph.B5 b5I = c0705Nt.i();
        if (!g) {
            AbstractC0223i0 abstractC0223i0U0 = b5I.e().U0();
            abstractC0223i0U0.getClass();
            if (abstractC0223i0U0 instanceof PV) {
                x1f.a();
                return;
            }
        }
        if (C2098md.a(b5I.a(), this.d)) {
            return;
        }
        ch0.a("Collect outlines");
        final ArrayList arrayList = new ArrayList();
        a(this.d, c0705Nt, new Consumer() { // from class: ryf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(b5I, c0705Nt, arrayList, (List) obj);
            }
        });
        this.a.a(this.d, b5I, arrayList);
        ch0.b();
    }

    public final /* synthetic */ void a(com.android.tools.r8.graph.B5 b5, C0705Nt c0705Nt, List list, List list2) {
        new SV(this, b5, list2, list).a();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006d  */
    public static void a(C0333y c0333y, C0705Nt c0705Nt, Consumer consumer) {
        c0333y.M().Z.getClass();
        int i = c0333y.M().Z.b;
        Set setC = AbstractC2780ub0.c();
        for (H5 h5 : c0705Nt.d) {
            if (setC.add(h5)) {
                C0473Eu c0473EuG = AbstractC0551Hu.g();
                C1650hK c1650hK = new C1650hK(c0705Nt, h5);
                boolean z = false;
                H5 h5I = h5;
                int i2 = 0;
                while (c1650hK.d.hasNext()) {
                    AbstractC0890Uw next = c1650hK.next();
                    if (next.i() != h5) {
                        if (!h5.x() && !next.i().x()) {
                            if (setC.contains(next.i())) {
                                break;
                            }
                            c0473EuG.a(next);
                            i2++;
                            if (i2 <= 100) {
                            }
                            h5I = next.i();
                        } else {
                            h5I = next.i();
                            z = true;
                            break;
                        }
                    } else {
                        c0473EuG.a(next);
                        i2++;
                        if (i2 <= 100 && next.i() != h5I) {
                            break;
                        } else {
                            h5I = next.i();
                        }
                    }
                }
                setC.addAll(c1650hK.e);
                if (z) {
                    if (!g && h5I == h5) {
                        x1f.a();
                        return;
                    }
                    setC.remove(h5I);
                }
                if (i2 >= i) {
                    consumer.accept(c0473EuG.a());
                }
            }
        }
    }

    public final /* synthetic */ void a(com.android.tools.r8.graph.B5 b5, C0705Nt c0705Nt, List list) {
        new UV(this, b5, list).a();
    }

    @Override // com.android.tools.r8.internal.KV
    public final void a(com.android.tools.r8.graph.B5 b5) {
        FV fv = this.a;
        C0333y c0333y = this.d;
        if (!FV.d) {
            fv.getClass();
            if (c0333y.A() != fv.b) {
                x1f.a();
                return;
            }
        }
        fv.c.remove(b5.getReference());
    }

    public final void a(final OV ov, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.synthesis.N n) {
        n.h = com.android.tools.r8.graph.F4.b(9, false);
        if (ov.f == null) {
            ov.f = ov.g.e.a(ov.e, (com.android.tools.r8.graph.I2[]) ov.b.toArray(com.android.tools.r8.graph.I2.h));
        }
        n.e = ov.f;
        int i = com.android.tools.r8.androidapi.f.a;
        com.android.tools.r8.androidapi.h hVar = com.android.tools.r8.androidapi.h.b;
        n.l = hVar;
        n.m = hVar;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: nyf
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return this.a.a(ov, c0322w2);
            }
        };
        if (this.d.M().j instanceof ClassFileConsumer) {
            n.f = b5.e().T0();
        }
    }

    public final /* synthetic */ AbstractC0223i0 a(OV ov, C0322w2 c0322w2) {
        return new PV(this, ov);
    }

    public final void a(final C0705Nt c0705Nt) {
        C0705Nt c0705Nt2;
        boolean z = g;
        if (!z) {
            AbstractC0223i0 abstractC0223i0U0 = c0705Nt.i().e().U0();
            abstractC0223i0U0.getClass();
            if (abstractC0223i0U0 instanceof PV) {
                x1f.a();
                return;
            }
        }
        final Set setC = AbstractC2780ub0.c();
        final Set setC2 = AbstractC2780ub0.c();
        a(this.d, c0705Nt, new Consumer() { // from class: pyf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0705Nt, setC, setC2, (List) obj);
            }
        });
        if (setC.isEmpty()) {
            c0705Nt2 = c0705Nt;
        } else {
            if (!z && setC2.isEmpty()) {
                x1f.a();
                return;
            }
            final L5 l5T = c0705Nt.t();
            while (l5T.hasNext()) {
                final H5 next = l5T.next();
                final K5 k5A = next.a(c0705Nt);
                k5A.forEachRemaining(new Consumer() { // from class: qyf
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        XV.a(setC, k5A, setC2, next, c0705Nt, l5T, (AbstractC0890Uw) obj);
                    }
                });
            }
            c0705Nt2 = c0705Nt;
            c0705Nt2.y();
        }
        c0705Nt2.y();
        if (g || c0705Nt2.b(this.d)) {
            return;
        }
        x1f.a();
    }

    public final /* synthetic */ void a(C0705Nt c0705Nt, Set set, Set set2, List list) {
        new TV(this, c0705Nt, list, set, set2).a();
    }

    public static /* synthetic */ void a(Set set, InterfaceC0968Xw interfaceC0968Xw, Set set2, H5 h5, C0705Nt c0705Nt, ListIterator listIterator, AbstractC0890Uw abstractC0890Uw) {
        if (set.contains(abstractC0890Uw)) {
            interfaceC0968Xw.r();
        } else if (set2.contains(abstractC0890Uw) && h5.x()) {
            interfaceC0968Xw.a(c0705Nt, (ListIterator<H5>) listIterator);
        }
    }
}
