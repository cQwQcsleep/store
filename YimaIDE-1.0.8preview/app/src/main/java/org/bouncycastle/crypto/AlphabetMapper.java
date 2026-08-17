package org.bouncycastle.crypto;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface AlphabetMapper {
    char[] convertToChars(byte[] bArr);

    byte[] convertToIndexes(char[] cArr);

    int getRadix();
}
