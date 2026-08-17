package com.hierynomus.sshj.transport.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import net.schmizz.sshj.transport.cipher.BaseCipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class StreamCipher extends BaseCipher {
    public StreamCipher(int i, String str, String str2) {
        super(0, i, str, str2);
    }

    public void initCipher(Cipher cipher, net.schmizz.sshj.transport.cipher.Cipher.Mode mode, byte[] bArr, byte[] bArr2) throws InvalidKeyException, InvalidAlgorithmParameterException {
        cipher.init(getMode(mode), getKeySpec(bArr));
    }
}
