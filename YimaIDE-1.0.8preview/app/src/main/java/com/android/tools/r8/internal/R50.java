package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R50 {
    public final boolean a = true;
    public final LinkedHashMap b = new LinkedHashMap(AbstractC1739iN.a(200));
    public final ArrayList c;
    public final EnumMap d;
    public final HashMap e;
    public final HashSet f;
    public final ArrayList g;
    public final ArrayList h;
    public boolean i;

    public R50() {
        AbstractC0871Ud.a(200, "initialArraySize");
        this.c = new ArrayList(200);
        this.d = new EnumMap(U50.class);
        this.e = new HashMap(AbstractC1739iN.a(200));
        this.f = new HashSet();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = true;
    }

    public static final Ua0 a(R50 r50, String str) {
        r50.getClass();
        V50 v50A = V50.a(str);
        if (v50A == null || "android".equals(v50A.d)) {
            return C1183bn.a;
        }
        C2590sK c2590sK = (C2590sK) r50.d.get(v50A.b);
        if (c2590sK == null) {
            return C1183bn.a;
        }
        String str2 = v50A.c;
        KB.b(str2, "url.name");
        if (!AbstractC1679hg0.a((CharSequence) str2, "*")) {
            String str3 = v50A.c;
            KB.b(str3, "url.name");
            if (!AbstractC1679hg0.a((CharSequence) str3, "?")) {
                return new C2443qe(new C1905kK(c2590sK, v50A.c));
            }
        }
        try {
            String str4 = v50A.c;
            KB.b(str4, "url.name");
            String strA = Qa0.a(AbstractC2066m90.a(str4));
            KB.b(strA, "globToRegexp(resourceNameToFieldName(url.name))");
            return new Yh0(new C1013Zp(new C2443qe(c2590sK.j()), true, new L50(new J40(strA))), M50.c);
        } catch (PatternSyntaxException unused) {
            return C1183bn.a;
        }
    }

    public final List b(String str) {
        KB.c(str, "webUrl");
        List listA = AbstractC1679hg0.a(AbstractC1679hg0.b(str, "android_res/"), new String[]{"/"}, 2, 2);
        if (listA.size() < 2) {
            return C0984Ym.b;
        }
        String strSubstring = (String) listA.get(0);
        String str2 = (String) listA.get(1);
        E50 e50 = E50.c;
        int iIndexOf = strSubstring.indexOf(45);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        if (!E50.s && strSubstring.indexOf(45) != -1) {
            x01.a(strSubstring);
            return null;
        }
        E50 e51 = (E50) E50.q.get(strSubstring);
        if (e51 == null) {
            return C0984Ym.b;
        }
        String strB = AbstractC1679hg0.b(str2, '.');
        List<U50> list = (List) AbstractC0754Pq.b.get(e51);
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        KB.b(list, "getRelatedResourceTypes(folderType)");
        ArrayList arrayList = new ArrayList();
        for (U50 u50 : list) {
            KB.b(u50, "it");
            arrayList.addAll(a(u50, strB));
        }
        return arrayList;
    }

    public final List a() {
        List listUnmodifiableList = Collections.unmodifiableList(this.c);
        KB.b(listUnmodifiableList, "unmodifiableList(_resources)");
        return listUnmodifiableList;
    }

    public final W50 a(int i) {
        return (W50) this.e.get(Integer.valueOf(i));
    }

    public final List a(U50 u50, String str) {
        KB.c(u50, "type");
        KB.c(str, TypeBlock.NAME_name);
        C2590sK c2590sK = (C2590sK) this.d.get(u50);
        return c2590sK == null ? C0984Ym.b : AbstractC1760ie.c(new C1905kK(c2590sK, AbstractC2066m90.a(str)));
    }

    public final List a(String str) {
        KB.c(str, "possibleUrlReference");
        V50 v50A = V50.a(str);
        if (v50A != null && !"android".equals(v50A.d)) {
            U50 u50 = v50A.b;
            KB.b(u50, "url.type");
            String str2 = v50A.c;
            KB.b(str2, "url.name");
            return a(u50, str2);
        }
        return C0984Ym.b;
    }

    public final W50 a(W50 w50) {
        if (!this.a) {
            w50.e = null;
        }
        U50 u50 = w50.c;
        KB.b(u50, "resource.type");
        String str = w50.d;
        KB.b(str, "resource.name");
        F50 f50 = new F50(u50, str, w50.e);
        W50 w51 = (W50) this.b.get(f50);
        if (w51 == null) {
            this.b.put(f50, w50);
            this.c.add(w50);
            ((C2590sK) this.d.computeIfAbsent(w50.c, K50.b)).a(w50.d, w50, null);
            int i = w50.f;
            if (i != -1) {
                this.e.put(Integer.valueOf(i), w50);
            }
            return w50;
        }
        int i2 = w50.f;
        Object[] objArr = new Object[0];
        if (i2 == -1 || w51.f == i2) {
            if (i2 != -1 && w51.f == -1) {
                w51.f = i2;
                this.e.put(Integer.valueOf(i2), w51);
            }
            return w51;
        }
        k2d.a(Xf0.a("Resource value must be the same between addResource calls.", objArr));
        return null;
    }

    public final List b() {
        ArrayList arrayList = this.g;
        KB.c(arrayList, "<this>");
        C2443qe c2443qe = new C2443qe(arrayList);
        N50 n50 = new N50(this);
        C1412eb0 c1412eb0 = C1412eb0.j;
        List listA = Wa0.a(new Yh0(new C2977wq(c2443qe, n50, c1412eb0), new C1498fb0(new O50(this))));
        ArrayList arrayList2 = this.h;
        KB.c(arrayList2, "<this>");
        List listA2 = Wa0.a(new Yh0(new C2977wq(new C2443qe(arrayList2), new P50(this), c1412eb0), new C1498fb0(Q50.c)));
        if (listA2.isEmpty()) {
            return AbstractC1760ie.c(listA);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : listA) {
            if (!listA2.contains(obj)) {
                arrayList3.add(obj);
            }
        }
        return arrayList3;
    }
}
