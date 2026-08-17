package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.J1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface J1 {
    public static final I1 a = new I1();

    B1 a(C2543rl0 c2543rl0);

    static J1 a(final C0333y c0333y, final com.android.tools.r8.graph.B5 b5) {
        return new J1() { // from class: j87
            @Override // com.android.tools.r8.internal.J1
            public final B1 a(C2543rl0 c2543rl0) {
                return c2543rl0.a(c0333y, b5, J1.a);
            }
        };
    }
}
