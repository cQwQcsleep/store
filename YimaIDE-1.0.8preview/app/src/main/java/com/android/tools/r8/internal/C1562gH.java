package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1562gH;
import com.android.tools.r8.internal.TG;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1562gH {
    public static final /* synthetic */ boolean d = true;
    public final C1647hH a;
    public final HashSet b = new HashSet();
    public final HashMap c = new HashMap();

    public C1562gH(C1647hH c1647hH) {
        this.a = c1647hH;
    }

    public final void a(C2159nH c2159nH) {
        if (!d && !c2159nH.a.f()) {
            x1f.a();
        } else {
            ((Set) this.c.computeIfAbsent(c2159nH.b.a(C1817jH.b), new Function() { // from class: mxg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C1562gH.a((TG) obj);
                }
            })).add(c2159nH.a.a().a);
        }
    }

    public static /* synthetic */ Set a(TG tg) {
        return new HashSet();
    }
}
