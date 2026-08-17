package com.sun.nio.zipfs;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ZipUtils {
    private static char EOL = 0;
    private static final long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;
    private static final String globMetaChars = "\\*?[{";
    private static final String regexMetaChars = ".^$+{[]|()";

    public static long dosToJavaTime(long j) {
        return new Date((int) (((j >> 25) & 127) + 80), (int) (((j >> 21) & 15) - 1), (int) ((j >> 16) & 31), (int) ((j >> 11) & 31), (int) ((j >> 5) & 63), (int) ((j << 1) & 62)).getTime();
    }

    private static boolean isGlobMeta(char c) {
        return globMetaChars.indexOf(c) != -1;
    }

    private static boolean isRegexMeta(char c) {
        return regexMetaChars.indexOf(c) != -1;
    }

    public static long javaToDosTime(long j) {
        Date date = new Date(j);
        int year = date.getYear();
        if (year + WinError.RPC_S_INVALID_OBJECT < 1980) {
            return 2162688L;
        }
        return ((year - 80) << 25) | ((date.getMonth() + 1) << 21) | (date.getDate() << 16) | (date.getHours() << 11) | (date.getMinutes() << 5) | (date.getSeconds() >> 1);
    }

    public static final long javaToUnixTime(long j) {
        return j / 1000;
    }

    public static final long javaToWinTime(long j) {
        return (TimeUnit.MICROSECONDS.convert(j, TimeUnit.MILLISECONDS) - WINDOWS_EPOCH_IN_MICROSECONDS) * 10;
    }

    private static char next(String str, int i) {
        return i < str.length() ? str.charAt(i) : EOL;
    }

    public static byte[] toDirectoryPath(byte[] bArr) {
        if (bArr.length == 0 || bArr[bArr.length - 1] == 47) {
            return bArr;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + 1);
        bArrCopyOf[bArrCopyOf.length - 1] = 47;
        return bArrCopyOf;
    }

    public static String toRegexPattern(String str) {
        int i;
        StringBuilder sb = new StringBuilder("^");
        int i2 = 0;
        while (true) {
            boolean z = false;
            while (true) {
                if (i2 >= str.length()) {
                    if (z) {
                        throw new PatternSyntaxException("Missing '}", str, i2 - 1);
                    }
                    sb.append('$');
                    return sb.toString();
                }
                i = i2 + 1;
                char cCharAt = str.charAt(i2);
                if (cCharAt != '*') {
                    if (cCharAt != ',') {
                        if (cCharAt == '/') {
                            sb.append(cCharAt);
                        } else if (cCharAt == '?') {
                            sb.append("[^/]");
                        } else if (cCharAt != '{') {
                            if (cCharAt != '}') {
                                if (cCharAt == '[') {
                                    sb.append("[[^/]&&[");
                                    if (next(str, i) == '^') {
                                        sb.append("\\^");
                                        i2 += 2;
                                    } else {
                                        if (next(str, i) == '!') {
                                            sb.append('^');
                                            i = i2 + 2;
                                        }
                                        if (next(str, i) == '-') {
                                            sb.append(LocaleUtility.IETF_SEPARATOR);
                                            i2 = i + 1;
                                        } else {
                                            i2 = i;
                                        }
                                    }
                                    boolean z2 = false;
                                    char c = 0;
                                    while (i2 < str.length()) {
                                        int i3 = i2 + 1;
                                        char cCharAt2 = str.charAt(i2);
                                        if (cCharAt2 == ']') {
                                            i2 = i3;
                                            cCharAt = cCharAt2;
                                            break;
                                        }
                                        if (cCharAt2 == '/') {
                                            throw new PatternSyntaxException("Explicit 'name separator' in class", str, i2);
                                        }
                                        if (cCharAt2 == '\\' || cCharAt2 == '[' || (cCharAt2 == '&' && next(str, i3) == '&')) {
                                            sb.append('\\');
                                        }
                                        sb.append(cCharAt2);
                                        if (cCharAt2 != '-') {
                                            z2 = true;
                                            i2 = i3;
                                            cCharAt = cCharAt2;
                                            c = cCharAt;
                                        } else {
                                            if (!z2) {
                                                throw new PatternSyntaxException("Invalid range", str, i2);
                                            }
                                            int i4 = i2 + 2;
                                            cCharAt = next(str, i3);
                                            if (cCharAt == EOL || cCharAt == ']') {
                                                i2 = i4;
                                                break;
                                            }
                                            if (cCharAt < c) {
                                                throw new PatternSyntaxException("Invalid range", str, i2 - 1);
                                            }
                                            sb.append(cCharAt);
                                            i2 = i4;
                                            z2 = false;
                                        }
                                    }
                                    if (cCharAt != ']') {
                                        throw new PatternSyntaxException("Missing ']", str, i2 - 1);
                                    }
                                    sb.append("]]");
                                } else if (cCharAt != '\\') {
                                    if (isRegexMeta(cCharAt)) {
                                        sb.append('\\');
                                    }
                                    sb.append(cCharAt);
                                } else {
                                    if (i == str.length()) {
                                        throw new PatternSyntaxException("No character to escape", str, i2);
                                    }
                                    i2 += 2;
                                    char cCharAt3 = str.charAt(i);
                                    if (isGlobMeta(cCharAt3) || isRegexMeta(cCharAt3)) {
                                        sb.append('\\');
                                    }
                                    sb.append(cCharAt3);
                                }
                            } else {
                                if (z) {
                                    break;
                                }
                                sb.append('}');
                            }
                        } else {
                            if (z) {
                                throw new PatternSyntaxException("Cannot nest groups", str, i2);
                            }
                            sb.append("(?:(?:");
                            i2 = i;
                            z = true;
                        }
                    } else if (z) {
                        sb.append(")|(?:");
                    } else {
                        sb.append(',');
                    }
                    i2 = i;
                } else if (next(str, i) == '*') {
                    sb.append(".*");
                    i2 += 2;
                } else {
                    sb.append("[^/]*");
                    i2 = i;
                }
            }
            sb.append("))");
            i2 = i;
        }
    }

    public static final long unixToJavaTime(long j) {
        return TimeUnit.MILLISECONDS.convert(j, TimeUnit.SECONDS);
    }

    public static final long winToJavaTime(long j) {
        return ((j / 10) + WINDOWS_EPOCH_IN_MICROSECONDS) / 1000;
    }

    public static void writeBytes(OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(bArr, 0, bArr.length);
    }

    public static void writeInt(OutputStream outputStream, long j) throws IOException {
        outputStream.write((int) (j & 255));
        outputStream.write((int) ((j >>> 8) & 255));
        outputStream.write((int) ((j >>> 16) & 255));
        outputStream.write((int) ((j >>> 24) & 255));
    }

    public static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((int) (j & 255));
        outputStream.write((int) ((j >>> 8) & 255));
        outputStream.write((int) ((j >>> 16) & 255));
        outputStream.write((int) ((j >>> 24) & 255));
        outputStream.write((int) ((j >>> 32) & 255));
        outputStream.write((int) ((j >>> 40) & 255));
        outputStream.write((int) ((j >>> 48) & 255));
        outputStream.write((int) ((j >>> 56) & 255));
    }

    public static void writeShort(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >>> 8) & 255);
    }

    public static void writeBytes(OutputStream outputStream, byte[] bArr, int i, int i2) throws IOException {
        outputStream.write(bArr, i, i2);
    }
}
