package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Wf0;
import defpackage.xlf;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Wf0 {
    public static final char[] a = new char[0];
    public static final String[] b = new String[0];
    public static final String c = System.getProperty("line.separator");
    public static final /* synthetic */ boolean d = true;

    public enum a {
        b,
        c,
        d,
        e;

        a() {
        }

        public final String a() {
            int i = Vf0.a[ordinal()];
            if (i == 1) {
                return "(";
            }
            if (i == 2) {
                return "[";
            }
            if (i == 3) {
                return "{";
            }
            if (i == 4) {
                return XmlPullParser.NO_NAMESPACE;
            }
            defpackage.gk0.a("Invalid brace type: ", this);
            return null;
        }

        public final String b() {
            int i = Vf0.a[ordinal()];
            if (i == 1) {
                return ")";
            }
            if (i == 2) {
                return "]";
            }
            if (i == 3) {
                return "}";
            }
            if (i == 4) {
                return XmlPullParser.NO_NAMESPACE;
            }
            defpackage.gk0.a("Invalid brace type: ", this);
            return null;
        }
    }

    public static String a(int i, int i2, boolean z) {
        boolean z2 = d;
        if (!z2 && (i2 < 0 || i2 > 8)) {
            x1f.a();
            return null;
        }
        String str = z ? "0x" : XmlPullParser.NO_NAMESPACE;
        String hexString = Integer.toHexString(i);
        if (i >= 0) {
            return str + a(i2, hexString);
        }
        if (!z2 && hexString.length() != 8) {
            x1f.a();
            return null;
        }
        return str + hexString;
    }

    public static int b(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!b(str.charAt(i))) {
                return i;
            }
        }
        return str.length();
    }

    public static boolean c(String str) {
        return str.equals("0") || i(str).equals("false");
    }

    public static boolean d(String str) {
        return str.equals("1") || i(str).equals("true");
    }

    public static String e(String str) {
        return "\"" + str + "\"";
    }

    public static List<String> f(String str) {
        return a(str, false);
    }

    public static HashSet g(String str) {
        final HashSet hashSet = new HashSet();
        a(str, false, new Consumer() { // from class: vlf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hashSet.add((String) obj);
            }
        });
        return hashSet;
    }

    public static String h(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (31 >= cCharAt || cCharAt >= 127) {
                sb.append("\\u");
                sb.append(a((int) cCharAt, 4, false));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String i(String str) {
        return str.toLowerCase(Locale.ROOT);
    }

    public static String j(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static String k(String str) {
        int length = str.length();
        int i = 0;
        while (i < length && b(str.charAt(i))) {
            i++;
        }
        while (true) {
            int i2 = length - 1;
            if (i2 <= i || !b(str.charAt(i2))) {
                break;
            }
            length--;
        }
        return (i > 0 || length < str.length()) ? str.substring(i, length) : str;
    }

    public static String l(String str) {
        String strReplace = str.replace("\r\n", "\n");
        String str2 = c;
        if (str2.equals("\r\n")) {
            return strReplace.replace("\n", "\r\n");
        }
        if (d || str2.equals("\n")) {
            return strReplace;
        }
        x1f.a();
        return null;
    }

    public static String c(String... strArr) {
        return a("\n", Arrays.asList(strArr));
    }

    public static String b(String... strArr) {
        return a((List<String>) Arrays.asList(strArr));
    }

    public static String b(int i, int i2) {
        return a(i2, Integer.toString(i));
    }

    public static boolean b(int i) {
        return Character.isWhitespace(i) || a(i);
    }

    public static void b(int i, String str, StringBuilder sb) {
        sb.append(str);
        for (int length = str.length(); length < i; length++) {
            sb.append(" ");
        }
    }

    public static void a(String str, char c2, Consumer consumer) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == c2) {
                consumer.accept(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        consumer.accept(str.substring(i));
    }

    public static List<String> a(String str, char c2) {
        ArrayList arrayList = new ArrayList();
        a(str, c2, new xlf(arrayList));
        return arrayList;
    }

    public static String[] a(String str, char c2, final int i) {
        if (!d && i <= 1) {
            x1f.a();
            return null;
        }
        final String[] strArr = new String[i];
        final C1131bA c1131bA = new C1131bA(0);
        a(str, c2, new Consumer() { // from class: wlf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Wf0.a(c1131bA, i, strArr, (String) obj);
            }
        });
        if (i == c1131bA.a()) {
            return strArr;
        }
        return null;
    }

    public static /* synthetic */ void a(C1131bA c1131bA, int i, String[] strArr, String str) {
        int iB = c1131bA.b();
        if (iB < i) {
            strArr[iB] = str;
        }
    }

    public static boolean a(StringBuilder sb, String str, Object obj) {
        if (obj == null) {
            return false;
        }
        String string = obj.toString();
        if (string.isEmpty()) {
            return false;
        }
        if (str != null) {
            sb.append(str);
        }
        sb.append(string);
        return true;
    }

    public static void a(int i, String str, StringBuilder sb) {
        for (int length = str.length(); length < i; length++) {
            sb.append(" ");
        }
        sb.append(str);
    }

    public static <T> StringBuilder a(StringBuilder sb, Collection<T> collection) {
        return a(sb, collection, ", ", a.b);
    }

    public static <T> StringBuilder a(StringBuilder sb, Iterable<T> iterable, String str, a aVar) {
        sb.append(aVar.a());
        boolean z = true;
        for (T t : iterable) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(t);
        }
        sb.append(aVar.b());
        return sb;
    }

    public static void a(StringBuilder sb, String... strArr) {
        for (String str : strArr) {
            sb.append(str);
            sb.append(c);
        }
    }

    public static String a(String str, String... strArr) {
        return a(str, (Iterable) Arrays.asList(strArr));
    }

    public static <T> String a(String str, Iterable<T> iterable) {
        return a(str, iterable, a.e);
    }

    public static <T> String a(String str, Iterable<T> iterable, Function<T, String> function) {
        return a(str, iterable, function, a.e);
    }

    public static String a(Stream stream, Function function) {
        return a(" <OR> ", (Iterable) stream.collect(Collectors.toList()), function, a.e);
    }

    public static <T> String a(String str, T[] tArr, Function<T, String> function, a aVar) {
        return a(str, Arrays.asList(tArr), function, aVar);
    }

    public static <T> String a(String str, Iterable<T> iterable, a aVar) {
        return a(str, iterable, new Function() { // from class: ulf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return obj.toString();
            }
        }, aVar);
    }

    public static <T> String a(String str, Iterable<T> iterable, Function<T, String> function, a aVar) {
        StringBuilder sb = new StringBuilder();
        a(sb, C2753uC.c(iterable, function), str, aVar);
        return sb.toString();
    }

    public static String a(List<String> list) {
        return a(c, (List) list);
    }

    public static String a(String str, List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(str);
        }
        return sb.toString();
    }

    public static String a(String... strArr) {
        return a(c, strArr);
    }

    public static <T> String a(Collection<T> collection) {
        return a(c, collection, a.e);
    }

    public static List<String> a(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        a(str, z, new xlf(arrayList));
        return arrayList;
    }

    public static void a(String str, boolean z, Consumer consumer) {
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\r' && (i = i2 + 1) < length && str.charAt(i) == '\n') {
                consumer.accept(str.substring(i3, i2));
                i3 = i + 1;
                i2 = i;
            } else if (cCharAt == '\n') {
                i = i2;
                consumer.accept(str.substring(i3, i2));
                i3 = i + 1;
                i2 = i;
            }
            i2++;
        }
        if (i3 < length) {
            String strSubstring = str.substring(i3);
            if (z || !strSubstring.isEmpty()) {
                consumer.accept(strSubstring);
            }
        }
    }

    public static String a(int i, String str) {
        if (!d && i > 16) {
            x1f.a();
            return null;
        }
        int length = i - str.length();
        if (length <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "0000000000000000", 0, length);
        sb.append(str);
        return sb.toString();
    }

    public static String a(int i, int i2) {
        return a(i, i2, true);
    }

    public static boolean a(int i) {
        return i == 65279;
    }

    public static String a(long j) {
        return a(16, j);
    }

    public static String a(int i, long j) {
        boolean z = d;
        if (!z && (i < 0 || i > 16)) {
            x1f.a();
            return null;
        }
        String hexString = Long.toHexString(j);
        if (j >= 0) {
            return "0x" + a(i, hexString);
        }
        if (!z && hexString.length() != 16) {
            x1f.a();
            return null;
        }
        return "0x" + hexString;
    }

    public static String a(String str, int i) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            i--;
            if (i >= 0) {
                sb.append(str);
            } else {
                return sb.toString();
            }
        }
    }

    public static String a(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str = a(str, entry.getKey(), entry.getValue());
        }
        return str;
    }

    public static String a(String str, String str2, String str3) {
        return str.replaceAll(Pattern.quote(str2), Matcher.quoteReplacement(str3));
    }

    public static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static String a(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    public static int a(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        int i = 0;
        while (iIndexOf > -1) {
            i++;
            iIndexOf = str.indexOf(str2, iIndexOf + 1);
        }
        return i;
    }
}
