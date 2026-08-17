package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3015xH {
    public final String a;
    public final Map b;

    public C3015xH(String str, Map map) {
        KB.c(str, "className");
        this.a = str;
        this.b = map;
    }

    public final Map<String, AH> a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3015xH)) {
            return false;
        }
        C3015xH c3015xH = (C3015xH) obj;
        return KB.a((Object) this.a, (Object) c3015xH.a) && KB.a(this.b, c3015xH.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        Iterable iterable;
        Iterable iterableSingletonList;
        Map map = this.b;
        KB.c(map, "<this>");
        if (map.size() == 0) {
            iterableSingletonList = C0984Ym.b;
        } else {
            Iterator it = map.entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (it.hasNext()) {
                    ArrayList arrayList = new ArrayList(map.size());
                    arrayList.add(new C1491fW(entry.getKey(), entry.getValue()));
                    do {
                        Map.Entry entry2 = (Map.Entry) it.next();
                        arrayList.add(new C1491fW(entry2.getKey(), entry2.getValue()));
                    } while (it.hasNext());
                    iterable = arrayList;
                } else {
                    iterableSingletonList = Collections.singletonList(new C1491fW(entry.getKey(), entry.getValue()));
                    KB.b(iterableSingletonList, "singletonList(...)");
                }
                return "@" + this.a + '(' + AbstractC1760ie.a(iterable, null, null, null, C2929wH.c, 31) + ')';
            }
            iterableSingletonList = C0984Ym.b;
        }
        iterable = iterableSingletonList;
        return "@" + this.a + '(' + AbstractC1760ie.a(iterable, null, null, null, C2929wH.c, 31) + ')';
    }
}
