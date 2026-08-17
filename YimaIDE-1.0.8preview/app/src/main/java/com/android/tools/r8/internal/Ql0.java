package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ql0 {
    public static final Ql0 d = new Ql0(Fcntl.S_IRUSR, Fcntl.S_IRUSR, Fcntl.S_IRUSR);
    public final int a;
    public final int b;
    public final int c;

    public Ql0(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ql0)) {
            return false;
        }
        Ql0 ql0 = (Ql0) obj;
        return this.a == ql0.a && this.b == ql0.b && this.c == ql0.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + ((Integer.hashCode(this.b) + (Integer.hashCode(this.a) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb;
        int i;
        int i2 = this.c;
        int i3 = this.a;
        if (i2 == 0) {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append('.');
            i = this.b;
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append('.');
            sb.append(this.b);
            sb.append('.');
            i = this.c;
        }
        sb.append(i);
        return sb.toString();
    }
}
