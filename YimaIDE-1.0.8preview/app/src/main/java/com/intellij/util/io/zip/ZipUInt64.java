package com.intellij.util.io.zip;

import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class ZipUInt64 {
    private final BigInteger value;

    public ZipUInt64(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public static byte[] getBytes(BigInteger bigInteger) {
        long jLongValue = bigInteger.longValue();
        byte[] bArr = {(byte) (255 & jLongValue), (byte) ((65280 & jLongValue) >> 8), (byte) ((16711680 & jLongValue) >> 16), (byte) ((4278190080L & jLongValue) >> 24), (byte) ((1095216660480L & jLongValue) >> 32), (byte) ((280375465082880L & jLongValue) >> 40), (byte) ((71776119061217280L & jLongValue) >> 48), (byte) ((jLongValue & 9151314442816847872L) >> 56)};
        if (bigInteger.testBit(63)) {
            bArr[7] = (byte) (bArr[7] | (-128));
        }
        return bArr;
    }

    public static long getLongValue(byte[] bArr, int i) {
        return getValue(bArr, i).longValue();
    }

    public static BigInteger getValue(byte[] bArr, int i) {
        int i2 = i + 7;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(((((long) bArr[i2]) << 56) & 9151314442816847872L) + ((((long) bArr[i + 6]) << 48) & 71776119061217280L) + ((((long) bArr[i + 5]) << 40) & 280375465082880L) + ((((long) bArr[i + 4]) << 32) & 1095216660480L) + ((((long) bArr[i + 3]) << 24) & 4278190080L) + ((((long) bArr[i + 2]) << 16) & 16711680) + ((((long) bArr[i + 1]) << 8) & 65280) + (((long) bArr[i]) & 255));
        return (bArr[i2] & (-128)) == -128 ? bigIntegerValueOf.setBit(63) : bigIntegerValueOf;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ZipUInt64) {
            return this.value.equals(((ZipUInt64) obj).getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "ZipInt64: " + this.value;
    }

    public long getLongValue() {
        return this.value.longValue();
    }

    public ZipUInt64(BigInteger bigInteger) {
        this.value = bigInteger;
    }

    public static long getLongValue(byte[] bArr) {
        return getLongValue(bArr, 0);
    }

    public ZipUInt64(long j) {
        this(BigInteger.valueOf(j));
    }

    public static byte[] getBytes(long j) {
        return getBytes(BigInteger.valueOf(j));
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public BigInteger getValue() {
        return this.value;
    }
}
