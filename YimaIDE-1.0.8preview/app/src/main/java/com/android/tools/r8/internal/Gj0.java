package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Gj0 {
    public final InterfaceC0994Yw a;
    public final C2543rl0 b;

    public Gj0(InterfaceC0994Yw interfaceC0994Yw, C2543rl0 c2543rl0) {
        this.a = interfaceC0994Yw;
        this.b = c2543rl0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Gj0.class == obj.getClass()) {
            Gj0 gj0 = (Gj0) obj;
            if (this.a == gj0.a && this.b == gj0.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
