package org.jetbrains.kotlin.util.capitalizeDecapitalize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001c\u0010\u000b\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\u000e\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0012\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0013"}, d2 = {"decapitalizeSmartForCompiler", "", "asciiOnly", "", "decapitalizeSmart", "capitalizeFirstWord", "decapitalizeWithUnderscores", "str", "isUpperCaseCharAt", "index", "", "isLowerCaseCharAt", "toLowerCase", "string", "toUpperCase", "capitalizeAsciiOnly", "decapitalizeAsciiOnly", "toLowerCaseAsciiOnly", "toUpperCaseAsciiOnly", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class CapitalizeDecapitalizeKt {
    public static final String capitalizeAsciiOnly(String str) {
        char cCharAt;
        str.getClass();
        if (str.length() == 0 || 'a' > (cCharAt = str.charAt(0)) || cCharAt >= '{') {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(Character.toUpperCase(cCharAt));
        sb.append((CharSequence) str, 1, str.length());
        return sb.toString();
    }

    public static final String capitalizeFirstWord(String str, boolean z) {
        Object next;
        str.getClass();
        Iterator it = CollectionsKt.drop(StringsKt.getIndices(str), 1).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (isLowerCaseCharAt(str, ((Number) next).intValue(), z));
        Integer num = (Integer) next;
        if (num == null) {
            return toUpperCase(str, z);
        }
        int iIntValue = num.intValue();
        return toUpperCase(str.substring(0, iIntValue), z) + str.substring(iIntValue);
    }

    public static /* synthetic */ String capitalizeFirstWord$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return capitalizeFirstWord(str, z);
    }

    public static final String decapitalizeAsciiOnly(String str) {
        char cCharAt;
        str.getClass();
        if (str.length() == 0 || 'A' > (cCharAt = str.charAt(0)) || cCharAt >= '[') {
            return str;
        }
        return Character.toLowerCase(cCharAt) + str.substring(1);
    }

    public static final String decapitalizeSmart(String str, boolean z) {
        str.getClass();
        String strDecapitalizeWithUnderscores = decapitalizeWithUnderscores(str, z);
        return strDecapitalizeWithUnderscores == null ? decapitalizeSmartForCompiler(str, z) : strDecapitalizeWithUnderscores;
    }

    public static /* synthetic */ String decapitalizeSmart$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return decapitalizeSmart(str, z);
    }

    public static final String decapitalizeSmartForCompiler(String str, boolean z) {
        Object next;
        str.getClass();
        if (str.length() == 0 || !isUpperCaseCharAt(str, 0, z)) {
            return str;
        }
        if (str.length() == 1 || !isUpperCaseCharAt(str, 1, z)) {
            if (z) {
                return decapitalizeAsciiOnly(str);
            }
            if (str.length() <= 0) {
                return str;
            }
            return Character.toLowerCase(str.charAt(0)) + str.substring(1);
        }
        Iterator it = StringsKt.getIndices(str).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (isUpperCaseCharAt(str, ((Number) next).intValue(), z));
        Integer num = (Integer) next;
        if (num == null) {
            return toLowerCase(str, z);
        }
        int iIntValue = num.intValue() - 1;
        return toLowerCase(str.substring(0, iIntValue), z) + str.substring(iIntValue);
    }

    public static /* synthetic */ String decapitalizeSmartForCompiler$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return decapitalizeSmartForCompiler(str, z);
    }

    private static final String decapitalizeWithUnderscores(String str, boolean z) {
        List listSplit$default = StringsKt.split$default(str, new String[]{"_"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() <= 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object obj2 : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj2;
            if (i == 0) {
                sb.append(toLowerCase(str2, z));
            } else {
                sb.append(toUpperCase(String.valueOf(StringsKt.first(str2)), z));
                sb.append(toLowerCase(StringsKt.drop(str2, 1), z));
            }
            i = i2;
        }
        return sb.toString();
    }

    private static final boolean isLowerCaseCharAt(String str, int i, boolean z) {
        char cCharAt = str.charAt(i);
        if (z) {
            return 'a' <= cCharAt && cCharAt < '{';
        }
        return Character.isLowerCase(cCharAt);
    }

    private static final boolean isUpperCaseCharAt(String str, int i, boolean z) {
        char cCharAt = str.charAt(i);
        if (z) {
            return 'A' <= cCharAt && cCharAt < '[';
        }
        return Character.isUpperCase(cCharAt);
    }

    private static final String toLowerCase(String str, boolean z) {
        if (z) {
            return toLowerCaseAsciiOnly(str);
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return lowerCase;
    }

    public static final String toLowerCaseAsciiOnly(String str) {
        str.getClass();
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ('A' <= cCharAt && cCharAt < '[') {
                cCharAt = Character.toLowerCase(cCharAt);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static final String toUpperCase(String str, boolean z) {
        if (z) {
            return toUpperCaseAsciiOnly(str);
        }
        String upperCase = str.toUpperCase(Locale.ROOT);
        upperCase.getClass();
        return upperCase;
    }

    public static final String toUpperCaseAsciiOnly(String str) {
        str.getClass();
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ('a' <= cCharAt && cCharAt < '{') {
                cCharAt = Character.toUpperCase(cCharAt);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }
}
