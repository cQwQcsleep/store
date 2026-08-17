package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rf0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2531rf0 {
    public static final InterfaceC2446qf0[] a = {new C2018lf0(), new C2189nf0(), new C2275of0(), new C2103mf0()};

    public static String a(InterfaceC1168bf0 interfaceC1168bf0, C2361pf0 c2361pf0) {
        if (interfaceC1168bf0.b()) {
            return interfaceC1168bf0.e();
        }
        ArrayList arrayList = interfaceC1168bf0.a().c;
        if (arrayList.size() != 2) {
            return null;
        }
        return (String) c2361pf0.h.get(((C2543rl0) arrayList.get(1)).h());
    }
}
