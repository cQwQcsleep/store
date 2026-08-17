package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1826jQ extends D implements Serializable {
    public static final C1826jQ c = new C1826jQ(0);
    public final int b;

    static {
        new C1826jQ(AbstractC1103at.a);
    }

    public C1826jQ(int i) {
        this.b = i;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1826jQ) && this.b == ((C1826jQ) obj).b;
    }

    public final int hashCode() {
        return this.b ^ C1826jQ.class.hashCode();
    }

    public final String toString() {
        return AbstractC2181nb0.a(this.b, ")", new StringBuilder("Hashing.murmur3_128("));
    }
}
