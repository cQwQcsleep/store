package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GH extends JH {
    public final float a;

    public GH(float f) {
        this.a = f;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Float.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GH) && Float.compare(this.a, ((GH) obj).a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.a);
    }
}
