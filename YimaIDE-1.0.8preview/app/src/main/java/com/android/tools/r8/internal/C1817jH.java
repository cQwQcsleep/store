package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1562gH;
import com.android.tools.r8.internal.C1647hH;
import com.android.tools.r8.internal.C1817jH;
import com.android.tools.r8.internal.C2159nH;
import com.android.tools.r8.internal.HE;
import com.android.tools.r8.internal.NW;
import com.android.tools.r8.internal.QE;
import com.android.tools.r8.internal.TG;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1817jH {
    public static final TG b;
    public static final /* synthetic */ boolean c = true;
    public final Consumer a;

    static {
        TG tg = TG.b;
        RG rg = new RG(false);
        rg.b.addAll(Arrays.asList(SG.b, SG.d, SG.c));
        b = rg.a();
    }

    public C1817jH(Consumer consumer) {
        this.a = consumer;
    }

    public static C1562gH b(IE ie, HE he) {
        return new C1562gH(new C1647hH(((FE) ie.a.get(he)).a.a()));
    }

    public final void a(AbstractC2243oF abstractC2243oF) {
        ArrayList arrayListA;
        Map mapSingletonMap;
        List listSingletonList;
        if (abstractC2243oF.a() != null) {
            JE jeA = abstractC2243oF.a();
            AbstractC2671tG abstractC2671tG = jeA.c;
            boolean z = jeA.b == 1;
            arrayListA = new ArrayList(z ? 2 : 1);
            IE ie = IE.b;
            GE ge = new GE();
            HE he = new HE("CLASS");
            if (abstractC2671tG.d()) {
                ge.a(he, abstractC2671tG);
                mapSingletonMap = Collections.EMPTY_MAP;
                listSingletonList = Collections.EMPTY_LIST;
            } else {
                AG agB = abstractC2671tG.b();
                if (!c && agB.a.b() == null) {
                    x1f.a();
                    return;
                }
                ge.a(he, agB.a.b());
                FG fg = agB.b;
                HE he2 = new HE("MEMBERS");
                mapSingletonMap = Collections.singletonMap(he2, fg);
                listSingletonList = Collections.singletonList(he2);
            }
            Map map = mapSingletonMap;
            List list = listSingletonList;
            C1647hH c1647hH = new C1647hH(((FE) ge.a().a.get(he)).a.a());
            arrayListA.add(new MW(jeA.a, c1647hH, TG.b, map, list, NW.f));
            if (z) {
                SG[] sgArr = {SG.b};
                RG rg = new RG(true);
                rg.b.addAll(Arrays.asList(sgArr));
                TG tgA = rg.a();
                if (abstractC2671tG.d()) {
                    HE he3 = new HE("MEMBERS");
                    arrayListA.add(new MW(jeA.a, c1647hH, tgA, Collections.singletonMap(he3, EG.d), Collections.singletonList(he3), NW.d));
                } else {
                    arrayListA.add(new KW(Collections.EMPTY_LIST, jeA.a, tgA, c1647hH, map, list, NW.c));
                }
            }
        } else {
            arrayListA = a(new C3182zF(abstractC2243oF.b()).a());
        }
        OW.a(arrayListA);
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            ((OW) it.next()).b(sb);
            sb.append("\n");
        }
        this.a.accept(sb.toString());
    }

    public static void a(IE ie, Map map, List list, HE he) {
        AbstractC2671tG abstractC2671tG = ((FE) ie.a.get(he)).a;
        if (abstractC2671tG.d()) {
            return;
        }
        FG fg = (FG) map.put(he, abstractC2671tG.b().b);
        list.add(he);
        if (c || fg == null) {
            return;
        }
        x1f.a();
    }

    public static void a(final IE ie, Map map, QE qe) {
        HE heA = a(qe.a, ie);
        if (!c && heA == null) {
            x1f.a();
            return;
        }
        C1562gH c1562gH = (C1562gH) map.computeIfAbsent(heA, new Function() { // from class: fdh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1817jH.a(ie, (HE) obj);
            }
        });
        if (!C1562gH.d) {
            c1562gH.getClass();
            if (!qe.a.f()) {
                x1f.a();
                return;
            }
        }
        c1562gH.b.add(qe.a.a().a);
    }

    public static void a(Set set, final IE ie, Map map, C2159nH c2159nH) {
        set.addAll(c2159nH.b.b());
        HE heA = a(c2159nH.a, ie);
        if (c || heA != null) {
            ((C1562gH) map.computeIfAbsent(heA, new Function() { // from class: zch
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C1817jH.b(ie, (HE) obj);
                }
            })).a(c2159nH);
        } else {
            x1f.a();
        }
    }

    public final void a(final List list, final C3097yF c3097yF, final C1647hH c1647hH, C1647hH c1647hH2, IE ie, final TG tg, HashSet hashSet, Set set) {
        if (c1647hH.a.a.e() && c1647hH.a.equals(c1647hH2.a)) {
            a(list, c1647hH2, c3097yF, ie, tg, hashSet, set);
            return;
        }
        final HashMap map = new HashMap();
        final ArrayList arrayListA = a(hashSet, ie, map);
        a(c1647hH2, set, ie, map, new InterfaceC1733iH() { // from class: adh
            @Override // com.android.tools.r8.internal.InterfaceC1733iH
            public final void a(C1647hH c1647hH3, Map map2, List list2, NW nw) {
                this.a.a(list, c3097yF, tg, c1647hH, map, arrayListA, c1647hH3, map2, list2, nw);
            }
        });
    }

    public final void a(List list, C1647hH c1647hH, C2415qF c2415qF, IE ie, C1562gH c1562gH, TG tg, Set set) {
        a(list, c1647hH, c2415qF.a, ie, tg, c1562gH.b, set);
    }

    public final void a(List list, C1647hH c1647hH, C2415qF c2415qF, IE ie, TG tg, Set set) {
        a(list, c1647hH, c2415qF.a, ie, tg, set);
    }

    public static C1562gH a(IE ie, HE he) {
        return new C1562gH(new C1647hH(((FE) ie.a.get(he)).a.a()));
    }

    public final ArrayList a(final C2415qF c2415qF) {
        final ArrayList arrayList = new ArrayList();
        final HashSet hashSet = new HashSet();
        final IE ie = c2415qF.b;
        final HashMap map = new HashMap();
        c2415qF.c.a(new Consumer() { // from class: uch
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1817jH.a(ie, map, (QE) obj);
            }
        });
        SE se = c2415qF.d;
        se.a.forEach(new Consumer() { // from class: vch
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1817jH.a(hashSet, ie, map, (C2159nH) obj);
            }
        });
        if (!hashSet.isEmpty()) {
            arrayList.add(new LW(c2415qF.a, hashSet, C1902kH.a));
        }
        map.forEach(new BiConsumer() { // from class: wch
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(arrayList, c2415qF, ie, map, (HE) obj, (C1562gH) obj2);
            }
        });
        if (c || !arrayList.isEmpty()) {
            return arrayList;
        }
        x1f.a();
        return null;
    }

    public final void a(final List list, final C2415qF c2415qF, final IE ie, final Map map, HE he, final C1562gH c1562gH) {
        final C1647hH c1647hH = c1562gH.a;
        if (!c1562gH.b.isEmpty() && !c1562gH.c.isEmpty()) {
            c1562gH.c.forEach(new BiConsumer() { // from class: cdh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(list, c1647hH, c2415qF, ie, c1562gH, (TG) obj, (Set) obj2);
                }
            });
            return;
        }
        if (c1562gH.c.isEmpty()) {
            return;
        }
        boolean zA = c2415qF.c.a();
        HashMap map2 = c1562gH.c;
        if (zA) {
            map2.forEach(new BiConsumer() { // from class: ddh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(list, c1647hH, c2415qF, ie, (TG) obj, (Set) obj2);
                }
            });
        } else {
            map2.forEach(new BiConsumer() { // from class: edh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(map, list, c2415qF, c1647hH, ie, (TG) obj, (Set) obj2);
                }
            });
        }
    }

    public final /* synthetic */ void a(Map map, final List list, final C2415qF c2415qF, final C1647hH c1647hH, final IE ie, final TG tg, final Set set) {
        map.forEach(new BiConsumer() { // from class: sch
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(list, c2415qF, c1647hH, ie, tg, set, (HE) obj, (C1562gH) obj2);
            }
        });
    }

    public final void a(List list, C2415qF c2415qF, C1647hH c1647hH, IE ie, TG tg, Set set, HE he, C1562gH c1562gH) {
        if (c1562gH.b.isEmpty()) {
            return;
        }
        a(list, c2415qF.a, c1562gH.a, c1647hH, ie, tg, c1562gH.b, set);
    }

    public static ArrayList a(HashSet hashSet, final IE ie, final HashMap map) {
        final ArrayList arrayList = new ArrayList();
        hashSet.forEach(new Consumer() { // from class: xch
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1817jH.a(ie, map, arrayList, (HE) obj);
            }
        });
        return arrayList;
    }

    public static void a(C1647hH c1647hH, Set set, IE ie, final HashMap map, final InterfaceC1733iH interfaceC1733iH) {
        final NW nw = NW.c;
        final ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        boolean z = false;
        while (it.hasNext()) {
            HE he = (HE) it.next();
            AbstractC2671tG abstractC2671tG = ((FE) ie.a.get(he)).a;
            if (abstractC2671tG.d()) {
                nw = NW.e;
            } else if (!z) {
                AG agB = abstractC2671tG.b();
                FG fg = agB.b;
                fg.getClass();
                if (fg == EG.d) {
                    arrayList.clear();
                    z = true;
                }
                map.putIfAbsent(he, agB.b);
                arrayList.add(he);
            }
        }
        if (z && nw == NW.e) {
            nw = NW.d;
        }
        if (arrayList.isEmpty()) {
            nw = NW.d;
        }
        Consumer consumer = new Consumer() { // from class: bdh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1733iH.a((C1647hH) obj, map, arrayList, nw);
            }
        };
        C2416qG c2416qG = (C2416qG) c1647hH.a.b;
        if (c2416qG.a.d()) {
            consumer.accept(c1647hH);
            return;
        }
        if (!c2416qG.b) {
            consumer.accept(c1647hH);
            return;
        }
        if (c1647hH.a.a.e()) {
            C1476fH.a();
            C2416qG c2416qG2 = C2416qG.c;
            ME me = c1647hH.a;
            C1476fH c1476fH = me.a;
            AbstractC2515rV abstractC2515rV = me.c;
            if (LE.a || abstractC2515rV != null) {
                consumer.accept(new C1647hH(new ME(c1476fH, C2416qG.c, abstractC2515rV)));
                return;
            } else {
                x1f.a();
                return;
            }
        }
        if (c1647hH.a.a.d()) {
            C1476fH.a();
            C2416qG c2416qG3 = C2416qG.c;
            ME me2 = c1647hH.a;
            C1476fH c1476fH2 = me2.a;
            AbstractC2515rV abstractC2515rV2 = me2.c;
            if (!LE.a && abstractC2515rV2 == null) {
                x1f.a();
                return;
            }
            C1647hH c1647hH2 = new C1647hH(new ME(c2416qG.a, C2416qG.c, abstractC2515rV2));
            consumer.accept(c1647hH);
            consumer.accept(c1647hH2);
            return;
        }
        C1476fH.a();
        C2416qG c2416qG4 = C2416qG.c;
        ME me3 = c1647hH.a;
        C1476fH c1476fH3 = me3.a;
        AbstractC2515rV abstractC2515rV3 = me3.c;
        if (!LE.a && abstractC2515rV3 == null) {
            x1f.a();
            return;
        }
        C1647hH c1647hH3 = new C1647hH(new ME(c1476fH3, C2416qG.c, abstractC2515rV3));
        consumer.accept(c1647hH);
        consumer.accept(c1647hH3);
    }

    public final void a(final List list, C1647hH c1647hH, final C3097yF c3097yF, IE ie, final TG tg, Set set) {
        a(c1647hH, set, ie, new HashMap(), new InterfaceC1733iH() { // from class: tch
            @Override // com.android.tools.r8.internal.InterfaceC1733iH
            public final void a(C1647hH c1647hH2, Map map, List list2, NW nw) {
                this.a.a(list, c3097yF, tg, c1647hH2, map, list2, nw);
            }
        });
    }

    public final /* synthetic */ void a(List list, C3097yF c3097yF, TG tg, C1647hH c1647hH, Map map, List list2, NW nw) {
        if (nw.equals(NW.c)) {
            list.add(new KW(Collections.EMPTY_LIST, c3097yF, tg, c1647hH, map, list2, nw));
        } else {
            list.add(new MW(c3097yF, c1647hH, tg, map, list2, nw));
        }
    }

    public final /* synthetic */ void a(List list, C3097yF c3097yF, TG tg, C1647hH c1647hH, Map map, List list2, C1647hH c1647hH2, Map map2, List list3, NW nw) {
        list.add(new JW(c3097yF, tg, c1647hH, c1647hH2, map, list2, list3, nw));
    }

    public final void a(final List list, C1647hH c1647hH, final C3097yF c3097yF, IE ie, final TG tg, HashSet hashSet, Set set) {
        final HashMap map = new HashMap();
        final ArrayList arrayListA = a(hashSet, ie, map);
        a(c1647hH, set, ie, map, new InterfaceC1733iH() { // from class: ych
            @Override // com.android.tools.r8.internal.InterfaceC1733iH
            public final void a(C1647hH c1647hH2, Map map2, List list2, NW nw) {
                this.a.a(map, arrayListA, list, c3097yF, tg, c1647hH2, map2, list2, nw);
            }
        });
    }

    public final void a(Map map, List list, List list2, C3097yF c3097yF, TG tg, C1647hH c1647hH, Map map2, List list3, NW nw) {
        ArrayList arrayList = new ArrayList(list3.size());
        Iterator it = list3.iterator();
        while (it.hasNext()) {
            HE he = (HE) it.next();
            FG fg = (FG) map.get(he);
            if (fg.g() && list.contains(he)) {
                HashMap map3 = new HashMap(map);
                HG hg = HG.k;
                HG hgC = ((GG) new GG().a(fg.d())).c();
                C2345pV c2345pV = C2345pV.b;
                IG ig = IG.b;
                QG qg = OG.b;
                JG jg = JG.a;
                ig.getClass();
                if (IG.c == ig || IG.d == ig) {
                    if (!qg.b()) {
                        defpackage.l0.a("Method constructor pattern must match 'void' type.");
                        return;
                    }
                    qg = PG.a;
                }
                map3.put(he, new NG(c2345pV, hgC, ig, qg, jg));
                list2.add(new KW(list, c3097yF, tg, c1647hH, map3, Collections.singletonList(he), nw));
                HashMap map4 = new HashMap(map);
                C1901kG c1901kG = C1901kG.h;
                map4.put(he, new C2158nG(c2345pV, ((C1816jG) new C1816jG().a(fg.d())).c(), C1987lG.b, C2244oG.b));
                list2.add(new KW(list, c3097yF, tg, c1647hH, map4, Collections.singletonList(he), nw));
            } else {
                arrayList.add(he);
            }
        }
        if (nw.equals(NW.c) && arrayList.isEmpty()) {
            return;
        }
        list2.add(new KW(list, c3097yF, tg, c1647hH, map, arrayList, nw));
    }

    public static HE a(AbstractC2757uG abstractC2757uG, IE ie) {
        Collection collectionC;
        HashSet<HE> hashSet = new HashSet(2);
        ArrayDeque arrayDeque = new ArrayDeque();
        if (abstractC2757uG.f()) {
            collectionC = Collections.singletonList(abstractC2757uG.a());
        } else {
            collectionC = abstractC2757uG.c().c();
        }
        arrayDeque.addAll(collectionC);
        while (!arrayDeque.isEmpty()) {
            EE ee = (EE) arrayDeque.pop();
            if (hashSet.add(ee.a)) {
                arrayDeque.addAll(((FE) ie.a.get(ee.a)).a.c());
            }
        }
        HE he = null;
        for (HE he2 : hashSet) {
            if (((FE) ie.a.get(he2)).a.d()) {
                if (he != null) {
                    defpackage.l0.a("Unexpected reference to multiple class bindings");
                    return null;
                }
                he = he2;
            }
        }
        return he;
    }
}
