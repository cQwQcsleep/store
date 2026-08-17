package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QE {
    public static final /* synthetic */ boolean b = true;
    public final AbstractC2757uG a;

    public QE(AbstractC2757uG abstractC2757uG) {
        if (b || abstractC2757uG != null) {
            this.a = abstractC2757uG;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QE.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((QE) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
