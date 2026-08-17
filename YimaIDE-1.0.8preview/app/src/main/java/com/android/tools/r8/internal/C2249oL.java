package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2249oL {
    public static final /* synthetic */ boolean b = true;
    public final int a;

    public C2249oL(int i) {
        this.a = i;
    }

    public final boolean a() {
        return this.a < 0;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof C2249oL) && this.a == ((C2249oL) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }

    public final String toString() {
        if (!a()) {
            if (!b && a()) {
                x1f.a();
                return null;
            }
            return "v" + this.a;
        }
        boolean z = b;
        if (!z && !a()) {
            x1f.a();
            return null;
        }
        int i = (this.a & Integer.MAX_VALUE) >> 16;
        if (!z && !a()) {
            x1f.a();
            return null;
        }
        return "phi(" + i + "," + (this.a & 65535) + ")";
    }
}
