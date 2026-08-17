package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1823jN {
    public static Map a(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return C1010Zm.b;
        }
        if (size == 1) {
            C1491fW c1491fW = (C1491fW) arrayList.get(0);
            KB.c(c1491fW, "pair");
            Map mapSingletonMap = Collections.singletonMap(c1491fW.b, c1491fW.c);
            KB.b(mapSingletonMap, "singletonMap(...)");
            return mapSingletonMap;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(a(arrayList.size()));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C1491fW c1491fW2 = (C1491fW) it.next();
            linkedHashMap.put(c1491fW2.b, c1491fW2.c);
        }
        return linkedHashMap;
    }

    public static int a(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }
}
