package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2066m90 {
    public static final List a(Consumer consumer, List list) {
        KB.c(consumer, "rootsConsumer");
        C1639h90 c1639h90 = new C1639h90(consumer);
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            W50 w50 = (W50) obj;
            if (!w50.a()) {
                int i = w50.b;
                if ((i & 8) == 0 && (i & 4) == 0) {
                }
            }
            arrayList.add(obj);
        }
        c1639h90.b(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a(setNewSetFromMap, (W50) it.next(), null);
        }
        return Wa0.a(new C1013Zp(new C1013Zp(new C1013Zp(new C1013Zp(new C2443qe(list), false, C1725i90.c), true, C1809j90.c), true, C1894k90.c), false, C1980l90.c));
    }

    public static final String a(String str) {
        KB.c(str, "resourceName");
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':' || cCharAt == '.' || cCharAt == '-') {
                char[] cArr = new char[str.length()];
                str.getChars(0, i, cArr, 0);
                cArr[i] = '_';
                while (true) {
                    i++;
                    if (i < length) {
                        char cCharAt2 = str.charAt(i);
                        if (cCharAt2 == ':' || cCharAt2 == '.' || cCharAt2 == '-') {
                            cCharAt2 = '_';
                        }
                        cArr[i] = cCharAt2;
                    } else {
                        return new String(cArr);
                    }
                }
            } else {
                i++;
            }
        }
        return str;
    }

    public static final void a(Set set, W50 w50, W50 w51) {
        if (w51 != null && w51.h != null) {
            X50 x50 = new X50(w51);
            if (w50.h == null) {
                w50.h = new HashSet();
            }
            w50.h.add(x50);
        }
        if (set.contains(w50)) {
            return;
        }
        set.add(w50);
        w50.a(true);
        ArrayList<W50> arrayList = w50.g;
        if (arrayList != null) {
            for (W50 w52 : arrayList) {
                KB.b(w52, "it");
                a(set, w52, w50);
            }
        }
    }
}
