package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XY implements com.android.tools.r8.naming.P {
    public final AbstractC1323dZ a;
    public final LinkedHashMap b = new LinkedHashMap();
    public ArrayList c = new ArrayList();

    public XY(AbstractC1323dZ abstractC1323dZ) {
        this.a = abstractC1323dZ;
    }

    @Override // com.android.tools.r8.naming.P
    public final String a() {
        String strA = this.a.a();
        if (strA == null) {
            return null;
        }
        if (this.a.g == 6) {
            ArrayList arrayList = new ArrayList();
            this.c = arrayList;
            this.b.put(strA, arrayList);
        }
        this.c.add(strA);
        return strA;
    }

    @Override // com.android.tools.r8.naming.P
    public final void close() {
        this.a.close();
    }

    public final void a(BiConsumer biConsumer) {
        this.b.forEach(biConsumer);
    }
}
