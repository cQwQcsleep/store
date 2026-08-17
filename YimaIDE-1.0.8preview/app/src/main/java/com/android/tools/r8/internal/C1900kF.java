package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1560gF;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1900kF extends AbstractC2072mF {
    public final AbstractC2554rv b;

    public C1900kF(Set set) {
        this.b = AbstractC2554rv.a(set);
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final TG a(TG tg) {
        TG tg2 = TG.b;
        RG rg = new RG(false);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((AbstractC1560gF) it.next()).a(rg);
        }
        return rg.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final Set b() {
        HashSet hashSet = new HashSet();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((AbstractC1560gF) it.next()).a(hashSet);
        }
        return hashSet;
    }

    public final String toString() {
        return "KeepConstraints{" + ((String) this.b.stream().map(new Function() { // from class: whh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Objects.toString((AbstractC1560gF) obj);
            }
        }).collect(Collectors.joining(", "))) + "}";
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final AbstractC2554rv a() {
        return this.b;
    }
}
