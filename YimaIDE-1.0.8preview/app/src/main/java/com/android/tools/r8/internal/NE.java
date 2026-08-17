package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NE extends PE {
    public static final /* synthetic */ boolean c = true;
    public final KE b;

    public NE(KE ke) {
        if (c || ke != null) {
            this.b = ke;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2757uG
    public final EE a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NE) {
            return this.b.equals(((NE) obj).b);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.PE
    public final List h() {
        return Collections.singletonList(this.b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.toString();
    }
}
