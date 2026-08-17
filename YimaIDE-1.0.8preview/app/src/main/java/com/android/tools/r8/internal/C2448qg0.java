package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2448qg0 implements InterfaceC2105mg0, Serializable {
    public final Object b;

    public C2448qg0(Object obj) {
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2448qg0) {
            return WU.a(this.b, ((C2448qg0) obj).b);
        }
        return false;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b});
    }

    public final String toString() {
        return "Suppliers.ofInstance(" + this.b + ")";
    }
}
