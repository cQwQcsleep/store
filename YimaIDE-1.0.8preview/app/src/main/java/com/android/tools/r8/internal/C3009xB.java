package com.android.tools.r8.internal;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3009xB {
    public final List a;
    public final List b;

    public C3009xB(List list, List list2) {
        this.a = list;
        this.b = list2;
    }

    public final C3009xB a(Function function) {
        return new C3009xB((List) this.a.stream().map(function).collect(Collectors.toList()), (List) this.b.stream().map(function).collect(Collectors.toList()));
    }
}
