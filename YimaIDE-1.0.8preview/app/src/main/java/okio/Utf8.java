package okio;

import defpackage.t1f;
import defpackage.v1f;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.eac.CertificateHolderAuthorization;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0080\b\u001a4\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"HIGH_SURROGATE_HEADER", HttpUrl.FRAGMENT_ENCODE_SET, "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", HttpUrl.FRAGMENT_ENCODE_SET, "REPLACEMENT_CHARACTER", HttpUrl.FRAGMENT_ENCODE_SET, "REPLACEMENT_CODE_POINT", "isIsoControl", HttpUrl.FRAGMENT_ENCODE_SET, "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", HttpUrl.FRAGMENT_ENCODE_SET, "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", HttpUrl.FRAGMENT_ENCODE_SET, "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", HttpUrl.FRAGMENT_ENCODE_SET, "processUtf8CodePoints", "utf8Size", HttpUrl.FRAGMENT_ENCODE_SET, "size", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        if (i < 0 || i >= 32) {
            return 127 <= i && i < 160;
        }
        return true;
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final int process2Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        bArr.getClass();
        function1.getClass();
        int i3 = i + 1;
        if (i2 <= i3) {
            function1.invoke(numValueOf);
            return 1;
        }
        byte b = bArr[i];
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            function1.invoke(numValueOf);
            return 1;
        }
        int i4 = (b2 ^ 3968) ^ (b << 6);
        if (i4 < 128) {
            function1.invoke(numValueOf);
            return 2;
        }
        function1.invoke(Integer.valueOf(i4));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        bArr.getClass();
        function1.getClass();
        int i3 = i + 2;
        if (i2 <= i3) {
            function1.invoke(numValueOf);
            int i4 = i + 1;
            return (i2 <= i4 || (bArr[i4] & 192) != 128) ? 1 : 2;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) != 128) {
            function1.invoke(numValueOf);
            return 1;
        }
        byte b3 = bArr[i3];
        if ((b3 & 192) != 128) {
            function1.invoke(numValueOf);
            return 2;
        }
        int i5 = ((b3 ^ (-123008)) ^ (b2 << 6)) ^ (b << 12);
        if (i5 < 2048) {
            function1.invoke(numValueOf);
            return 3;
        }
        if (55296 > i5 || i5 >= 57344) {
            function1.invoke(Integer.valueOf(i5));
            return 3;
        }
        function1.invoke(numValueOf);
        return 3;
    }

    public static final int process4Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        bArr.getClass();
        function1.getClass();
        int i3 = i + 3;
        if (i2 <= i3) {
            function1.invoke(numValueOf);
            int i4 = i + 1;
            if (i2 <= i4 || (bArr[i4] & 192) != 128) {
                return 1;
            }
            int i5 = i + 2;
            return (i2 <= i5 || (bArr[i5] & 192) != 128) ? 2 : 3;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) != 128) {
            function1.invoke(numValueOf);
            return 1;
        }
        byte b3 = bArr[i + 2];
        if ((b3 & 192) != 128) {
            function1.invoke(numValueOf);
            return 2;
        }
        byte b4 = bArr[i3];
        if ((b4 & 192) != 128) {
            function1.invoke(numValueOf);
            return 3;
        }
        int i6 = (((b4 ^ 3678080) ^ (b3 << 6)) ^ (b2 << 12)) ^ (b << 18);
        if (i6 > 1114111) {
            function1.invoke(numValueOf);
            return 4;
        }
        if (55296 <= i6 && i6 < 57344) {
            function1.invoke(numValueOf);
            return 4;
        }
        if (i6 < 65536) {
            function1.invoke(numValueOf);
            return 4;
        }
        function1.invoke(Integer.valueOf(i6));
        return 4;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0040  */
    public static final void processUtf16Chars(byte[] bArr, int i, int i2, Function1<? super Character, Unit> function1) {
        Character chValueOf = Character.valueOf(REPLACEMENT_CHARACTER);
        bArr.getClass();
        function1.getClass();
        while (i < i2) {
            byte b = bArr[i];
            if (b >= 0) {
                function1.invoke(Character.valueOf((char) b));
                i++;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 < 0) {
                        break;
                    }
                    i++;
                    function1.invoke(Character.valueOf((char) b2));
                }
            } else {
                int i3 = 2;
                if ((b >> 5) == -2) {
                    int i4 = i + 1;
                    if (i2 > i4) {
                        byte b3 = bArr[i4];
                        if ((b3 & 192) == 128) {
                            int i5 = (b << 6) ^ (b3 ^ 3968);
                            if (i5 < 128) {
                                function1.invoke(chValueOf);
                            } else {
                                function1.invoke(Character.valueOf((char) i5));
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                        i += i3;
                    }
                    function1.invoke(chValueOf);
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                    i += i3;
                } else if ((b >> 4) == -2) {
                    int i6 = i + 2;
                    if (i2 <= i6) {
                        function1.invoke(chValueOf);
                        Unit unit3 = Unit.INSTANCE;
                        int i7 = i + 1;
                        if (i2 <= i7 || (bArr[i7] & 192) != 128) {
                            i3 = 1;
                        }
                    } else {
                        byte b4 = bArr[i + 1];
                        if ((b4 & 192) == 128) {
                            byte b5 = bArr[i6];
                            if ((b5 & 192) == 128) {
                                int i8 = (b << 12) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                                if (i8 >= 2048 && (55296 > i8 || i8 >= 57344)) {
                                    function1.invoke(Character.valueOf((char) i8));
                                } else {
                                    function1.invoke(chValueOf);
                                }
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                function1.invoke(chValueOf);
                                Unit unit5 = Unit.INSTANCE;
                            }
                        } else {
                            function1.invoke(chValueOf);
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else if ((b >> 3) == -2) {
                    int i9 = i + 3;
                    if (i2 <= i9) {
                        function1.invoke(chValueOf);
                        Unit unit7 = Unit.INSTANCE;
                        int i10 = i + 1;
                        if (i2 <= i10 || (bArr[i10] & 192) != 128) {
                            i3 = 1;
                        } else {
                            int i11 = i + 2;
                            if (i2 > i11 && (bArr[i11] & 192) == 128) {
                                i3 = 3;
                            }
                        }
                    } else {
                        byte b6 = bArr[i + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i + 2];
                            if ((b7 & 192) == 128) {
                                byte b8 = bArr[i9];
                                if ((b8 & 192) == 128) {
                                    int i12 = (b << 18) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12));
                                    if (i12 <= 1114111 && ((55296 > i12 || i12 >= 57344) && i12 >= 65536 && i12 != 65533)) {
                                        function1.invoke(Character.valueOf((char) ((i12 >>> 10) + HIGH_SURROGATE_HEADER)));
                                        function1.invoke(Character.valueOf((char) ((i12 & 1023) + LOG_SURROGATE_HEADER)));
                                    } else {
                                        function1.invoke(chValueOf);
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    function1.invoke(chValueOf);
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                function1.invoke(chValueOf);
                                Unit unit10 = Unit.INSTANCE;
                            }
                        } else {
                            function1.invoke(chValueOf);
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else {
                    function1.invoke(chValueOf);
                    i++;
                }
            }
        }
    }

    public static final void processUtf8Bytes(String str, int i, int i2, Function1<? super Byte, Unit> function1) {
        int i3;
        char cCharAt;
        str.getClass();
        function1.getClass();
        while (i < i2) {
            char cCharAt2 = str.charAt(i);
            if (Intrinsics.compare(cCharAt2, 128) < 0) {
                function1.invoke(Byte.valueOf((byte) cCharAt2));
                i++;
                while (i < i2 && Intrinsics.compare(str.charAt(i), 128) < 0) {
                    function1.invoke(Byte.valueOf((byte) str.charAt(i)));
                    i++;
                }
            } else {
                if (Intrinsics.compare(cCharAt2, PKIFailureInfo.wrongIntegrity) < 0) {
                    function1.invoke(Byte.valueOf((byte) ((cCharAt2 >> 6) | CertificateHolderAuthorization.CVCA)));
                    function1.invoke(Byte.valueOf((byte) ((cCharAt2 & '?') | 128)));
                } else if (55296 > cCharAt2 || cCharAt2 >= 57344) {
                    function1.invoke(Byte.valueOf((byte) ((cCharAt2 >> '\f') | 224)));
                    function1.invoke(Byte.valueOf((byte) (((cCharAt2 >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((cCharAt2 & '?') | 128)));
                } else if (Intrinsics.compare(cCharAt2, 56319) > 0 || i2 <= (i3 = i + 1) || 56320 > (cCharAt = str.charAt(i3)) || cCharAt >= 57344) {
                    function1.invoke(Byte.valueOf(REPLACEMENT_BYTE));
                } else {
                    int iCharAt = ((cCharAt2 << '\n') + str.charAt(i3)) - 56613888;
                    function1.invoke(Byte.valueOf((byte) ((iCharAt >> 18) | 240)));
                    function1.invoke(Byte.valueOf((byte) (((iCharAt >> 12) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) (((iCharAt >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((iCharAt & 63) | 128)));
                    i += 2;
                }
                i++;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x003e  */
    public static final void processUtf8CodePoints(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        bArr.getClass();
        function1.getClass();
        while (i < i2) {
            byte b = bArr[i];
            if (b >= 0) {
                function1.invoke(Integer.valueOf(b));
                i++;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 < 0) {
                        break;
                    }
                    i++;
                    function1.invoke(Integer.valueOf(b2));
                }
            } else {
                int i3 = 2;
                if ((b >> 5) == -2) {
                    int i4 = i + 1;
                    if (i2 > i4) {
                        byte b3 = bArr[i4];
                        if ((b3 & 192) == 128) {
                            int i5 = (b << 6) ^ (b3 ^ 3968);
                            if (i5 < 128) {
                                function1.invoke(numValueOf);
                            } else {
                                function1.invoke(Integer.valueOf(i5));
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                        i += i3;
                    }
                    function1.invoke(numValueOf);
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                    i += i3;
                } else if ((b >> 4) == -2) {
                    int i6 = i + 2;
                    if (i2 <= i6) {
                        function1.invoke(numValueOf);
                        Unit unit3 = Unit.INSTANCE;
                        int i7 = i + 1;
                        if (i2 <= i7 || (bArr[i7] & 192) != 128) {
                            i3 = 1;
                        }
                    } else {
                        byte b4 = bArr[i + 1];
                        if ((b4 & 192) == 128) {
                            byte b5 = bArr[i6];
                            if ((b5 & 192) == 128) {
                                int i8 = (b << 12) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                                if (i8 >= 2048 && (55296 > i8 || i8 >= 57344)) {
                                    function1.invoke(Integer.valueOf(i8));
                                } else {
                                    function1.invoke(numValueOf);
                                }
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                function1.invoke(numValueOf);
                                Unit unit5 = Unit.INSTANCE;
                            }
                        } else {
                            function1.invoke(numValueOf);
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else if ((b >> 3) == -2) {
                    int i9 = i + 3;
                    if (i2 <= i9) {
                        function1.invoke(numValueOf);
                        Unit unit7 = Unit.INSTANCE;
                        int i10 = i + 1;
                        if (i2 <= i10 || (bArr[i10] & 192) != 128) {
                            i3 = 1;
                        } else {
                            int i11 = i + 2;
                            if (i2 > i11 && (bArr[i11] & 192) == 128) {
                                i3 = 3;
                            }
                        }
                    } else {
                        byte b6 = bArr[i + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i + 2];
                            if ((b7 & 192) == 128) {
                                byte b8 = bArr[i9];
                                if ((b8 & 192) == 128) {
                                    int i12 = (b << 18) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12));
                                    if (i12 <= 1114111 && ((55296 > i12 || i12 >= 57344) && i12 >= 65536)) {
                                        function1.invoke(Integer.valueOf(i12));
                                    } else {
                                        function1.invoke(numValueOf);
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    function1.invoke(numValueOf);
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                function1.invoke(numValueOf);
                                Unit unit10 = Unit.INSTANCE;
                            }
                        } else {
                            function1.invoke(numValueOf);
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else {
                    function1.invoke(numValueOf);
                    i++;
                }
            }
        }
    }

    public static final long size(String str, int i, int i2) {
        long j;
        str.getClass();
        long j2 = 0;
        if (i < 0) {
            v1f.a("beginIndex < 0: ", i);
            return 0L;
        }
        if (i2 < i) {
            t1f.a("endIndex < beginIndex: ", i2, " < ", i);
            return 0L;
        }
        if (i2 > str.length()) {
            t1f.a("endIndex > string.length: ", i2, " > ", str.length());
            return 0L;
        }
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                j2++;
            } else {
                if (cCharAt < 2048) {
                    j = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    j = 3;
                } else {
                    int i3 = i + 1;
                    char cCharAt2 = i3 < i2 ? str.charAt(i3) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j2++;
                        i = i3;
                    } else {
                        j2 += 4;
                        i += 2;
                    }
                }
                j2 += j;
            }
            i++;
        }
        return j2;
    }

    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String str, int i) {
        str.getClass();
        return size$default(str, i, 0, 2, null);
    }

    public static final long size(String str) {
        str.getClass();
        return size$default(str, 0, 0, 3, null);
    }
}
