package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ALDER32 extends Checksum {
    private static final int MOD_ADLER = 65521;
    private int a = 1;
    private int b = 0;

    @Override // com.reandroid.utils.Checksum
    public long getValue() {
        return ((long) (this.a | (this.b << 16))) & 4294967295L;
    }

    @Override // com.reandroid.utils.Checksum
    public void reset() {
        this.a = 1;
        this.b = 0;
    }

    public String toString() {
        return HexUtil.toHex8(getValue());
    }

    @Override // com.reandroid.utils.Checksum
    public void update(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            int i3 = this.a;
            int i4 = this.b;
            int i5 = i2 + i;
            while (i < i5) {
                i3 = (i3 + (bArr[i] & 255)) % MOD_ADLER;
                i4 = (i4 + i3) % MOD_ADLER;
                i++;
            }
            this.a = i3;
            this.b = i4;
        }
    }
}
