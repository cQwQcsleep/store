package com.google.crypto.tink.subtle;

import java.security.SecureRandom;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class Random {
    private static final ThreadLocal<SecureRandom> localRandom = new ThreadLocal<SecureRandom>() { // from class: com.google.crypto.tink.subtle.Random.1
        @Override // java.lang.ThreadLocal
        public SecureRandom initialValue() {
            return Random.newDefaultSecureRandom();
        }
    };

    private Random() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SecureRandom newDefaultSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] randBytes(int i) {
        byte[] bArr = new byte[i];
        localRandom.get().nextBytes(bArr);
        return bArr;
    }

    public static final int randInt(int i) {
        return localRandom.get().nextInt(i);
    }

    public static final int randInt() {
        return localRandom.get().nextInt();
    }
}
