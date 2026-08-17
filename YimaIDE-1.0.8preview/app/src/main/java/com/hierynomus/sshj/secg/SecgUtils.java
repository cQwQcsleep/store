package com.hierynomus.sshj.secg;

import java.math.BigInteger;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import net.schmizz.sshj.common.SSHRuntimeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SecgUtils {
    public static ECPoint getDecoded(byte[] bArr, EllipticCurve ellipticCurve) {
        int elementSize = getElementSize(ellipticCurve);
        if (bArr.length != (elementSize * 2) + 1 || bArr[0] != 4) {
            throw new SSHRuntimeException("Invalid 'f' for Elliptic Curve " + ellipticCurve.toString());
        }
        byte[] bArr2 = new byte[elementSize];
        byte[] bArr3 = new byte[elementSize];
        System.arraycopy(bArr, 1, bArr2, 0, elementSize);
        System.arraycopy(bArr, elementSize + 1, bArr3, 0, elementSize);
        return new ECPoint(new BigInteger(1, bArr2), new BigInteger(1, bArr3));
    }

    private static int getElementSize(EllipticCurve ellipticCurve) {
        return (ellipticCurve.getField().getFieldSize() + 7) / 8;
    }

    public static byte[] getEncoded(ECPoint eCPoint, EllipticCurve ellipticCurve) {
        int elementSize = getElementSize(ellipticCurve);
        int i = (elementSize * 2) + 1;
        byte[] bArr = new byte[i];
        bArr[0] = 4;
        byte[] bArrStripLeadingZeroes = stripLeadingZeroes(eCPoint.getAffineX().toByteArray());
        byte[] bArrStripLeadingZeroes2 = stripLeadingZeroes(eCPoint.getAffineY().toByteArray());
        System.arraycopy(bArrStripLeadingZeroes, 0, bArr, (elementSize + 1) - bArrStripLeadingZeroes.length, bArrStripLeadingZeroes.length);
        System.arraycopy(bArrStripLeadingZeroes2, 0, bArr, i - bArrStripLeadingZeroes2.length, bArrStripLeadingZeroes2.length);
        return bArr;
    }

    private static byte[] stripLeadingZeroes(byte[] bArr) {
        int i = 0;
        while (bArr[i] == 0) {
            i++;
        }
        return Arrays.copyOfRange(bArr, i, bArr.length);
    }
}
