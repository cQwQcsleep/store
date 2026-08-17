package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.GV;
import com.android.tools.r8.naming.C3337n;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.naming.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3339o {
    public final IdentityHashMap a = new IdentityHashMap();
    public final IdentityHashMap b = new IdentityHashMap();

    public final C3337n a(final C3331k.b bVar, com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        List list = eVar instanceof GV ? (List) this.b.get(eVar.d()) : (List) this.a.get(eVar.c());
        if (list == null) {
            return null;
        }
        int iA = C2847vL.a(list, new Predicate() { // from class: cwh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C3337n) obj).b.b.a(bVar.b.a);
            }
        });
        return (C3337n) (iA >= 0 ? list.get(iA) : null);
    }
}
