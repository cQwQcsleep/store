package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D7 extends AbstractC2943wV implements Serializable {
    public final InterfaceC0392Br b;
    public final AbstractC2943wV c;

    public D7(EnumC1144bN enumC1144bN, AbstractC2943wV abstractC2943wV) {
        this.b = enumC1144bN;
        this.c = abstractC2943wV;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.c.compare(this.b.apply(obj), this.b.apply(obj2));
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof D7) {
            D7 d7 = (D7) obj;
            if (this.b.equals(d7.b) && this.c.equals(d7.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c});
    }

    public final String toString() {
        return this.c + ".onResultOf(" + this.b + ")";
    }
}
