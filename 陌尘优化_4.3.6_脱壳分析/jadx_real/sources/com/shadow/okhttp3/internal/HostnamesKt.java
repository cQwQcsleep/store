package com.shadow.okhttp3.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okio.Buffer;
import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class HostnamesKt {
    private static final boolean containsInvalidHostnameAsciiCodes(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (CloseableKt.compare(cCharAt, 31) <= 0 || CloseableKt.compare(cCharAt, 127) >= 0 || StringsKt.h(" #%/:?@[\\]", cCharAt, 0, false, 6) != -1) {
                return true;
            }
        }
        return false;
    }

    private static final boolean decodeIpv4Suffix(String str, int i, int i2, byte[] bArr, int i3) {
        int i4 = i3;
        while (i < i2) {
            if (i4 == bArr.length) {
                return false;
            }
            if (i4 != i3) {
                if (str.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int i5 = i;
            int i6 = 0;
            while (i5 < i2) {
                char cCharAt = str.charAt(i5);
                if (CloseableKt.compare(cCharAt, 48) < 0 || CloseableKt.compare(cCharAt, 57) > 0) {
                    break;
                }
                if ((i6 == 0 && i != i5) || (i6 = ((i6 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i5++;
            }
            if (i5 - i == 0) {
                return false;
            }
            bArr[i4] = (byte) i6;
            i4++;
            i = i5;
        }
        return i4 == i3 + 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0077, code lost:
    
        if (r4 == 16) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0079, code lost:
    
        if (r5 != (-1)) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007b, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007c, code lost:
    
        r11 = r4 - r5;
        java.lang.System.arraycopy(r1, r5, r1, 16 - r11, r11);
        java.util.Arrays.fill(r1, r5, (16 - r4) + r5, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008c, code lost:
    
        return java.net.InetAddress.getByAddress(r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final InetAddress decodeIpv6(String str, int i, int i2) {
        int i3;
        byte[] bArr = new byte[16];
        int i4 = 0;
        int i5 = -1;
        int i6 = -1;
        while (true) {
            if (i < i2) {
                if (i4 != 16) {
                    int i7 = i + 2;
                    if (i7 <= i2 && StringsKt.q(str, "::", i, false)) {
                        if (i5 == -1) {
                            i4 += 2;
                            i5 = i4;
                            if (i7 != i2) {
                                i6 = i7;
                                i = i6;
                                int i8 = 0;
                                while (i < i2) {
                                }
                                i3 = i - i6;
                                if (i3 == 0) {
                                    break;
                                }
                                break;
                                break;
                            }
                            break;
                        }
                        return null;
                    }
                    if (i4 != 0) {
                        if (StringsKt.q(str, ":", i, false)) {
                            i++;
                        } else {
                            if (!StringsKt.q(str, ".", i, false) || !decodeIpv4Suffix(str, i6, i2, bArr, i4 - 2)) {
                                return null;
                            }
                            i4 += 2;
                        }
                    }
                    i6 = i;
                    i = i6;
                    int i82 = 0;
                    while (i < i2) {
                        int hexDigit = Util.parseHexDigit(str.charAt(i));
                        if (hexDigit == -1) {
                            break;
                        }
                        i82 = (i82 << 4) + hexDigit;
                        i++;
                    }
                    i3 = i - i6;
                    if (i3 == 0 || i3 > 4) {
                        break;
                    }
                    int i9 = i4 + 1;
                    bArr[i4] = (byte) ((i82 >>> 8) & 255);
                    i4 += 2;
                    bArr[i9] = (byte) (i82 & 255);
                } else {
                    return null;
                }
            } else {
                break;
            }
        }
        return null;
    }

    private static final String inet6AddressToAscii(byte[] bArr) {
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            int i5 = i3;
            while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                i5 += 2;
            }
            int i6 = i5 - i3;
            if (i6 > i4 && i6 >= 4) {
                i = i3;
                i4 = i6;
            }
            i3 = i5 + 2;
        }
        Buffer buffer = new Buffer();
        while (i2 < bArr.length) {
            if (i2 == i) {
                buffer.writeByte(58);
                i2 += i4;
                if (i2 == 16) {
                    buffer.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    buffer.writeByte(58);
                }
                buffer.writeHexadecimalUnsignedLong((Util.and(bArr[i2], 255) << 8) | Util.and(bArr[i2 + 1], 255));
                i2 += 2;
            }
        }
        return buffer.readUtf8();
    }

    public static final String toCanonicalHost(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (StringsKt.e(str, ":")) {
            InetAddress inetAddressDecodeIpv6 = (StringsKt.r(str, "[", false) && StringsKt.f(str, "]", false)) ? decodeIpv6(str, 1, str.length() - 1) : decodeIpv6(str, 0, str.length());
            if (inetAddressDecodeIpv6 == null) {
                return null;
            }
            byte[] address = inetAddressDecodeIpv6.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            if (address.length == 4) {
                return inetAddressDecodeIpv6.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        try {
            String ascii = IDN.toASCII(str);
            CloseableKt.checkNotNullExpressionValue(ascii, "toASCII(host)");
            Locale locale = Locale.US;
            CloseableKt.checkNotNullExpressionValue(locale, "US");
            String lowerCase = ascii.toLowerCase(locale);
            CloseableKt.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (lowerCase.length() == 0) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                return null;
            }
            return lowerCase;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
