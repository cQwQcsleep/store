package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2867vc0 extends AbstractC2269oc0 {
    public static final /* synthetic */ boolean e = true;
    public final RU d;

    public C2867vc0(C0245l1 c0245l1, RU ru) {
        super(c0245l1);
        if (e || !ru.e()) {
            this.d = ru;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean A() {
        return this.d.d();
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        if (obj != null && C2867vc0.class == obj.getClass()) {
            C2867vc0 c2867vc0 = (C2867vc0) obj;
            if (this.b == c2867vc0.b && this.d.equals(c2867vc0.d)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        return Objects.hash(this.b, this.d);
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "SingleStatefulFieldValue(" + this.b.m0() + ")";
    }

    @Override // com.android.tools.r8.internal.B1
    public final int x() {
        return this.d.c();
    }

    @Override // com.android.tools.r8.internal.AbstractC2269oc0, com.android.tools.r8.internal.B1
    public final RU y() {
        return this.d;
    }
}
