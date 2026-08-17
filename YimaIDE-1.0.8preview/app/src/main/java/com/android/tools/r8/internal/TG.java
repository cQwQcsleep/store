package com.android.tools.r8.internal;

import com.android.tools.r8.internal.SG;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TG {
    public static final TG b;
    public final AbstractC2554rv a;

    static {
        int i = AbstractC2554rv.c;
        b = new TG(W40.j);
    }

    public TG(AbstractC2554rv abstractC2554rv) {
        this.a = abstractC2554rv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TG.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((TG) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "KeepOptions{" + ((String) this.a.stream().map(new Function() { // from class: w0e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Objects.toString((SG) obj);
            }
        }).collect(Collectors.joining(", "))) + "}";
    }
}
