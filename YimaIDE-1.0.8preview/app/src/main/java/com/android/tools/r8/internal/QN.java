package com.android.tools.r8.internal;

import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QN extends AbstractC1960l {
    public final MessageDigest b;
    public final int c;
    public boolean d;

    public QN(MessageDigest messageDigest, int i) {
        this.b = messageDigest;
        this.c = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public final C0886Us a() {
        b();
        this.d = true;
        int i = this.c;
        int digestLength = this.b.getDigestLength();
        MessageDigest messageDigest = this.b;
        if (i == digestLength) {
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = AbstractC0912Vs.b;
            return new C0886Us(bArrDigest);
        }
        byte[] bArrCopyOf = Arrays.copyOf(messageDigest.digest(), this.c);
        char[] cArr2 = AbstractC0912Vs.b;
        return new C0886Us(bArrCopyOf);
    }

    public final void b() {
        if (this.d) {
            k2d.a("Cannot re-use a Hasher after calling hash() on it");
        }
    }
}
