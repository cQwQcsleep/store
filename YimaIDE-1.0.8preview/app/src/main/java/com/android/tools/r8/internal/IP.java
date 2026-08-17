package com.android.tools.r8.internal;

import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.graph.AbstractC0208g;
import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.IP;
import com.android.tools.r8.internal.InterfaceC1247cd0;
import com.android.tools.r8.internal.O2;
import defpackage.f63;
import defpackage.hkh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IP {
    public final com.android.tools.r8.graph.B1 a;
    public final TreeMap b = new TreeMap();
    public int c = 0;

    public IP(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }

    public final ArrayList a(HashMap map) {
        ArrayList arrayList = new ArrayList();
        ArrayList<O2> arrayList2 = new ArrayList(map.keySet());
        arrayList2.sort(new Comparator() { // from class: tg6
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return IP.a((O2) obj, (O2) obj2);
            }
        });
        for (O2 o2 : arrayList2) {
            C3020xM c3020xM = (C3020xM) map.get(o2);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("api_level_below_or_equal", Integer.valueOf(o2.a.d()));
            EnumC3077y2 enumC3077y2 = o2.b;
            if (enumC3077y2 != null) {
                linkedHashMap.put("api_level_greater_or_equal", Integer.valueOf(enumC3077y2.d()));
            }
            a("rewrite_type", c3020xM.n(), linkedHashMap);
            Set<com.android.tools.r8.graph.I2> setK = c3020xM.k();
            if (!setK.isEmpty()) {
                linkedHashMap.put("maintain_type", a(setK));
            }
            a("rewrite_derived_type_only", c3020xM.m(), linkedHashMap);
            a("static_field_retarget", c3020xM.o(), linkedHashMap);
            a("covariant_retarget", c3020xM.d(), linkedHashMap);
            a("static_retarget", c3020xM.p(), linkedHashMap);
            a("non_emulated_virtual_retarget", c3020xM.l(), linkedHashMap);
            Map<C0322w2, C1606gn> mapH = c3020xM.h();
            if (!mapH.isEmpty()) {
                linkedHashMap.put("emulated_virtual_retarget", b(mapH));
            }
            a("emulated_virtual_retarget_through_emulated_interface", c3020xM.i(), linkedHashMap);
            a(linkedHashMap, c3020xM.c());
            Map<com.android.tools.r8.graph.I2, C1777in> mapG = c3020xM.g();
            if (!mapG.isEmpty()) {
                linkedHashMap.put("emulated_interface", b(mapG));
            }
            LinkedHashMap<com.android.tools.r8.graph.I2, Tm0> linkedHashMapQ = c3020xM.q();
            if (!linkedHashMapQ.isEmpty()) {
                linkedHashMap.put("wrapper", a((LinkedHashMap) linkedHashMapQ));
            }
            a("legacy_backport", c3020xM.j(), linkedHashMap);
            Set<com.android.tools.r8.graph.I2> setF = c3020xM.f();
            if (!setF.isEmpty()) {
                linkedHashMap.put("dont_retarget", a(setF));
            }
            Map<com.android.tools.r8.graph.I2, C1850jh> mapE = c3020xM.e();
            if (!mapE.isEmpty()) {
                linkedHashMap.put("custom_conversion", b(mapE));
            }
            b("amend_library_method", c3020xM.b(), linkedHashMap);
            b("amend_library_field", c3020xM.a(), linkedHashMap);
            arrayList.add(linkedHashMap);
        }
        return arrayList;
    }

    public final void b(String str, Map map, LinkedHashMap linkedHashMap) {
        if (map.isEmpty()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        map.forEach(new BiConsumer() { // from class: zg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(arrayList, (AbstractC0259n1) obj, (AbstractC0208g) obj2);
            }
        });
        linkedHashMap.put(str, arrayList);
    }

    public final TreeMap b(Map map) {
        final TreeMap treeMap = new TreeMap();
        map.forEach(new BiConsumer() { // from class: ug6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(treeMap, (AbstractC0259n1) obj, (InterfaceC1247cd0) obj2);
            }
        });
        return treeMap;
    }

    public static int a(O2 o2, O2 o3) {
        int iCompareTo = o2.a.compareTo(o3.a);
        if (iCompareTo == 0) {
            EnumC3077y2 enumC3077y2 = o2.b;
            EnumC3077y2 enumC3077y3 = o3.b;
            if (enumC3077y2 == null) {
                iCompareTo = enumC3077y3 == null ? 0 : 1;
            } else {
                iCompareTo = enumC3077y3 == null ? -1 : enumC3077y2.compareTo(enumC3077y3);
            }
        }
        return -iCompareTo;
    }

    public static void a(GP gp, StringConsumer stringConsumer, com.android.tools.r8.graph.B1 b1) {
        IP ip = new IP(b1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        C3189zM c3189zM = gp.a;
        linkedHashMap.put("identifier", c3189zM.c());
        linkedHashMap.put("required_compilation_api_level", Integer.valueOf(c3189zM.d().d()));
        linkedHashMap.put("synthesized_library_classes_package_prefix", c3189zM.e());
        linkedHashMap.put("support_all_callbacks_from_library", Boolean.valueOf(c3189zM.e));
        linkedHashMap.put("shrinker_config", c3189zM.b());
        linkedHashMap.put("configuration_format_version", 200);
        linkedHashMap.put("common_flags", ip.a((HashMap) gp.b));
        linkedHashMap.put("program_flags", ip.a((HashMap) gp.d));
        linkedHashMap.put("library_flags", ip.a((HashMap) gp.c));
        linkedHashMap.put("package_map", ip.b);
        stringConsumer.accept(new C0471Es().a(linkedHashMap), new HP());
    }

    public final Object[] a(C2964wi c2964wi) {
        String strA = a(c2964wi.a);
        int i = c2964wi.b;
        return new Object[]{strA, Integer.toString(i == 0 ? -1 : AbstractC3104yM.a(i))};
    }

    public final void a(String str, Map map, LinkedHashMap linkedHashMap) {
        if (map.isEmpty()) {
            return;
        }
        linkedHashMap.put(str, a(map));
    }

    public final void a(LinkedHashMap linkedHashMap, Map map) {
        if (map.isEmpty()) {
            return;
        }
        final TreeMap treeMap = new TreeMap();
        map.forEach(new BiConsumer() { // from class: vg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(treeMap, (AbstractC0259n1) obj, (C0322w2[]) obj2);
            }
        });
        linkedHashMap.put("api_generic_types_conversion", treeMap);
    }

    public final void a(TreeMap treeMap, AbstractC0259n1 abstractC0259n1, C0322w2[] c0322w2Arr) {
        String strA = a(abstractC0259n1);
        String[] strArr = new String[c0322w2Arr.length];
        for (int i = 0; i < c0322w2Arr.length; i++) {
            C0322w2 c0322w2 = c0322w2Arr[i];
            strArr[i] = c0322w2 == null ? XmlPullParser.NO_NAMESPACE : a(c0322w2);
        }
        treeMap.put(strA, strArr);
    }

    public final /* synthetic */ void a(List list, AbstractC0259n1 abstractC0259n1, AbstractC0208g abstractC0208g) {
        list.add(abstractC0208g.toString() + " " + a(abstractC0259n1));
    }

    public final LinkedHashMap a(LinkedHashMap linkedHashMap) {
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap.forEach(new BiConsumer() { // from class: wg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(linkedHashMap2, (AbstractC0259n1) obj, (InterfaceC1247cd0) obj2);
            }
        });
        return linkedHashMap2;
    }

    public final /* synthetic */ void a(LinkedHashMap linkedHashMap, AbstractC0259n1 abstractC0259n1, InterfaceC1247cd0 interfaceC1247cd0) {
        linkedHashMap.put(a(abstractC0259n1), interfaceC1247cd0.a(this));
    }

    public final TreeMap a(Map map) {
        final TreeMap treeMap = new TreeMap();
        map.forEach(new BiConsumer() { // from class: xg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(treeMap, (AbstractC0259n1) obj, (AbstractC0259n1) obj2);
            }
        });
        return treeMap;
    }

    public final /* synthetic */ void a(TreeMap treeMap, AbstractC0259n1 abstractC0259n1, AbstractC0259n1 abstractC0259n2) {
        treeMap.put(a(abstractC0259n1), a(abstractC0259n2));
    }

    public final /* synthetic */ void a(TreeMap treeMap, AbstractC0259n1 abstractC0259n1, InterfaceC1247cd0 interfaceC1247cd0) {
        treeMap.put(a(abstractC0259n1), interfaceC1247cd0.a(this));
    }

    public final ArrayList a(Collection collection) {
        final ArrayList arrayList = new ArrayList();
        collection.forEach(new Consumer() { // from class: sg6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, (AbstractC0259n1) obj);
            }
        });
        arrayList.sort(Comparator.naturalOrder());
        return arrayList;
    }

    public final /* synthetic */ void a(List list, AbstractC0259n1 abstractC0259n1) {
        list.add(a(abstractC0259n1));
    }

    public final String a(AbstractC0259n1 abstractC0259n1) {
        if (abstractC0259n1 instanceof com.android.tools.r8.graph.I2) {
            return a((com.android.tools.r8.graph.I2) abstractC0259n1);
        }
        if (abstractC0259n1 instanceof C0245l1) {
            C0245l1 c0245l1 = (C0245l1) abstractC0259n1;
            return a(c0245l1.getType()) + " " + a(c0245l1.w0()) + "#" + c0245l1.x0();
        }
        if (abstractC0259n1 instanceof C0322w2) {
            C0322w2 c0322w2 = (C0322w2) abstractC0259n1;
            StringBuilder sb = new StringBuilder();
            sb.append(a(c0322w2.D0()));
            sb.append(" ");
            sb.append(a(c0322w2.w0()));
            sb.append("#");
            sb.append(c0322w2.x0());
            sb.append("(");
            for (int i = 0; i < c0322w2.B0().size(); i++) {
                sb.append(a(c0322w2.k(i)));
                if (i != c0322w2.B0().size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(")");
            return sb.toString();
        }
        hkh.a();
        return null;
    }

    public final String a(com.android.tools.r8.graph.I2 i2) {
        if (!i2.T0() && !i2.S0() && !i2.W0()) {
            if (i2.I0()) {
                StringBuilder sb = new StringBuilder();
                sb.append(a(i2.a(this.a)));
                for (int i = 0; i < i2.C0(); i++) {
                    sb.append("[]");
                }
                return sb.toString();
            }
            return ((String) this.b.computeIfAbsent(i2.E0(), new Function() { // from class: yg6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a((String) obj);
                }
            })) + i2.G0();
        }
        return i2.toString();
    }

    public final /* synthetic */ String a(String str) {
        return a();
    }

    public final String a() {
        int i = this.c;
        if (i < 66) {
            this.c = i + 1;
            return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789æÆøØ".charAt(i) + "$";
        }
        f63.a("MultiAPILevelMachineDesugaredLibrarySpecificationJsonExporter cannot encode the next package because the encoding ran out of characters. Extend the chars sequence or improve the encoding to fix this.");
        return null;
    }
}
