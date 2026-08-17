package jdk.internal.jrtfs;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class JrtUtils {
    private static final char EOL = 0;
    private static final String globMetaChars = "\\*?[{";
    private static final String regexMetaChars = ".^$+{[]|()";

    private JrtUtils() {
    }

    private static boolean isGlobMeta(char c) {
        return globMetaChars.indexOf(c) != -1;
    }

    private static boolean isRegexMeta(char c) {
        return regexMetaChars.indexOf(c) != -1;
    }

    private static char next(String str, int i) {
        return i < str.length() ? str.charAt(i) : EOL;
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
                                    char c = EOL;
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
                                            if (cCharAt == 0 || cCharAt == ']') {
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
}
