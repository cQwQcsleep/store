package org.bouncycastle.pqc.crypto.frodo;

import io.github.rosemoe.sora.widget.CodeEditor;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class FrodoMatrixGenerator {
    int n;
    int q;

    public static class Aes128MatrixGenerator extends FrodoMatrixGenerator {
        public Aes128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        public short[] genMatrix(byte[] bArr) {
            int i = this.n;
            short[] sArr = new short[i * i];
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            AESEngine aESEngine = new AESEngine();
            aESEngine.init(true, new KeyParameter(bArr));
            for (int i2 = 0; i2 < this.n; i2++) {
                Pack.shortToLittleEndian((short) i2, bArr2, 0);
                for (int i3 = 0; i3 < this.n; i3 += 8) {
                    Pack.shortToLittleEndian((short) i3, bArr2, 2);
                    aESEngine.processBlock(bArr2, 0, bArr3, 0);
                    for (int i4 = 0; i4 < 8; i4++) {
                        sArr[(this.n * i2) + i3 + i4] = (short) (Pack.littleEndianToShort(bArr3, i4 * 2) & (this.q - 1));
                    }
                }
            }
            return sArr;
        }
    }

    public static class Shake128MatrixGenerator extends FrodoMatrixGenerator {
        public Shake128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        public short[] genMatrix(byte[] bArr) {
            int i = this.n;
            short[] sArr = new short[i * i];
            int i2 = (i * 16) / 8;
            byte[] bArr2 = new byte[i2];
            int length = bArr.length + 2;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, 0, bArr3, 2, bArr.length);
            SHAKEDigest sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
            for (short s = 0; s < this.n; s = (short) (s + 1)) {
                Pack.shortToLittleEndian(s, bArr3, 0);
                sHAKEDigest.update(bArr3, 0, length);
                sHAKEDigest.doFinal(bArr2, 0, i2);
                short s2 = 0;
                while (true) {
                    int i3 = this.n;
                    if (s2 < i3) {
                        sArr[(i3 * s) + s2] = (short) (Pack.littleEndianToShort(bArr2, s2 * 2) & (this.q - 1));
                        s2 = (short) (s2 + 1);
                    }
                }
            }
            return sArr;
        }
    }

    public FrodoMatrixGenerator(int i, int i2) {
        this.n = i;
        this.q = i2;
    }

    public abstract short[] genMatrix(byte[] bArr);
}
