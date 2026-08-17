package org.eclipse.tm4e.core.internal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class StringUtils {
    private static final List<String> LIST_WITH_EMPTY_STRING = List.of("");
    private static final Pattern RRGGBB = Pattern.compile("^#[0-9a-f]{6}", 2);
    private static final Pattern RRGGBBAA = Pattern.compile("^#[0-9a-f]{8}", 2);
    private static final Pattern RGB = Pattern.compile("^#[0-9a-f]{3}", 2);
    private static final Pattern RGBA = Pattern.compile("^#[0-9a-f]{4}", 2);

    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidHexColor(CharSequence charSequence) {
        if (charSequence.length() < 1) {
            return false;
        }
        return RRGGBB.matcher(charSequence).matches() || RRGGBBAA.matcher(charSequence).matches() || RGB.matcher(charSequence).matches() || RGBA.matcher(charSequence).matches();
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String[] splitToArray(String str, char c, int i) {
        if (str.isEmpty()) {
            return new String[]{""};
        }
        String[] strArr = new String[8];
        int iIndexOf = str.indexOf(c, 0);
        int i2 = 0;
        int i3 = 0;
        while (iIndexOf >= 0) {
            if (i2 == strArr.length) {
                String[] strArr2 = new String[strArr.length + (strArr.length >> 1)];
                System.arraycopy(strArr, 0, strArr2, 0, i2);
                strArr = strArr2;
            }
            strArr[i2] = str.substring(i3, iIndexOf);
            i2++;
            i3 = iIndexOf + 1;
            if (i2 == i) {
                break;
            }
            iIndexOf = str.indexOf(c, i3);
        }
        if (i2 == strArr.length) {
            String[] strArr3 = new String[strArr.length + 1];
            System.arraycopy(strArr, 0, strArr3, 0, i2);
            strArr = strArr3;
        }
        strArr[i2] = str.substring(i3);
        int i4 = i2 + 1;
        if (i4 == strArr.length) {
            return strArr;
        }
        String[] strArr4 = new String[i4];
        System.arraycopy(strArr, 0, strArr4, 0, i4);
        return strArr4;
    }

    public static List<String> splitToList(String str, char c) {
        if (str.isEmpty()) {
            return LIST_WITH_EMPTY_STRING;
        }
        ArrayList arrayList = new ArrayList(8);
        int i = 0;
        int iIndexOf = str.indexOf(c, 0);
        while (iIndexOf >= 0) {
            arrayList.add(str.substring(i, iIndexOf));
            i = iIndexOf + 1;
            iIndexOf = str.indexOf(c, i);
        }
        arrayList.add(str.substring(i));
        return arrayList;
    }

    public static int strArrCmp(List<String> list, List<String> list2) {
        if (list == null && list2 == null) {
            return 0;
        }
        if (list == null) {
            return -1;
        }
        if (list2 == null) {
            return 1;
        }
        int size = list.size();
        int size2 = list2.size();
        if (size != size2) {
            return size - size2;
        }
        for (int i = 0; i < size; i++) {
            int iStrcmp = strcmp(list.get(i), list2.get(i));
            if (iStrcmp != 0) {
                return iStrcmp;
            }
        }
        return 0;
    }

    public static int strcmp(String str, String str2) {
        int iCompareTo = str.compareTo(str2);
        if (iCompareTo < 0) {
            return -1;
        }
        return iCompareTo > 0 ? 1 : 0;
    }

    public static String substringBefore(String str, char c, String str2) {
        int iIndexOf = str.indexOf(c);
        return iIndexOf == -1 ? str2 : str.substring(0, iIndexOf);
    }

    public static String toString(Object obj, Consumer<StringBuilder> consumer) {
        if (obj == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(obj.getClass().getSimpleName());
        sb.append('{');
        consumer.accept(sb);
        sb.append('}');
        return sb.toString();
    }

    public static String[] splitToArray(String str, char c) {
        return splitToArray(str, c, -1);
    }
}
