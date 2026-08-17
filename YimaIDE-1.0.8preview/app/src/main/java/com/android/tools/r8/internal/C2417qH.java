package com.android.tools.r8.internal;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2417qH extends AbstractC2587sH {
    public final C1476fH a;

    public C2417qH(C1476fH c1476fH) {
        this.a = c1476fH;
    }

    @Override // com.android.tools.r8.internal.AbstractC2587sH
    public final Object a(Supplier supplier, Function function, Function function2, Function function3) {
        return function3.apply(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C2417qH) {
            return Objects.equals(this.a, ((C2417qH) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a);
    }

    public final String toString() {
        return this.a.toString();
    }
}
