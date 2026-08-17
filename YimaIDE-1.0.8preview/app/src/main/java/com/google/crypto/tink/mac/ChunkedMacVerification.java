package com.google.crypto.tink.mac;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface ChunkedMacVerification {
    void update(ByteBuffer byteBuffer) throws GeneralSecurityException;

    void verifyMac() throws GeneralSecurityException;
}
