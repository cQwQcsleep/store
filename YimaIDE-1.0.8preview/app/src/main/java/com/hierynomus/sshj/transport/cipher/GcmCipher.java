package com.hierynomus.sshj.transport.cipher;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import net.schmizz.sshj.common.SSHRuntimeException;
import net.schmizz.sshj.transport.cipher.BaseCipher;
import net.schmizz.sshj.transport.cipher.Cipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class GcmCipher extends BaseCipher {
    protected int authSize;
    protected boolean initialized;
    protected Cipher.Mode mode;
    protected CounterGCMParameterSpec parameters;
    protected SecretKey secretKey;

    public static class CounterGCMParameterSpec extends GCMParameterSpec {
        protected final byte[] iv;

        public CounterGCMParameterSpec(int i, byte[] bArr) {
            super(i, bArr);
            if (bArr.length == 12) {
                this.iv = (byte[]) bArr.clone();
            } else {
                jt6.a("GCM nonce must be 12 bytes, but given len=", bArr.length);
                throw null;
            }
        }

        public static long addExact(long j, long j2) {
            long j3 = j + j2;
            if (((j ^ j3) & (j2 ^ j3)) >= 0) {
                return j3;
            }
            pv9.a("long overflow");
            return 0L;
        }

        public static long getLong(byte[] bArr, int i, int i2) {
            if (i2 >= 8) {
                return (((long) bArr[i + 7]) & 255) | (((long) bArr[i]) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
            }
            qf1.a("Not enough data for a long: required=8, available=", i2);
            return 0L;
        }

        public static int putLong(long j, byte[] bArr, int i, int i2) {
            if (i2 < 8) {
                qf1.a("Not enough data for a long: required=8, available=", i2);
                return 0;
            }
            bArr[i] = (byte) (j >> 56);
            bArr[i + 1] = (byte) (j >> 48);
            bArr[i + 2] = (byte) (j >> 40);
            bArr[i + 3] = (byte) (j >> 32);
            bArr[i + 4] = (byte) (j >> 24);
            bArr[i + 5] = (byte) (j >> 16);
            bArr[i + 6] = (byte) (j >> 8);
            bArr[i + 7] = (byte) j;
            return 8;
        }

        @Override // javax.crypto.spec.GCMParameterSpec
        public byte[] getIV() {
            return (byte[]) this.iv.clone();
        }

        public void incrementCounter() {
            byte[] bArr = this.iv;
            int length = bArr.length - 8;
            putLong(addExact(getLong(bArr, length, 8), 1L), this.iv, length, 8);
        }
    }

    public GcmCipher(int i, int i2, int i3, String str, String str2) {
        super(i, i3, str, str2);
        this.authSize = i2;
    }

    public int getAuthenticationTagSize() {
        return this.authSize;
    }

    public javax.crypto.Cipher getInitializedCipherInstance() throws GeneralSecurityException {
        if (!this.initialized) {
            ((BaseCipher) this).cipher.init(this.mode == Cipher.Mode.Encrypt ? 1 : 2, this.secretKey, this.parameters);
            this.initialized = true;
        }
        return ((BaseCipher) this).cipher;
    }

    public void initCipher(javax.crypto.Cipher cipher, Cipher.Mode mode, byte[] bArr, byte[] bArr2) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.mode = mode;
        this.secretKey = getKeySpec(bArr);
        this.parameters = new CounterGCMParameterSpec(getAuthenticationTagSize() * 8, bArr2);
        cipher.init(getMode(mode), this.secretKey, this.parameters);
        this.initialized = true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: net.schmizz.sshj.common.SSHRuntimeException */
    public void update(byte[] bArr, int i, int i2) throws SSHRuntimeException {
        if (this.mode == Cipher.Mode.Decrypt) {
            i2 += getAuthenticationTagSize();
        }
        try {
            getInitializedCipherInstance().doFinal(bArr, i, i2, bArr, i);
            this.parameters.incrementCounter();
            this.initialized = false;
        } catch (GeneralSecurityException e) {
            throw new SSHRuntimeException("Error updating data through cipher", e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: net.schmizz.sshj.common.SSHRuntimeException */
    public void updateAAD(byte[] bArr, int i, int i2) throws SSHRuntimeException {
        try {
            getInitializedCipherInstance().updateAAD(bArr, i, i2);
        } catch (GeneralSecurityException e) {
            throw new SSHRuntimeException("Error updating data through cipher", e);
        }
    }
}
