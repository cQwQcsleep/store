package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3092yA extends AbstractC2922wA {
    public static final C3092yA e = new C3092yA(1, 0);

    public C3092yA(int i, int i2) {
        super(i, i2, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C3092yA)) {
            return false;
        }
        if (isEmpty() && ((C3092yA) obj).isEmpty()) {
            return true;
        }
        C3092yA c3092yA = (C3092yA) obj;
        return this.b == c3092yA.b && this.c == c3092yA.c;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.b * 31) + this.c;
    }

    public final boolean isEmpty() {
        return this.b > this.c;
    }

    public final String toString() {
        return this.b + ".." + this.c;
    }
}
