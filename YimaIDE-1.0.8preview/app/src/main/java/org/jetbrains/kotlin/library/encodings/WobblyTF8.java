package org.jetbrains.kotlin.library.encodings;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0014\u0010\u0011\u001a\u00020\u0010*\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H\u0082\u0002J\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H\u0082\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/library/encodings/WobblyTF8;", "", "()V", "EMPTY_BYTE_ARRAY", "", "EMPTY_STRING", "", "REPLACEMENT_CHAR", "", "decode", "array", "encode", "string", "isValidContinuation", "", "byteN", "", "readByteAsInt", "index", "set", "", "value", "", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WobblyTF8 {
    public static final WobblyTF8 INSTANCE = new WobblyTF8();
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private WobblyTF8() {
    }

    private final boolean isValidContinuation(int byteN) {
        return (byteN >>> 6) == 2;
    }

    private final int readByteAsInt(byte[] bArr, int i) {
        return bArr[i] & 255;
    }

    private final void set(byte[] bArr, int i, int i2) {
        bArr.getClass();
        bArr[i] = (byte) i2;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0075 A[PHI: r5
      0x0075: PHI (r5v3 int) = 
      (r5v0 int)
      (r5v0 int)
      (r5v0 int)
      (r5v0 int)
      (r5v5 int)
      (r5v5 int)
      (r5v0 int)
      (r5v0 int)
      (r5v0 int)
      (r5v7 int)
      (r5v7 int)
      (r5v8 int)
      (r5v8 int)
     binds: [B:13:0x0027, B:15:0x0031, B:20:0x0048, B:22:0x0052, B:24:0x0056, B:26:0x0060, B:61:0x0075, B:62:0x0075, B:54:0x0075, B:55:0x0075, B:56:0x0075, B:57:0x0075, B:58:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
    public final String decode(byte[] array) {
        int i;
        array.getClass();
        int length = array.length;
        if (length == 0) {
            return "";
        }
        char[] cArr = new char[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            int byteAsInt = readByteAsInt(array, i2);
            if ((byteAsInt & 128) == 0) {
                set(cArr, i3, byteAsInt);
                i3++;
                i2 = i4;
            } else if ((byteAsInt >>> 5) == 6) {
                if (i4 < length) {
                    int byteAsInt2 = readByteAsInt(array, i4);
                    if (isValidContinuation(byteAsInt2)) {
                        i2 += 2;
                        i = i3 + 1;
                        set(cArr, i3, ((byteAsInt & 31) << 6) | (byteAsInt2 & 63));
                    } else {
                        i2 = i4;
                        i = i3 + 1;
                        cArr[i3] = 65533;
                    }
                } else {
                    i2 = i4;
                    i = i3 + 1;
                    cArr[i3] = 65533;
                }
                i3 = i;
            } else {
                if ((byteAsInt >>> 4) == 14) {
                    if (i4 < length) {
                        int byteAsInt3 = readByteAsInt(array, i4);
                        if (isValidContinuation(byteAsInt3) && (i4 = i2 + 2) < length) {
                            int byteAsInt4 = readByteAsInt(array, i4);
                            if (isValidContinuation(byteAsInt4)) {
                                i2 += 3;
                                i = i3 + 1;
                                set(cArr, i3, ((byteAsInt & 15) << 12) | ((byteAsInt3 & 63) << 6) | (byteAsInt4 & 63));
                            }
                        }
                    }
                    i3 = i;
                } else if ((byteAsInt >>> 3) == 30 && i4 < length) {
                    int byteAsInt5 = readByteAsInt(array, i4);
                    if (isValidContinuation(byteAsInt5) && (i4 = i2 + 2) < length) {
                        int byteAsInt6 = readByteAsInt(array, i4);
                        if (isValidContinuation(byteAsInt6) && (i4 = i2 + 3) < length) {
                            int byteAsInt7 = readByteAsInt(array, i4);
                            if (isValidContinuation(byteAsInt7)) {
                                i2 += 4;
                                int i5 = ((byteAsInt & 7) << 18) | ((byteAsInt5 & 63) << 12) | ((byteAsInt6 & 63) << 6) | (byteAsInt7 & 63);
                                int i6 = i3 + 1;
                                cArr[i3] = Character.highSurrogate(i5);
                                i3 += 2;
                                cArr[i6] = Character.lowSurrogate(i5);
                            }
                        }
                    }
                }
                i2 = i4;
                i = i3 + 1;
                cArr[i3] = 65533;
                i3 = i;
            }
        }
        return length == i3 ? new String(cArr) : new String(cArr, 0, i3);
    }

    public final byte[] encode(String string) {
        string.getClass();
        int length = string.length();
        if (length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        int i = length * 3;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            char cCharAt = string.charAt(i2);
            if (Intrinsics.compare(cCharAt, 128) < 0) {
                set(bArr, i3, cCharAt);
                i3++;
            } else if (Intrinsics.compare(cCharAt, 2048) < 0) {
                int i5 = i3 + 1;
                set(bArr, i3, (cCharAt >>> 6) | BERTags.PRIVATE);
                i3 += 2;
                set(bArr, i5, (cCharAt & '?') | 128);
            } else {
                if (Character.isHighSurrogate(cCharAt) && i4 < length) {
                    char cCharAt2 = string.charAt(i4);
                    if (Character.isLowSurrogate(cCharAt2)) {
                        i2 += 2;
                        int codePoint = Character.toCodePoint(cCharAt, cCharAt2);
                        set(bArr, i3, (codePoint >>> 18) | 240);
                        set(bArr, i3 + 1, ((codePoint >>> 12) & 63) | 128);
                        int i6 = i3 + 3;
                        set(bArr, i3 + 2, ((codePoint >>> 6) & 63) | 128);
                        i3 += 4;
                        set(bArr, i6, (codePoint & 63) | 128);
                    }
                }
                set(bArr, i3, (cCharAt >>> '\f') | BERTags.FLAGS);
                int i7 = i3 + 2;
                set(bArr, i3 + 1, ((cCharAt >>> 6) & 63) | 128);
                i3 += 3;
                set(bArr, i7, (cCharAt & '?') | 128);
            }
            i2 = i4;
        }
        return i == i3 ? bArr : Arrays.copyOf(bArr, i3);
    }

    private final void set(char[] cArr, int i, int i2) {
        cArr.getClass();
        cArr[i] = (char) i2;
    }
}
