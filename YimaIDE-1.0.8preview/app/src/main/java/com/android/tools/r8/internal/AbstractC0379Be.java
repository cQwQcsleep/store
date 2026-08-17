package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0379Be;
import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Be, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0379Be {
    public static final int a(Comparator comparator, Object obj, Object obj2) {
        KB.c(comparator, "$comparator");
        if (obj == obj2) {
            return 0;
        }
        if (obj == null) {
            return 1;
        }
        if (obj2 == null) {
            return -1;
        }
        return comparator.compare(obj, obj2);
    }

    public static Comparator a() {
        final C2681tQ c2681tQ = C2681tQ.b;
        return new Comparator() { // from class: fu0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AbstractC0379Be.a(c2681tQ, obj, obj2);
            }
        };
    }
}
