package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BE {
    public static final BE c = new BE(C1476fH.a(), 3);
    public static final BE d = new BE(C1476fH.a(), 1);
    public static final BE e = new BE(C1476fH.a(), 2);
    public static final /* synthetic */ boolean f = true;
    public final C1476fH a;
    public final int b;

    public BE(C1476fH c1476fH, int i) {
        if (!f && c1476fH == null) {
            x1f.a();
            throw null;
        }
        this.a = c1476fH;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BE)) {
            return false;
        }
        BE be = (BE) obj;
        return this.b == be.b && this.a.equals(be.a);
    }

    public final int hashCode() {
        return Objects.hash(this.a, Integer.valueOf(this.b));
    }
}
