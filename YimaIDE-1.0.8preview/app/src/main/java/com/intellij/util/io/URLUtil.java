package com.intellij.util.io;

import com.intellij.openapi.util.Pair;
import com.intellij.util.lang.UrlUtilRt;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;
import org.jetbrains.kotlin.backend.jvm.JvmSyntheticAccessorGenerator;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class URLUtil {
    public static final Pattern DATA_URI_PATTERN = Pattern.compile("data:([^,;]+/[^,;]+)(;charset[=:][^,;]+)?(;base64)?,(.+)");
    public static final Pattern URL_PATTERN = Pattern.compile("\\b(mailto:|(news|(ht|f)tp(s?))://|((?<![\\p{L}0-9_.])(www\\.)))[-A-Za-z0-9+$&@#/%?=~_|!:,.;]*[-A-Za-z0-9+$&@#/%=~_|]");
    public static final Pattern URL_WITH_PARENS_PATTERN = Pattern.compile("\\b(mailto:|(news|(ht|f)tp(s?))://|((?<![\\p{L}0-9_.])(www\\.)))[-A-Za-z0-9+$&@#/%?=~_|!:,.;()]*[-A-Za-z0-9+$&@#/%=~_|()]");
    public static final Pattern FILE_URL_PATTERN = Pattern.compile("\\b(file:///)[-A-Za-z0-9+$&@#/%?=~_|!:,.;]*[-A-Za-z0-9+$&@#/%=~_|]");
    public static final Pattern HREF_PATTERN = Pattern.compile("<a(?:\\s+href\\s*=\\s*[\"']([^\"']*)[\"'])?\\s*>([^<]*)</a>");

    /* JADX WARN: Code duplicated, block: B:16:0x0032  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 2 && i != 4 && i != 15 && i != 17 && i != 25 && i != 34 && i != 36 && i != 22 && i != 23 && i != 29 && i != 30) {
            switch (i) {
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 2 && i != 4 && i != 15 && i != 17 && i != 25 && i != 34 && i != 36 && i != 22 && i != 23 && i != 29 && i != 30) {
            switch (i) {
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 3:
            case 5:
            case 12:
            case 13:
            case 18:
            case 32:
            case 33:
            case 35:
                objArr[0] = "url";
                break;
            case 2:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 17:
            case 22:
            case 23:
            case 25:
            case 29:
            case 30:
            case 34:
            case 36:
                objArr[0] = "com/intellij/util/io/URLUtil";
                break;
            case 14:
            case 16:
            case 28:
                objArr[0] = JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER;
                break;
            case 19:
                objArr[0] = "value";
                break;
            case 20:
                objArr[0] = "dataUrl";
                break;
            case 21:
                objArr[0] = "string";
                break;
            case 24:
                objArr[0] = "sshUrl";
                break;
            case 26:
                objArr[0] = "file";
                break;
            case 27:
                objArr[0] = "pathInJar";
                break;
            case 31:
                objArr[0] = "text";
                break;
            default:
                objArr[0] = "line";
                break;
        }
        if (i == 2) {
            objArr[1] = "openStream";
        } else if (i == 4) {
            objArr[1] = "openResourceStream";
        } else if (i == 15 || i == 17) {
            objArr[1] = "unescapePercentSequences";
        } else if (i == 25) {
            objArr[1] = "parseHostFromSshUrl";
        } else if (i == 34) {
            objArr[1] = "extractPath";
        } else if (i == 36) {
            objArr[1] = "addSchemaIfMissing";
        } else if (i != 22 && i != 23) {
            if (i != 29 && i != 30) {
                switch (i) {
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        objArr[1] = "resourceExists";
                        break;
                    default:
                        objArr[1] = "com/intellij/util/io/URLUtil";
                        break;
                }
            } else {
                objArr[1] = "encodeURIComponent";
            }
        } else {
            objArr[1] = "decode";
        }
        switch (i) {
            case 1:
                objArr[2] = "openStream";
                break;
            case 2:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 17:
            case 22:
            case 23:
            case 25:
            case 29:
            case 30:
            case 34:
            case 36:
                break;
            case 3:
                objArr[2] = "openResourceStream";
                break;
            case 5:
                objArr[2] = "resourceExists";
                break;
            case 12:
                objArr[2] = "splitJarUrl";
                break;
            case 13:
                objArr[2] = "urlToFile";
                break;
            case 14:
            case 16:
                objArr[2] = "unescapePercentSequences";
                break;
            case 18:
                objArr[2] = "containsScheme";
                break;
            case 19:
                objArr[2] = "isDataUri";
                break;
            case 20:
                objArr[2] = "getBytesFromDataUri";
                break;
            case 21:
                objArr[2] = "decode";
                break;
            case 24:
                objArr[2] = "parseHostFromSshUrl";
                break;
            case 26:
            case 27:
                objArr[2] = "getJarEntryURL";
                break;
            case 28:
                objArr[2] = "encodeURIComponent";
                break;
            case 31:
                objArr[2] = "findUrl";
                break;
            case 32:
                objArr[2] = "internProtocol";
                break;
            case 33:
                objArr[2] = "extractPath";
                break;
            case 35:
                objArr[2] = "addSchemaIfMissing";
                break;
            default:
                objArr[2] = "canContainUrl";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 4 && i != 15 && i != 17 && i != 25 && i != 34 && i != 36 && i != 22 && i != 23 && i != 29 && i != 30) {
            switch (i) {
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public static String extractPath(String str) {
        if (str == null) {
            $$$reportNull$$$0(33);
        }
        int iIndexOf = str.indexOf("://");
        return iIndexOf >= 0 ? str.substring(iIndexOf + 3) : str;
    }

    public static Pair<String, String> splitJarUrl(String str) {
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        int iIndexOf = str.indexOf("!/");
        if (iIndexOf < 0) {
            return null;
        }
        String strSubstring = str.substring(iIndexOf + 2);
        String strSubstring2 = str.substring(0, iIndexOf);
        if (strSubstring2.startsWith("jar:")) {
            strSubstring2 = strSubstring2.substring(4);
        }
        if (strSubstring2.startsWith("file")) {
            try {
                strSubstring2 = urlToFile(new URL(strSubstring2)).getPath().replace('\\', '/');
            } catch (Exception unused) {
                strSubstring2 = strSubstring2.substring(4);
                if (strSubstring2.startsWith("://")) {
                    strSubstring2 = strSubstring2.substring(3);
                } else if (!strSubstring2.isEmpty() && strSubstring2.charAt(0) == ':') {
                    strSubstring2 = strSubstring2.substring(1);
                }
            }
        }
        return new Pair<>(strSubstring2, strSubstring);
    }

    public static String unescapePercentSequences(String str) {
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        String string = unescapePercentSequences(str, 0, str.length()).toString();
        if (string == null) {
            $$$reportNull$$$0(15);
        }
        return string;
    }

    public static File urlToFile(URL url) {
        if (url == null) {
            $$$reportNull$$$0(13);
        }
        try {
            return new File(url.toURI().getSchemeSpecificPart());
        } catch (URISyntaxException e) {
            p06.a("URL='", url, WasmCompilerKt.importedStringConstants, e);
            return null;
        }
    }

    public static CharSequence unescapePercentSequences(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(16);
        }
        CharSequence charSequenceUnescapePercentSequences = UrlUtilRt.unescapePercentSequences(charSequence, i, i2);
        if (charSequenceUnescapePercentSequences == null) {
            $$$reportNull$$$0(17);
        }
        return charSequenceUnescapePercentSequences;
    }
}
