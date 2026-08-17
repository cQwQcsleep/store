package org.bouncycastle.crypto.params;

import defpackage.w01;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class KeyParameter implements CipherParameters {
    private byte[] key;

    public KeyParameter(byte[] bArr, int i, int i2) {
        this(i2);
        System.arraycopy(bArr, i, this.key, 0, i2);
    }

    public void copyTo(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.key;
        if (bArr2.length == i2) {
            System.arraycopy(bArr2, 0, bArr, i, i2);
        } else {
            w01.a("len");
        }
    }

    public byte[] getKey() {
        return this.key;
    }

    public int getKeyLength() {
        return this.key.length;
    }

    public KeyParameter reverse() {
        KeyParameter keyParameter = new KeyParameter(this.key.length);
        Arrays.reverse(this.key, keyParameter.key);
        return keyParameter;
    }

    public KeyParameter(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    private KeyParameter(int i) {
        this.key = new byte[i];
    }
}
