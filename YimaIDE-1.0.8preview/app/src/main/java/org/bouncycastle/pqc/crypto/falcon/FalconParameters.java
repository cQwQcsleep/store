package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FalconParameters implements CipherParameters {
    private final int logn;
    private final String name;
    private final int nonce_length;
    public static final FalconParameters falcon_512 = new FalconParameters("falcon-512", 9, 40);
    public static final FalconParameters falcon_1024 = new FalconParameters("falcon-1024", 10, 40);

    private FalconParameters(String str, int i, int i2) {
        if (i < 1 || i > 10) {
            w01.a("Log N degree should be between 1 and 10");
            throw null;
        }
        this.name = str;
        this.logn = i;
        this.nonce_length = i2;
    }

    public int getLogN() {
        return this.logn;
    }

    public String getName() {
        return this.name;
    }

    public int getNonceLength() {
        return this.nonce_length;
    }
}
