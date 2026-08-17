package net.schmizz.sshj.transport.digest;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Digest {
    byte[] digest();

    int getBlockSize();

    void init();

    void update(byte[] bArr);

    void update(byte[] bArr, int i, int i2);
}
