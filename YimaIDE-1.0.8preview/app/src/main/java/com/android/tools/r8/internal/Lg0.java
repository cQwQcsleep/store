package com.android.tools.r8.internal;

import defpackage.g3c;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Lg0 {
    public final AbstractC0574Ir a;
    public final byte[] b;
    public final int c;

    public Lg0(L00 l00) {
        this.a = l00;
        L0 l0C = l00.c();
        try {
            int iC = l0C.c();
            byte[] bArr = new byte[iC];
            C0767Qd c0767Qd = new C0767Qd(iC, bArr);
            l0C.a(c0767Qd);
            if (iC - c0767Qd.c != 0) {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
            this.b = bArr;
            this.c = Arrays.hashCode(bArr);
        } catch (IOException e) {
            g3c.a("Serializing to a byte array threw an IOException (should never happen).", e);
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Lg0) && Arrays.equals(this.b, ((Lg0) obj).b);
    }

    public final int hashCode() {
        return this.c;
    }
}
