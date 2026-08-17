package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2251oN;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2337pN {
    public static final C2337pN c = new C2337pN(com.android.tools.r8.shaking.V1.b, Collections.EMPTY_LIST);
    public final com.android.tools.r8.shaking.V1 a;
    public final List b;

    public C2337pN(com.android.tools.r8.shaking.V1 v1, List list) {
        this.a = v1;
        this.b = list;
    }

    public final W2 a() {
        return (this.a.a.isEmpty() && this.b.isEmpty()) ? V2.a : new X2(this.a, C2847vL.a((Collection) this.b, new Function() { // from class: m0i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C2251oN) obj).a();
            }
        }));
    }
}
