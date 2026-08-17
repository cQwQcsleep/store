package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2587sH;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KG extends LG {
    public static final KG b = new KG(Collections.EMPTY_LIST);
    public static final /* synthetic */ boolean c = true;
    public final List a;

    public KG(List list) {
        if (c || list != null) {
            this.a = list;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.LG
    public final List a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KG) {
            return this.a.equals(((KG) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "(" + ((String) this.a.stream().map(new Function() { // from class: f58
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AbstractC2587sH) obj).toString();
            }
        }).collect(Collectors.joining(", "))) + ")";
    }
}
