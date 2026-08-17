package nbjavac;

import com.sun.org.apache.xml.internal.serializer.CharInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import nbjavac.StringWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringWrapper {
    public static /* synthetic */ int a(String str) {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        if (i == str.length()) {
            return Integer.MAX_VALUE;
        }
        return i;
    }

    public static /* synthetic */ String b(int i, String str) {
        if (str.length() <= i) {
            return "";
        }
        String strSubstring = str.substring(i);
        int length = strSubstring.length() - 1;
        while (length >= 0 && Character.isWhitespace(strSubstring.charAt(length))) {
            length--;
        }
        return strSubstring.substring(0, length + 1);
    }

    public static boolean isBlank(String str) {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i == str.length();
    }

    private static String[] lines(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(10, i);
            if (iIndexOf == -1) {
                arrayList.add(str.substring(i));
                return (String[]) arrayList.toArray(new String[0]);
            }
            arrayList.add(str.substring(i, iIndexOf));
            i = iIndexOf + 1;
        }
    }

    public static String stripIndent(String str) {
        String[] strArrLines = lines(str);
        final int iOrElse = Arrays.stream(strArrLines).mapToInt(new ToIntFunction() { // from class: erd
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return StringWrapper.a((String) obj);
            }
        }).min().orElse(Integer.MAX_VALUE);
        if (strArrLines.length > 0 && isBlank(strArrLines[strArrLines.length - 1])) {
            iOrElse = Math.min(iOrElse, strArrLines[strArrLines.length - 1].length());
        }
        return (String) Arrays.stream(strArrLines).map(new Function() { // from class: frd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StringWrapper.b(iOrElse, (String) obj);
            }
        }).collect(Collectors.joining("\n"));
    }

    public static String stripTrailing(String str) {
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    public static String translateEscapes(String str) {
        if (str.isEmpty()) {
            return "";
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char c = charArray[i];
            if (c == '\\') {
                if (i3 < length) {
                    i += 2;
                    c = charArray[i3];
                } else {
                    c = 0;
                    i = i3;
                }
                if (c == '\n') {
                    continue;
                } else if (c != '\"' && c != '\'' && c != '\\') {
                    if (c == 'b') {
                        c = '\b';
                    } else if (c == 'f') {
                        c = '\f';
                    } else if (c != 'n') {
                        switch (c) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                                int iMin = Integer.min((c <= '3' ? 2 : 1) + i, length);
                                int i4 = c - '0';
                                while (i < iMin) {
                                    char c2 = charArray[i];
                                    if (c2 < '0' || '7' < c2) {
                                        c = (char) i4;
                                    } else {
                                        i++;
                                        i4 = (i4 << 3) | (c2 - '0');
                                    }
                                    break;
                                }
                                c = (char) i4;
                                break;
                            default:
                                switch (c) {
                                    case 'r':
                                        c = CharInfo.S_CARRIAGERETURN;
                                        break;
                                    case 's':
                                        c = ' ';
                                        break;
                                    case 't':
                                        c = '\t';
                                        break;
                                    default:
                                        drd.a("Invalid escape sequence: \\%c \\\\u%04X", new Object[]{Character.valueOf(c), Integer.valueOf(c)});
                                        return null;
                                }
                                break;
                        }
                    } else {
                        c = '\n';
                    }
                }
            } else {
                i = i3;
            }
            charArray[i2] = c;
            i2++;
        }
        return new String(charArray, 0, i2);
    }
}
