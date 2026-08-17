package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1601gk0 extends RuntimeException {
    public C1601gk0(ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("Message missing required fields: ");
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        super(sb.toString());
    }

    public C1601gk0() {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
