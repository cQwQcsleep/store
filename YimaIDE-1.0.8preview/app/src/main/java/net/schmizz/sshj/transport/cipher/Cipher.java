package net.schmizz.sshj.transport.cipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Cipher {

    public enum Mode {
        Encrypt,
        Decrypt
    }

    int getAuthenticationTagSize();

    int getBlockSize();

    int getIVSize();

    void init(Mode mode, byte[] bArr, byte[] bArr2);

    void setSequenceNumber(long j);

    void update(byte[] bArr, int i, int i2);

    void updateAAD(byte[] bArr);

    void updateAAD(byte[] bArr, int i, int i2);

    void updateWithAAD(byte[] bArr, int i, int i2, int i3);
}
