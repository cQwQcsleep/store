package org.bouncycastle.crypto.signers;

import defpackage.w01;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PlainDSAEncoding implements DSAEncoding {
    public static final PlainDSAEncoding INSTANCE = new PlainDSAEncoding();

    private void encodeValue(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, int i, int i2) {
        byte[] byteArray = checkValue(bigInteger, bigInteger2).toByteArray();
        int iMax = Math.max(0, byteArray.length - i2);
        int length = byteArray.length - iMax;
        int i3 = (i2 - length) + i;
        Arrays.fill(bArr, i, i3, (byte) 0);
        System.arraycopy(byteArray, iMax, bArr, i3, length);
    }

    public BigInteger checkValue(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger2.signum() >= 0 && bigInteger2.compareTo(bigInteger) < 0) {
            return bigInteger2;
        }
        w01.a("Value out of range");
        return null;
    }

    @Override // org.bouncycastle.crypto.signers.DSAEncoding
    public BigInteger[] decode(BigInteger bigInteger, byte[] bArr) {
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(bigInteger);
        if (bArr.length == unsignedByteLength * 2) {
            return new BigInteger[]{decodeValue(bigInteger, bArr, 0, unsignedByteLength), decodeValue(bigInteger, bArr, unsignedByteLength, unsignedByteLength)};
        }
        w01.a("Encoding has incorrect length");
        return null;
    }

    public BigInteger decodeValue(BigInteger bigInteger, byte[] bArr, int i, int i2) {
        return checkValue(bigInteger, new BigInteger(1, Arrays.copyOfRange(bArr, i, i2 + i)));
    }

    @Override // org.bouncycastle.crypto.signers.DSAEncoding
    public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(bigInteger);
        byte[] bArr = new byte[unsignedByteLength * 2];
        encodeValue(bigInteger, bigInteger2, bArr, 0, unsignedByteLength);
        encodeValue(bigInteger, bigInteger3, bArr, unsignedByteLength, unsignedByteLength);
        return bArr;
    }
}
