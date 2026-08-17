package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2299ow;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2640sw {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final C2299ow a(int i) {
        return (C2299ow) this.a.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: ibi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2299ow(((Integer) obj).intValue());
            }
        });
    }
}
