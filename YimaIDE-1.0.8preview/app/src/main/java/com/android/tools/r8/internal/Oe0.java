package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Oe0 extends AbstractC2451qi {
    public final /* synthetic */ Map d;
    public final /* synthetic */ Map e;
    public final /* synthetic */ Set f;

    public Oe0(IdentityHashMap identityHashMap, IdentityHashMap identityHashMap2, LinkedHashSet linkedHashSet) {
        this.d = identityHashMap;
        this.e = identityHashMap2;
        this.f = linkedHashSet;
    }

    @Override // com.android.tools.r8.internal.AbstractC2451qi
    public final void a(InterfaceC2194ni interfaceC2194ni, Function function) {
        Set set = (Set) this.d.get((AbstractC1932kf0) ((C2280oi) interfaceC2194ni).a);
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                function.apply((AbstractC1932kf0) this.e.get((AbstractC1932kf0) it.next()));
            }
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2621si
    public final List a(Collection collection) {
        return null;
    }
}
