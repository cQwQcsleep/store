package com.android.tools.r8.naming;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.C2752uB;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V0 {
    public static boolean a(C2752uB c2752uB, String str) {
        if (str.isEmpty()) {
            return true;
        }
        Objects.requireNonNull(c2752uB.s());
        return "SourceFile".equals(str);
    }

    public static U0 a(B1 b1) {
        Objects.requireNonNull(b1);
        return new U0("SourceFile", true);
    }
}
