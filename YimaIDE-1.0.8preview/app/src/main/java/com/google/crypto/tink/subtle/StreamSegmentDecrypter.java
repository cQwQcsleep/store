package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface StreamSegmentDecrypter {
    void decryptSegment(ByteBuffer byteBuffer, int i, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException;

    void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException;
}
