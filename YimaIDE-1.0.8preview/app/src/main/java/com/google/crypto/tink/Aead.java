package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface Aead {
    byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException;

    byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException;
}
