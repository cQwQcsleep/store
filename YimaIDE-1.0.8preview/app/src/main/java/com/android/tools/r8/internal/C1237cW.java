package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1237cW implements InterfaceC0869Ub {
    public final Class a;

    public C1237cW(Class cls, String str) {
        KB.c(cls, "jClass");
        this.a = cls;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0869Ub
    public final Class a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1237cW) && KB.a(this.a, ((C1237cW) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString() + " (Kotlin reflection is not available)";
    }
}
