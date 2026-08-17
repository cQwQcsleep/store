package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.C2210nt;
import com.android.tools.r8.internal.Kh0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2210nt {
    public static final /* synthetic */ boolean i = true;
    public final com.android.tools.r8.graph.B1 a;
    public final C2382pt b;
    public final C2296ot c;
    public final C2742u50 d;
    public final boolean e;
    public final int f;
    public Origin g;
    public C1898kD h;

    public C2210nt(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, boolean z, int i2) {
        this.a = b1;
        this.b = new C2382pt(b1);
        this.c = new C2296ot(b1);
        this.d = c2742u50;
        this.f = i2;
        this.e = z;
    }

    public final void a(C1898kD c1898kD, C2552rt.a aVar) {
        if (c1898kD.b.containsKey("rewrite_prefix")) {
            BK bk = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("rewrite_prefix")).d().b.entrySet()).b;
            AK ak = bk.g.e;
            int i2 = bk.f;
            while (true) {
                AK ak2 = bk.g;
                if (ak == ak2) {
                    break;
                }
                if (ak == ak2) {
                    z0e.a();
                    return;
                } else if (bk.f != i2) {
                    a1e.a();
                    return;
                } else {
                    AK ak3 = ak.e;
                    aVar.a((String) ak.g, ((AbstractC1643hD) ak.i).g());
                    ak = ak3;
                }
            }
        }
        if (c1898kD.b.containsKey("maintain_prefix")) {
            Iterator it = ((AbstractC1643hD) c1898kD.b.get("maintain_prefix")).c().b.iterator();
            while (it.hasNext()) {
                aVar.b(((AbstractC1643hD) it.next()).g());
            }
        }
        if (c1898kD.b.containsKey("dont_rewrite_prefix")) {
            Iterator it2 = ((AbstractC1643hD) c1898kD.b.get("dont_rewrite_prefix")).c().b.iterator();
            while (it2.hasNext()) {
                aVar.d.add(((AbstractC1643hD) it2.next()).g());
            }
        }
        if (c1898kD.b.containsKey("never_outline_api")) {
            Iterator it3 = ((AbstractC1643hD) c1898kD.b.get("never_outline_api")).c().b.iterator();
            while (it3.hasNext()) {
                this.b.b(((AbstractC1643hD) it3.next()).g());
                aVar.s.add(this.b.c());
            }
        }
        if (c1898kD.b.containsKey("api_generic_types_conversion")) {
            BK bk2 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("api_generic_types_conversion")).d().b.entrySet()).b;
            AK ak4 = bk2.g.e;
            int i3 = bk2.f;
            while (true) {
                AK ak5 = bk2.g;
                if (ak4 == ak5) {
                    break;
                }
                if (ak4 == ak5) {
                    z0e.a();
                    return;
                }
                if (bk2.f != i3) {
                    a1e.a();
                    return;
                }
                AK ak6 = ak4.e;
                C1558gD c1558gDC = ((AbstractC1643hD) ak4.i).c();
                for (int i4 = 0; i4 < c1558gDC.b.size(); i4 += 2) {
                    this.b.b((String) ak4.g);
                    C0322w2 c0322w2C = this.b.c();
                    int iB = ((AbstractC1643hD) c1558gDC.b.get(i4)).b();
                    this.b.b(((AbstractC1643hD) c1558gDC.b.get(i4 + 1)).g());
                    aVar.a(c0322w2C, iB, this.b.c());
                }
                ak4 = ak6;
            }
        }
        if (c1898kD.b.containsKey("rewrite_derived_prefix")) {
            BK bk3 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("rewrite_derived_prefix")).d().b.entrySet()).b;
            AK ak7 = bk3.g.e;
            int i5 = bk3.f;
            while (true) {
                AK ak8 = bk3.g;
                if (ak7 == ak8) {
                    break;
                }
                if (ak7 == ak8) {
                    z0e.a();
                    return;
                }
                if (bk3.f != i5) {
                    a1e.a();
                    return;
                }
                AK ak9 = ak7.e;
                BK bk4 = ((C2932wK) ((AbstractC1643hD) ak7.i).d().b.entrySet()).b;
                AK ak10 = bk4.g.e;
                int i6 = bk4.f;
                while (true) {
                    AK ak11 = bk4.g;
                    if (ak10 != ak11) {
                        if (ak10 == ak11) {
                            z0e.a();
                            return;
                        } else if (bk4.f != i6) {
                            a1e.a();
                            return;
                        } else {
                            AK ak12 = ak10.e;
                            aVar.a((String) ak7.g, (String) ak10.g, ((AbstractC1643hD) ak10.i).g());
                            ak10 = ak12;
                        }
                    }
                }
                ak7 = ak9;
            }
        }
        if (c1898kD.b.containsKey("retarget_static_field")) {
            BK bk5 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("retarget_static_field")).d().b.entrySet()).b;
            AK ak13 = bk5.g.e;
            int i7 = bk5.f;
            while (true) {
                AK ak14 = bk5.g;
                if (ak13 == ak14) {
                    break;
                }
                if (ak13 == ak14) {
                    z0e.a();
                    return;
                }
                if (bk5.f != i7) {
                    a1e.a();
                    return;
                }
                AK ak15 = ak13.e;
                this.c.b((String) ak13.g);
                C0245l1 c0245l1C = this.c.c();
                this.c.b(((AbstractC1643hD) ak13.i).g());
                aVar.a(c0245l1C, this.c.c());
                ak13 = ak15;
            }
        }
        if (c1898kD.b.containsKey("retarget_method")) {
            BK bk6 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("retarget_method")).d().b.entrySet()).b;
            AK ak16 = bk6.g.e;
            int i8 = bk6.f;
            while (true) {
                AK ak17 = bk6.g;
                if (ak16 == ak17) {
                    break;
                }
                if (ak16 == ak17) {
                    z0e.a();
                    return;
                }
                if (bk6.f != i8) {
                    a1e.a();
                    return;
                }
                AK ak18 = ak16.e;
                String str = (String) ak16.g;
                String strG = ((AbstractC1643hD) ak16.i).g();
                boolean zContains = strG.contains("#");
                C2382pt c2382pt = this.b;
                if (zContains) {
                    c2382pt.b(str);
                    C0322w2 c0322w2C2 = this.b.c();
                    this.b.b(strG);
                    aVar.a(aVar.l, c0322w2C2, this.b.c(), "retarget_method");
                } else {
                    c2382pt.b(str);
                    aVar.a(aVar.j, this.b.c(), this.a.e(C0929Wj.I(strG)), "retarget_method");
                }
                ak16 = ak18;
            }
        }
        if (c1898kD.b.containsKey("retarget_method_with_emulated_dispatch")) {
            BK bk7 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("retarget_method_with_emulated_dispatch")).d().b.entrySet()).b;
            AK ak19 = bk7.g.e;
            int i9 = bk7.f;
            while (true) {
                AK ak20 = bk7.g;
                if (ak19 == ak20) {
                    break;
                }
                if (ak19 == ak20) {
                    z0e.a();
                    return;
                }
                if (bk7.f != i9) {
                    a1e.a();
                    return;
                }
                AK ak21 = ak19.e;
                String str2 = (String) ak19.g;
                String strG2 = ((AbstractC1643hD) ak19.i).g();
                boolean zContains2 = strG2.contains("#");
                C2382pt c2382pt2 = this.b;
                if (zContains2) {
                    c2382pt2.b(str2);
                    C0322w2 c0322w2C3 = this.b.c();
                    this.b.b(strG2);
                    aVar.a(aVar.m, c0322w2C3, this.b.c(), "retarget_method_with_emulated_dispatch");
                } else {
                    c2382pt2.b(str2);
                    aVar.a(aVar.k, this.b.c(), this.a.e(C0929Wj.I(strG2)), "retarget_method_with_emulated_dispatch");
                }
                ak19 = ak21;
            }
        }
        if (c1898kD.b.containsKey("covariant_retarget_method")) {
            BK bk8 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("covariant_retarget_method")).d().b.entrySet()).b;
            AK ak22 = bk8.g.e;
            int i10 = bk8.f;
            while (true) {
                AK ak23 = bk8.g;
                if (ak22 == ak23) {
                    break;
                }
                if (ak22 == ak23) {
                    z0e.a();
                    return;
                }
                if (bk8.f != i10) {
                    a1e.a();
                    return;
                }
                AK ak24 = ak22.e;
                this.b.b((String) ak22.g);
                aVar.a(aVar.i, this.b.c(), this.a.e(C0929Wj.I(((AbstractC1643hD) ak22.i).g())), "covariant_retarget_method");
                ak22 = ak24;
            }
        }
        if (c1898kD.b.containsKey("backport")) {
            BK bk9 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("backport")).d().b.entrySet()).b;
            AK ak25 = bk9.g.e;
            int i11 = bk9.f;
            while (true) {
                AK ak26 = bk9.g;
                if (ak25 == ak26) {
                    break;
                }
                if (ak25 == ak26) {
                    z0e.a();
                    return;
                }
                if (bk9.f != i11) {
                    a1e.a();
                    return;
                }
                AK ak27 = ak25.e;
                aVar.a(aVar.o, this.a.e(C0929Wj.I((String) ak25.g)), this.a.e(C0929Wj.I(((AbstractC1643hD) ak25.i).g())), "backport");
                ak25 = ak27;
            }
        }
        if (c1898kD.b.containsKey("emulate_interface")) {
            BK bk10 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("emulate_interface")).d().b.entrySet()).b;
            AK ak28 = bk10.g.e;
            int i12 = bk10.f;
            while (true) {
                AK ak29 = bk10.g;
                if (ak28 == ak29) {
                    break;
                }
                if (ak28 == ak29) {
                    z0e.a();
                    return;
                }
                if (bk10.f != i12) {
                    a1e.a();
                    return;
                }
                AK ak30 = ak28.e;
                AbstractC1643hD abstractC1643hD = (AbstractC1643hD) ak28.i;
                abstractC1643hD.getClass();
                boolean z = abstractC1643hD instanceof C2155nD;
                Object obj = ak28.g;
                if (z) {
                    aVar.a(aVar.g, this.a.e(C0929Wj.I((String) obj)), new C2723tt(this.a.e(C0929Wj.I(((AbstractC1643hD) ak28.i).g()))), "emulate_interface");
                } else {
                    com.android.tools.r8.graph.I2 i2E = this.a.e(C0929Wj.I((String) obj));
                    C1898kD c1898kDD = ((AbstractC1643hD) ak28.i).d();
                    com.android.tools.r8.graph.I2 i2E2 = this.a.e(C0929Wj.I(a(c1898kDD, "rewrittenType").g()));
                    Set setC = AbstractC2780ub0.c();
                    if (c1898kDD.b.containsKey("emulatedMethods")) {
                        Iterator it4 = ((AbstractC1643hD) c1898kDD.b.get("emulatedMethods")).c().b.iterator();
                        while (it4.hasNext()) {
                            this.b.b(((AbstractC1643hD) it4.next()).g());
                            setC.add(this.b.c());
                        }
                    }
                    aVar.a(i2E, new C2637st(i2E2, setC));
                }
                ak28 = ak30;
            }
        }
        if (c1898kD.b.containsKey("custom_conversion")) {
            BK bk11 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("custom_conversion")).d().b.entrySet()).b;
            AK ak31 = bk11.g.e;
            int i13 = bk11.f;
            while (true) {
                AK ak32 = bk11.g;
                if (ak31 == ak32) {
                    break;
                }
                if (ak31 == ak32) {
                    z0e.a();
                    return;
                }
                if (bk11.f != i13) {
                    a1e.a();
                    return;
                }
                AK ak33 = ak31.e;
                aVar.a(aVar.p, this.a.e(C0929Wj.I((String) ak31.g)), this.a.e(C0929Wj.I(((AbstractC1643hD) ak31.i).g())), "custom_conversion");
                ak31 = ak33;
            }
        }
        if (c1898kD.b.containsKey("wrapper_conversion")) {
            Iterator it5 = ((AbstractC1643hD) c1898kD.b.get("wrapper_conversion")).c().b.iterator();
            while (it5.hasNext()) {
                aVar.b(this.a.e(C0929Wj.I(((AbstractC1643hD) it5.next()).g())));
            }
        }
        if (c1898kD.b.containsKey("wrapper_conversion_excluding")) {
            BK bk12 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("wrapper_conversion_excluding")).d().b.entrySet()).b;
            AK ak34 = bk12.g.e;
            int i14 = bk12.f;
            while (true) {
                AK ak35 = bk12.g;
                if (ak34 == ak35) {
                    break;
                }
                if (ak34 == ak35) {
                    z0e.a();
                    return;
                }
                if (bk12.f != i14) {
                    a1e.a();
                    return;
                }
                AK ak36 = ak34.e;
                com.android.tools.r8.graph.I2 i2E3 = this.a.e(C0929Wj.I((String) ak34.g));
                C1558gD c1558gDC2 = ((AbstractC1643hD) ak34.i).c();
                Set setC2 = AbstractC2780ub0.c();
                Iterator it6 = c1558gDC2.b.iterator();
                while (it6.hasNext()) {
                    this.b.b(((AbstractC1643hD) it6.next()).g());
                    setC2.add(this.b.c());
                }
                aVar.r.put(i2E3, setC2);
                ak34 = ak36;
            }
        }
        if (c1898kD.b.containsKey("dont_retarget")) {
            Iterator it7 = ((AbstractC1643hD) c1898kD.b.get("dont_retarget")).c().b.iterator();
            while (it7.hasNext()) {
                aVar.q.add(this.a.e(C0929Wj.I(((AbstractC1643hD) it7.next()).g())));
            }
        }
        if (c1898kD.b.containsKey("amend_library_method")) {
            Iterator it8 = ((AbstractC1643hD) c1898kD.b.get("amend_library_method")).c().b.iterator();
            while (it8.hasNext()) {
                this.b.b(((AbstractC1643hD) it8.next()).g());
                C0322w2 c0322w2C4 = this.b.c();
                C2382pt c2382pt3 = this.b;
                if (!C2382pt.j && !c2382pt3.d()) {
                    x1f.a();
                    return;
                }
                aVar.t.put(c0322w2C4, c2382pt3.i);
            }
        }
        if (c1898kD.b.containsKey("amend_library_field")) {
            Iterator it9 = ((AbstractC1643hD) c1898kD.b.get("amend_library_field")).c().b.iterator();
            while (it9.hasNext()) {
                this.c.b(((AbstractC1643hD) it9.next()).g());
                C0245l1 c0245l1C2 = this.c.c();
                C2296ot c2296ot = this.c;
                if (!C2296ot.i && !c2296ot.d()) {
                    x1f.a();
                    return;
                }
                aVar.a(c0245l1C2, c2296ot.h);
            }
        }
    }

    public final String b(com.android.tools.r8.t0 t0Var) {
        Origin origin = t0Var.getOrigin();
        this.g = origin;
        if (!i && origin == null) {
            x1f.a();
            return null;
        }
        try {
            String strA = t0Var.a();
            this.h = C2070mD.a(strA).d();
            return strA;
        } catch (Exception e) {
            C2742u50 c2742u50 = this.d;
            c2742u50.a(null, new ExceptionDiagnostic(e, this.g));
            throw c2742u50.c;
        }
    }

    public final AbstractC1643hD a(C1898kD c1898kD, String str) {
        if (c1898kD.b(str)) {
            return c1898kD.a(str);
        }
        this.d.a(new StringDiagnostic("Invalid desugared library configuration. Expected required key '" + str + "'", this.g));
        throw null;
    }

    public C2124mt a(com.android.tools.r8.t0 t0Var) {
        return a(this.g, b(t0Var), this.h, new Consumer() { // from class: yvh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2210nt.a((Kh0) obj);
            }
        });
    }

    public final C2124mt a(Origin origin, String str, C1898kD c1898kD, Consumer consumer) {
        if (!C0878Uk.a(c1898kD, this.d, origin)) {
            this.d.a("Attempt to parse a non desugared library human specification as a human specification.");
        }
        this.g = origin;
        this.h = c1898kD;
        C0394Bt c0394BtA = a(consumer, str);
        C2552rt.a aVarA = C2552rt.a(this.d, this.g);
        AbstractC1643hD abstractC1643hDA = a(this.h, "common_flags");
        AbstractC1643hD abstractC1643hDA2 = a(this.h, "library_flags");
        AbstractC1643hD abstractC1643hDA3 = a(this.h, "program_flags");
        a(abstractC1643hDA.c(), aVarA);
        a(this.e ? abstractC1643hDA2.c() : abstractC1643hDA3.c(), aVarA);
        C2124mt c2124mt = new C2124mt(c0394BtA, aVarA.a(), this.e);
        this.g = null;
        return c2124mt;
    }

    public final C0394Bt a(Consumer consumer, String str) {
        C0368At c0368AtA = C0394Bt.a();
        c0368AtA.b(str);
        int iB = a(this.h, "configuration_format_version").b();
        if (iB != 101) {
            this.d.warning(new StringDiagnostic("Human desugared library specification format version " + iB + " mismatches the parser expected version (101). This is allowed and should happen only while extending the specifications.", this.g));
        }
        c0368AtA.a(a(this.h, "identifier").g());
        c0368AtA.c(a(this.h, "synthesized_library_classes_package_prefix").g());
        c0368AtA.a(EnumC3077y2.b(a(this.h, "required_compilation_api_level").b()));
        if (this.h.b("shrinker_config")) {
            C1558gD c1558gDC = this.h.a("shrinker_config").c();
            ArrayList arrayList = new ArrayList(c1558gDC.size());
            Iterator it = c1558gDC.iterator();
            while (it.hasNext()) {
                arrayList.add(((AbstractC1643hD) it.next()).g());
            }
            c0368AtA.a(arrayList);
        }
        if (this.h.b("support_all_callbacks_from_library")) {
            c0368AtA.b(this.h.a("support_all_callbacks_from_library").a());
        }
        consumer.accept(c0368AtA);
        return c0368AtA.a();
    }

    public final void a(C1558gD c1558gD, C2552rt.a aVar) {
        Iterator it = c1558gD.b.iterator();
        while (it.hasNext()) {
            C1898kD c1898kDD = ((AbstractC1643hD) it.next()).d();
            if (this.f <= a(c1898kDD, "api_level_below_or_equal").b()) {
                if (c1898kDD.b.containsKey("api_level_greater_or_equal")) {
                    if (this.f >= ((AbstractC1643hD) c1898kDD.b.get("api_level_greater_or_equal")).b()) {
                        a(c1898kDD, aVar);
                    }
                } else {
                    a(c1898kDD, aVar);
                }
            }
        }
    }

    public static /* synthetic */ void a(Kh0 kh0) {
    }
}
