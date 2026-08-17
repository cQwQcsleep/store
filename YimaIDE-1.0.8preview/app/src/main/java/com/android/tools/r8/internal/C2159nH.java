package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2159nH {
    public static final /* synthetic */ boolean c = true;
    public final AbstractC2757uG a;
    public final AbstractC2072mF b;

    public C2159nH(AbstractC2757uG abstractC2757uG, AbstractC2072mF abstractC2072mF) {
        boolean z = c;
        if (!z && abstractC2757uG == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2072mF == null) {
            x1f.a();
            throw null;
        }
        this.a = abstractC2757uG;
        this.b = abstractC2072mF;
    }

    public static C2074mH a() {
        return new C2074mH();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2159nH)) {
            return false;
        }
        C2159nH c2159nH = (C2159nH) obj;
        return this.a.equals(c2159nH.a) && this.b.equals(c2159nH.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    public final String toString() {
        return "KeepTarget{item=" + this.a + ", constraints=" + this.b + "}";
    }
}
