package com.android.tools.r8.internal;

import com.android.tools.r8.errors.UnsupportedDesugaredLibraryConfigurationVersionDiagnostic;
import com.android.tools.r8.internal.EJ;
import com.android.tools.r8.internal.Kh0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EJ {
    public static final Ta0 g = Ta0.a(1, 0, 9);
    public static final /* synthetic */ boolean h = true;
    public final com.android.tools.r8.graph.B1 a;
    public final C2742u50 b;
    public final boolean c;
    public final int d;
    public Origin e;
    public C1898kD f;

    public EJ(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, boolean z, int i) {
        this.a = b1;
        this.b = c2742u50;
        this.d = i;
        this.c = z;
    }

    public static void a(C1898kD c1898kD, JJ.a aVar) {
        if (c1898kD.b.containsKey("rewrite_prefix")) {
            BK bk = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("rewrite_prefix")).d().b.entrySet()).b;
            AK ak = bk.g.e;
            int i = bk.f;
            while (true) {
                AK ak2 = bk.g;
                if (ak == ak2) {
                    break;
                }
                if (ak == ak2) {
                    z0e.a();
                    return;
                } else if (bk.f != i) {
                    a1e.a();
                    return;
                } else {
                    AK ak3 = ak.e;
                    aVar.c((String) ak.g, ((AbstractC1643hD) ak.i).g());
                    ak = ak3;
                }
            }
        }
        if (c1898kD.b.containsKey("retarget_lib_member")) {
            BK bk2 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("retarget_lib_member")).d().b.entrySet()).b;
            AK ak4 = bk2.g.e;
            int i2 = bk2.f;
            while (true) {
                AK ak5 = bk2.g;
                if (ak4 == ak5) {
                    break;
                }
                if (ak4 == ak5) {
                    z0e.a();
                    return;
                } else if (bk2.f != i2) {
                    a1e.a();
                    return;
                } else {
                    AK ak6 = ak4.e;
                    aVar.b((String) ak4.g, ((AbstractC1643hD) ak4.i).g());
                    ak4 = ak6;
                }
            }
        }
        if (c1898kD.b.containsKey("backport")) {
            BK bk3 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("backport")).d().b.entrySet()).b;
            AK ak7 = bk3.g.e;
            int i3 = bk3.f;
            while (true) {
                AK ak8 = bk3.g;
                if (ak7 == ak8) {
                    break;
                }
                if (ak7 == ak8) {
                    z0e.a();
                    return;
                } else if (bk3.f != i3) {
                    a1e.a();
                    return;
                } else {
                    AK ak9 = ak7.e;
                    aVar.a((String) ak7.g, ((AbstractC1643hD) ak7.i).g());
                    ak7 = ak9;
                }
            }
        }
        if (c1898kD.b.containsKey("emulate_interface")) {
            BK bk4 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("emulate_interface")).d().b.entrySet()).b;
            AK ak10 = bk4.g.e;
            int i4 = bk4.f;
            while (true) {
                AK ak11 = bk4.g;
                if (ak10 == ak11) {
                    break;
                }
                if (ak10 == ak11) {
                    z0e.a();
                    return;
                }
                if (bk4.f != i4) {
                    a1e.a();
                    return;
                }
                AK ak12 = ak10.e;
                String str = (String) ak10.g;
                String strG = ((AbstractC1643hD) ak10.i).g();
                aVar.a(aVar.e, aVar.a.e(C0929Wj.I(str)), aVar.a.e(C0929Wj.I(strG)), "emulate_interface");
                ak10 = ak12;
            }
        }
        if (c1898kD.b.containsKey("custom_conversion")) {
            BK bk5 = ((C2932wK) ((AbstractC1643hD) c1898kD.b.get("custom_conversion")).d().b.entrySet()).b;
            AK ak13 = bk5.g.e;
            int i5 = bk5.f;
            while (true) {
                AK ak14 = bk5.g;
                if (ak13 == ak14) {
                    break;
                }
                if (ak13 == ak14) {
                    z0e.a();
                    return;
                }
                if (bk5.f != i5) {
                    a1e.a();
                    return;
                }
                AK ak15 = ak13.e;
                String str2 = (String) ak13.g;
                String strG2 = ((AbstractC1643hD) ak13.i).g();
                aVar.a(aVar.h, aVar.a.e(C0929Wj.I(str2)), aVar.a.e(C0929Wj.I(strG2)), "custom_conversion");
                ak13 = ak15;
            }
        }
        if (c1898kD.b.containsKey("wrapper_conversion")) {
            Iterator it = ((AbstractC1643hD) c1898kD.b.get("wrapper_conversion")).c().b.iterator();
            while (it.hasNext()) {
                aVar.k.add(aVar.a.e(C0929Wj.I(((AbstractC1643hD) it.next()).g())));
            }
        }
        if (c1898kD.b.containsKey("dont_rewrite")) {
            Iterator it2 = ((AbstractC1643hD) c1898kD.b.get("dont_rewrite")).c().b.iterator();
            while (it2.hasNext()) {
                String strG3 = ((AbstractC1643hD) it2.next()).g();
                aVar.getClass();
                int iD = JJ.a.d(strG3, "don't rewrite");
                aVar.i.add(new C1405eW(aVar.a.e(C0929Wj.I(strG3.substring(0, iD))), aVar.a.c(strG3.substring(iD + 1))));
            }
        }
        if (c1898kD.b.containsKey("dont_retarget_lib_member")) {
            Iterator it3 = ((AbstractC1643hD) c1898kD.b.get("dont_retarget_lib_member")).c().b.iterator();
            while (it3.hasNext()) {
                aVar.j.add(aVar.a.e(C0929Wj.I(((AbstractC1643hD) it3.next()).g())));
            }
        }
    }

    public final String b(com.android.tools.r8.t0 t0Var) {
        Origin origin = t0Var.getOrigin();
        this.e = origin;
        if (!h && origin == null) {
            x1f.a();
            return null;
        }
        try {
            String strA = t0Var.a();
            this.f = C2070mD.a(strA).d();
            return strA;
        } catch (Exception e) {
            C2742u50 c2742u50 = this.b;
            c2742u50.a(null, new ExceptionDiagnostic(e, this.e));
            throw c2742u50.c;
        }
    }

    public final AbstractC1643hD a(C1898kD c1898kD, String str) {
        if (c1898kD.b(str)) {
            return c1898kD.a(str);
        }
        this.b.a(new StringDiagnostic("Invalid desugared library configuration. Expected required key '" + str + "'", this.e));
        throw null;
    }

    public DJ a(com.android.tools.r8.t0 t0Var) {
        return a(this.e, b(t0Var), this.f, new Consumer() { // from class: y34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                EJ.a((Kh0) obj);
            }
        });
    }

    public final DJ a(Origin origin, String str, C1898kD c1898kD, Consumer consumer) {
        if (C0878Uk.a(c1898kD, this.b, origin)) {
            this.b.a("Attempt to parse a desugared library human specification as a legacy specification.");
        }
        this.e = origin;
        this.f = c1898kD;
        MJ mjA = a(consumer, str);
        JJ.a aVarA = JJ.a(this.a, this.b, this.e);
        AbstractC1643hD abstractC1643hDA = a(this.f, "common_flags");
        AbstractC1643hD abstractC1643hDA2 = a(this.f, "library_flags");
        AbstractC1643hD abstractC1643hDA3 = a(this.f, "program_flags");
        Iterator it = abstractC1643hDA.c().b.iterator();
        while (it.hasNext()) {
            C1898kD c1898kDD = ((AbstractC1643hD) it.next()).d();
            if (this.d <= a(c1898kDD, "api_level_below_or_equal").b()) {
                a(c1898kDD, aVarA);
            }
        }
        Iterator it2 = (this.c ? abstractC1643hDA2.c() : abstractC1643hDA3.c()).b.iterator();
        while (it2.hasNext()) {
            C1898kD c1898kDD2 = ((AbstractC1643hD) it2.next()).d();
            if (this.d <= a(c1898kDD2, "api_level_below_or_equal").b()) {
                a(c1898kDD2, aVarA);
            }
        }
        DJ dj = new DJ(mjA, aVarA.a(), this.c);
        this.e = null;
        return dj;
    }

    public final MJ a(Consumer consumer, String str) {
        LJ ljA = MJ.a();
        ljA.b(str);
        if (a(this.f, "configuration_format_version").b() <= 5) {
            String strG = a(this.f, "version").g();
            Ta0 ta0A = Ta0.a(strG);
            Ta0 ta0 = g;
            if (ta0A.b(ta0)) {
                ljA.a(String.join(":", a(this.f, "group_id").g(), a(this.f, "artifact_id").g(), strG));
                ljA.c(a(this.f, "synthesized_library_classes_package_prefix").g());
                ljA.a(EnumC3077y2.b(a(this.f, "required_compilation_api_level").b()));
                if (this.f.b("shrinker_config")) {
                    C1558gD c1558gDC = this.f.a("shrinker_config").c();
                    ArrayList arrayList = new ArrayList(c1558gDC.size());
                    Iterator it = c1558gDC.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((AbstractC1643hD) it.next()).g());
                    }
                    ljA.a(arrayList);
                }
                if (this.f.b("support_all_callbacks_from_library")) {
                    ljA.b(this.f.a("support_all_callbacks_from_library").a());
                }
                consumer.accept(ljA);
                return ljA.a();
            }
            this.b.a(new StringDiagnostic("Unsupported desugared library version: " + strG + ", please upgrade the desugared library to at least version " + ta0 + ".", this.e));
            throw null;
        }
        this.b.a(new UnsupportedDesugaredLibraryConfigurationVersionDiagnostic(this.e));
        throw null;
    }

    public static /* synthetic */ void a(Kh0 kh0) {
    }
}
