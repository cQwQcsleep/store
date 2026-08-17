package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3099yH extends AH {
    public final C3015xH a;

    public C3099yH(C3015xH c3015xH) {
        KB.c(c3015xH, "annotation");
        this.a = c3015xH;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C3099yH) && KB.a(this.a, ((C3099yH) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.AH
    public final String toString() {
        return "AnnotationValue(" + this.a + ')';
    }
}
