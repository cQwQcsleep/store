package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BB implements Comparator {
    public final /* synthetic */ HashMap b;

    public BB(HashMap map) {
        this.b = map;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Integer num = (Integer) this.b.get(obj);
        Integer num2 = (Integer) this.b.get(obj2);
        if (num == num2) {
            return 0;
        }
        if (num == null) {
            return -1;
        }
        if (num2 == null) {
            return 1;
        }
        return num.compareTo(num2);
    }
}
