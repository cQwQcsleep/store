package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.EnumC0272p0;
import com.android.tools.r8.graph.InterfaceC0332x5;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3383e {
    public final InterfaceC0332x5 a;
    public final C0285r0 b;
    public final EnumC0272p0 c;

    public C3383e(InterfaceC0332x5 interfaceC0332x5, C0285r0 c0285r0, EnumC0272p0 enumC0272p0) {
        this.a = interfaceC0332x5;
        this.b = c0285r0;
        this.c = enumC0272p0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C3383e) {
            C3383e c3383e = (C3383e) obj;
            if (this.a == c3383e.a && this.b == c3383e.b && this.c == c3383e.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b, this.c);
    }
}
