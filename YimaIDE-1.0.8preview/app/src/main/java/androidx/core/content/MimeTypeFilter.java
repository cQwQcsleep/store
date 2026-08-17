package androidx.core.content;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    public static String matches(String str, String[] strArr) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(PsuedoNames.PSEUDONAME_ROOT);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(strArrSplit, str2.split(PsuedoNames.PSEUDONAME_ROOT))) {
                return str2;
            }
        }
        return null;
    }

    public static String[] matchesMany(String[] strArr, String str) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = str.split(PsuedoNames.PSEUDONAME_ROOT);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(PsuedoNames.PSEUDONAME_ROOT), strArrSplit)) {
                arrayList.add(str2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static boolean mimeTypeAgainstFilter(String[] strArr, String[] strArr2) {
        if (strArr2.length != 2) {
            w01.a("Ill-formatted MIME type filter. Must be type/subtype.");
            return false;
        }
        if (strArr2[0].isEmpty() || strArr2[1].isEmpty()) {
            w01.a("Ill-formatted MIME type filter. Type or subtype empty.");
            return false;
        }
        if (strArr.length != 2) {
            return false;
        }
        if ("*".equals(strArr2[0]) || strArr2[0].equals(strArr[0])) {
            return "*".equals(strArr2[1]) || strArr2[1].equals(strArr[1]);
        }
        return false;
    }

    public static boolean matches(String str, String str2) {
        if (str == null) {
            return false;
        }
        return mimeTypeAgainstFilter(str.split(PsuedoNames.PSEUDONAME_ROOT), str2.split(PsuedoNames.PSEUDONAME_ROOT));
    }

    public static String matches(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        String[] strArrSplit = str.split(PsuedoNames.PSEUDONAME_ROOT);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(PsuedoNames.PSEUDONAME_ROOT), strArrSplit)) {
                return str2;
            }
        }
        return null;
    }
}
