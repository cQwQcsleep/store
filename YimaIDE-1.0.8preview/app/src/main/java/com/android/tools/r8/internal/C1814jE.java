package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1814jE {
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public final HashMap c = new HashMap();
    public final LinkedHashSet d = new LinkedHashSet();

    public final int a(String str, boolean z) {
        KB.c(str, "className");
        Integer num = (Integer) this.c.get(str);
        if (num != null) {
            int iIntValue = num.intValue();
            if (z == this.d.contains(Integer.valueOf(iIntValue))) {
                return iIntValue;
            }
        }
        int size = this.a.size();
        if (z) {
            this.d.add(Integer.valueOf(size));
        }
        C1387eE c1387eE = C1387eE.n;
        C1219cE c1219cE = new C1219cE();
        if (z || AbstractC1679hg0.a((CharSequence) str, '$', false, 2) >= 0) {
            this.a.add(str);
        } else {
            Integer num2 = (Integer) ND.e.get(str);
            if (num2 != null) {
                int iIntValue2 = num2.intValue();
                c1219cE.c |= 2;
                c1219cE.e = iIntValue2;
                this.a.add(XmlPullParser.NO_NAMESPACE);
            } else {
                EnumC1303dE enumC1303dE = EnumC1303dE.e;
                c1219cE.c |= 8;
                c1219cE.g = enumC1303dE;
                this.a.add("L" + AbstractC1679hg0.a(str, '.', '$') + ';');
            }
        }
        this.b.add(c1219cE);
        this.c.put(str, Integer.valueOf(size));
        return size;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0060  */
    public final int a(String str) {
        KB.c(str, "string");
        HashMap map = this.c;
        Object objValueOf = map.get(str);
        if (objValueOf == null) {
            int size = this.a.size();
            this.a.add(str);
            ArrayList arrayList = this.b;
            KB.c(arrayList, "<this>");
            C1219cE c1219cE = (C1219cE) (arrayList.isEmpty() ? null : arrayList.get(arrayList.size() - 1));
            if (c1219cE != null) {
                int i = c1219cE.c;
                if ((i & 2) != 2 && (i & 8) != 8 && c1219cE.h.size() == 0 && c1219cE.i.size() == 0) {
                    int i2 = c1219cE.d + 1;
                    c1219cE.c |= 1;
                    c1219cE.d = i2;
                } else {
                    ArrayList arrayList2 = this.b;
                    C1387eE c1387eE = C1387eE.n;
                    arrayList2.add(new C1219cE());
                }
            } else {
                ArrayList arrayList3 = this.b;
                C1387eE c1387eE2 = C1387eE.n;
                arrayList3.add(new C1219cE());
            }
            objValueOf = Integer.valueOf(size);
            map.put(str, objValueOf);
        }
        return ((Number) objValueOf).intValue();
    }
}
