package com.android.tools.r8.internal;

import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1138bH extends AbstractC1222cH {
    public static final /* synthetic */ boolean b = true;
    public final List a;

    public C1138bH(List list) {
        boolean z = b;
        if (!z && list == null) {
            x1f.a();
            throw null;
        }
        if (z || !list.isEmpty()) {
            this.a = list;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1222cH
    public final void a(Consumer consumer) {
        this.a.forEach(consumer);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1138bH.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C1138bH) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC1222cH
    public final boolean a() {
        return false;
    }
}
