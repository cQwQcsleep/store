package com.hierynomus.sshj.common;

import java.io.IOException;
import org.bouncycastle.openssl.EncryptionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class KeyDecryptionFailedException extends IOException {
    public static final String MESSAGE = "Decryption of the key failed. A supplied passphrase may be incorrect.";

    public KeyDecryptionFailedException() {
        super(MESSAGE);
    }

    public KeyDecryptionFailedException(EncryptionException encryptionException) {
        super(MESSAGE, encryptionException);
    }
}
