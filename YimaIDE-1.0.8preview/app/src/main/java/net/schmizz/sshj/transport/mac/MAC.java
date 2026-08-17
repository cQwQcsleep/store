package net.schmizz.sshj.transport.mac;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface MAC {
    void doFinal(byte[] bArr, int i);

    byte[] doFinal();

    byte[] doFinal(byte[] bArr);

    int getBlockSize();

    void init(byte[] bArr);

    boolean isEtm();

    void update(long j);

    void update(byte[] bArr);

    void update(byte[] bArr, int i, int i2);
}
