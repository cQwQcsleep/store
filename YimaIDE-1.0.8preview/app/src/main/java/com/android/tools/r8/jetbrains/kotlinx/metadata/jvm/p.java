package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.KB;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class p {
    public final LinkedHashMap a = new LinkedHashMap();

    public p() {
        new ArrayList(0);
        new ArrayList(0);
    }

    public final void a(String str, List list, LinkedHashMap linkedHashMap) {
        KB.c(str, "fqName");
        KB.c(list, "fileFacades");
        this.a.put(str, new q(new ArrayList(list), new LinkedHashMap(linkedHashMap)));
    }
}
