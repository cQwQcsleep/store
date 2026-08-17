package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OE extends PE {
    public static final /* synthetic */ boolean c = true;
    public final ME b;

    public OE(ME me) {
        if (c || me != null) {
            this.b = me;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2757uG
    public final ME b() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2757uG
    public final AbstractC2671tG c() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OE) {
            return this.b.equals(((OE) obj).b);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.PE
    public final List h() {
        return Collections.EMPTY_LIST;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.toString();
    }
}
