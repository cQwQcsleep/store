package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class HarakaS256Digest extends HarakaSBase implements Digest {
    public HarakaS256Digest(HarakaSXof harakaSXof) {
        this.haraka256_rc = harakaSXof.haraka256_rc;
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[32];
        haraka256Perm(bArr2);
        HarakaSBase.xor(bArr2, 0, this.buffer, 0, bArr, i, 32);
        reset();
        return bArr.length;
    }

    public String getAlgorithmName() {
        return "HarakaS-256";
    }

    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.pqc.crypto.sphincsplus.HarakaSBase
    public void reset() {
        super.reset();
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 > 32 - i2) {
            w01.a("total input cannot be more than 32 bytes");
        } else {
            System.arraycopy(bArr, i, this.buffer, i3, i2);
            this.off += i2;
        }
    }

    public void update(byte b) {
        int i = this.off;
        if (i > 31) {
            w01.a("total input cannot be more than 32 bytes");
            return;
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }
}
