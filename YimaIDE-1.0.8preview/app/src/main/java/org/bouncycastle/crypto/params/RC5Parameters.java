package org.bouncycastle.crypto.params;

import defpackage.w01;
import org.bouncycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class RC5Parameters implements CipherParameters {
    private byte[] key;
    private int rounds;

    public RC5Parameters(byte[] bArr, int i) {
        if (bArr.length > 255) {
            w01.a("RC5 key length can be no greater than 255");
            throw null;
        }
        byte[] bArr2 = new byte[bArr.length];
        this.key = bArr2;
        this.rounds = i;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public byte[] getKey() {
        return this.key;
    }

    public int getRounds() {
        return this.rounds;
    }
}
