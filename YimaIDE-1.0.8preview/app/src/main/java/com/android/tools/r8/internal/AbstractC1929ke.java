package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ke, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1929ke extends AbstractC1844je {
    public static List a(Object obj) {
        if (obj == null) {
            return C0984Ym.b;
        }
        List listSingletonList = Collections.singletonList(obj);
        KB.b(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static List a(Object... objArr) {
        return objArr.length > 0 ? T3.a(objArr) : C0984Ym.b;
    }
}
