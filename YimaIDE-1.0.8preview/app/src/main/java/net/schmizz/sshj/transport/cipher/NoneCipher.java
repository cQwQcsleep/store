package net.schmizz.sshj.transport.cipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class NoneCipher implements Cipher {

    public static class Factory implements net.schmizz.sshj.common.Factory.Named<Cipher> {
        @Override // net.schmizz.sshj.common.Factory
        public Cipher create() {
            return new NoneCipher();
        }

        @Override // net.schmizz.sshj.common.Factory.Named
        public String getName() {
            return "none";
        }
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public int getAuthenticationTagSize() {
        return 0;
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public int getBlockSize() {
        return 8;
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public int getIVSize() {
        return 8;
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void init(Cipher.Mode mode, byte[] bArr, byte[] bArr2) {
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void setSequenceNumber(long j) {
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void update(byte[] bArr, int i, int i2) {
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void updateAAD(byte[] bArr) {
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void updateAAD(byte[] bArr, int i, int i2) {
    }

    @Override // net.schmizz.sshj.transport.cipher.Cipher
    public void updateWithAAD(byte[] bArr, int i, int i2, int i3) {
    }
}
