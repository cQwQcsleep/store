package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1041a8;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1211c8 {
    public static final C1211c8 b = new C1211c8(Collections.EMPTY_MAP);
    public static final /* synthetic */ boolean c = true;
    public final Map a;

    public C1211c8(Map map) {
        if (c || map.values().stream().noneMatch(new Predicate() { // from class: jjg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.isNull((C1041a8) obj);
            }
        })) {
            this.a = map;
        } else {
            x1f.a();
            throw null;
        }
    }
}
