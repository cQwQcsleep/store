package com.shadow.kotlin.text;

import com.shadow.kotlin.collections.AbstractList;
import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.functions.Function1;
import com.shadow.kotlin.ranges.IntRange;
import com.shadow.kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1;
import com.shadow.kotlin.sequences.TransformingSequence;
import core.pro.android.notify.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class StringsKt extends StringsKt__StringsJVMKt {
    public static void b(StringBuilder sb, Object obj, Function1 function1) {
        if (function1 != null) {
            sb.append((CharSequence) function1.invoke(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    public static String c(char[] cArr, int i, int i2) {
        AbstractList.Companion companion = AbstractList.Companion;
        int length = cArr.length;
        companion.getClass();
        if (i < 0 || i2 > length) {
            throw new IndexOutOfBoundsException("startIndex: " + i + ", endIndex: " + i2 + ", size: " + length);
        }
        if (i <= i2) {
            return new String(cArr, i, i2 - i);
        }
        throw new IllegalArgumentException("startIndex: " + i + " > endIndex: " + i2);
    }

    public static boolean d(CharSequence charSequence, char c) {
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        return h(charSequence, c, 0, false, 2) >= 0;
    }

    public static boolean e(String str, String str2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        return i(str, str2, 0, false, 2) >= 0;
    }

    public static boolean f(String str, String str2, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "suffix");
        return !z ? str.endsWith(str2) : StringsKt__StringsJVMKt.regionMatches(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    public static boolean g(String str, String str2) {
        return str == null ? str2 == null : str.equalsIgnoreCase(str2);
    }

    public static final int getLastIndex(CharSequence charSequence) {
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static int h(CharSequence charSequence, char c, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? indexOfAny(charSequence, new char[]{c}, i, z) : ((String) charSequence).indexOf(c, i);
    }

    public static /* synthetic */ int i(CharSequence charSequence, String str, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, str, i, z);
    }

    public static final int indexOf(CharSequence charSequence, String str, int i, boolean z) {
        int first;
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        CloseableKt.checkNotNullParameter(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        int length = charSequence.length();
        if (i < 0) {
            i = 0;
        }
        int length2 = charSequence.length();
        if (length > length2) {
            length = length2;
        }
        IntRange intRange = new IntRange(i, length, 1);
        if ((charSequence instanceof String) && (str instanceof String)) {
            first = intRange.getFirst();
            int last = intRange.getLast();
            int step = intRange.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (!StringsKt__StringsJVMKt.regionMatches(str, 0, (String) charSequence, first, str.length(), z)) {
                    if (first != last) {
                        first += step;
                    }
                }
                return first;
            }
            return -1;
        }
        first = intRange.getFirst();
        int last2 = intRange.getLast();
        int step2 = intRange.getStep();
        if ((step2 > 0 && first <= last2) || (step2 < 0 && last2 <= first)) {
            while (!regionMatchesImpl(str, 0, charSequence, first, str.length(), z)) {
                if (first != last2) {
                    first += step2;
                }
            }
            return first;
        }
        return -1;
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    public static final int indexOfAny(CharSequence charSequence, char[] cArr, int i, boolean z) {
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        CloseableKt.checkNotNullParameter(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            CloseableKt.checkNotNullParameter(cArr, "<this>");
            int length = cArr.length;
            if (length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            if (length != 1) {
                throw new IllegalArgumentException("Array has more than one element.");
            }
            return ((String) charSequence).indexOf(cArr[0], i);
        }
        if (i < 0) {
            i = 0;
        }
        ?? Iterator2 = new IntRange(i, getLastIndex(charSequence), 1).iterator2();
        while (Iterator2.hasNext()) {
            int iNextInt = Iterator2.nextInt();
            char cCharAt = charSequence.charAt(iNextInt);
            for (char c : cArr) {
                if (CharsKt.equals(c, cCharAt, z)) {
                    return iNextInt;
                }
            }
        }
        return -1;
    }

    public static int j(String str, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(str);
        }
        CloseableKt.checkNotNullParameter(str, "<this>");
        return str.lastIndexOf(c, i);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    public static String k(String str) {
        CharSequence charSequenceSubSequence;
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (8 <= str.length()) {
            charSequenceSubSequence = str.subSequence(0, str.length());
        } else {
            StringBuilder sb = new StringBuilder(8);
            ?? Iterator2 = new IntRange(1, 8 - str.length(), 1).iterator2();
            while (Iterator2.hasNext()) {
                Iterator2.nextInt();
                sb.append('0');
            }
            sb.append((CharSequence) str);
            charSequenceSubSequence = sb;
        }
        return charSequenceSubSequence.toString();
    }

    public static String m(String str, String str2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "prefix");
        if (!startsWith$default$1(str, str2)) {
            return str;
        }
        String strSubstring = str.substring(str2.length());
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String n(String str, char c, char c2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        String strReplace = str.replace(c, c2);
        CloseableKt.checkNotNullExpressionValue(strReplace, "replace(...)");
        return strReplace;
    }

    public static String o(String str, String str2, String str3) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int iIndexOf = indexOf(str, str2, 0, false);
        if (iIndexOf < 0) {
            return str;
        }
        int length = str2.length();
        int i = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i2 = 0;
        do {
            sb.append((CharSequence) str, i2, iIndexOf);
            sb.append(str3);
            i2 = iIndexOf + length;
            if (iIndexOf >= str.length()) {
                break;
            }
            iIndexOf = indexOf(str, str2, iIndexOf + i, false);
        } while (iIndexOf > 0);
        sb.append((CharSequence) str, i2, str.length());
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static List p(String str, final char[] cArr) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        final boolean z = false;
        if (cArr.length != 1) {
            requireNonNegativeLimit(0);
            SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 = new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(new DelimitedRangesSequence(str, 0, 0, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: com.shadow.kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((CharSequence) obj, ((Number) obj2).intValue());
                }

                public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
                    CloseableKt.checkNotNullParameter(charSequence, "$this$$receiver");
                    int iIndexOfAny = StringsKt.indexOfAny(charSequence, cArr, i, z);
                    if (iIndexOfAny < 0) {
                        return null;
                    }
                    return new com.shadow.kotlin.Pair(Integer.valueOf(iIndexOfAny), 1);
                }
            }));
            ArrayList arrayList = new ArrayList(CollectionsKt.b(sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1));
            Iterator<Object> it = sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1.iterator();
            while (it.hasNext()) {
                arrayList.add(substring(str, (IntRange) it.next()));
            }
            return arrayList;
        }
        String strValueOf = String.valueOf(cArr[0]);
        requireNonNegativeLimit(0);
        int iIndexOf = indexOf(str, strValueOf, 0, false);
        if (iIndexOf == -1) {
            return CollectionsKt.d(str.toString());
        }
        ArrayList arrayList2 = new ArrayList(10);
        int length = 0;
        do {
            arrayList2.add(str.subSequence(length, iIndexOf).toString());
            length = strValueOf.length() + iIndexOf;
            iIndexOf = indexOf(str, strValueOf, length, false);
        } while (iIndexOf != -1);
        arrayList2.add(str.subSequence(length, str.length()).toString());
        return arrayList2;
    }

    public static boolean q(String str, String str2, int i, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        return !z ? str.startsWith(str2, i) : StringsKt__StringsJVMKt.regionMatches(str, i, str2, 0, str2.length(), z);
    }

    public static boolean r(String str, String str2, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "prefix");
        return !z ? str.startsWith(str2) : StringsKt__StringsJVMKt.regionMatches(str, 0, str2, 0, str2.length(), z);
    }

    public static final boolean regionMatchesImpl(String str, int i, CharSequence charSequence, int i2, int i3, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(charSequence, "other");
        if (i2 < 0 || i < 0 || i > str.length() - i3 || i2 > charSequence.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!CharsKt.equals(str.charAt(i + i4), charSequence.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static final void requireNonNegativeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(h.a(i, "Limit must be non-negative, but was ").toString());
        }
    }

    public static String s(String str, String str2) {
        CloseableKt.checkNotNullParameter(str2, "delimiter");
        int i = i(str, str2, 0, false, 6);
        if (i == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + i, str.length());
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static boolean startsWith$default$1(String str, String str2) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "prefix");
        return r(str, str2, false);
    }

    public static final String substring(CharSequence charSequence, IntRange intRange) {
        CloseableKt.checkNotNullParameter(charSequence, "<this>");
        CloseableKt.checkNotNullParameter(intRange, "range");
        return charSequence.subSequence(intRange.getFirst(), intRange.getLast() + 1).toString();
    }

    public static String t(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str, "missingDelimiterValue");
        int iJ = j(str, '.', 0, 6);
        if (iJ == -1) {
            return str;
        }
        String strSubstring = str.substring(iJ + 1, str.length());
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String u(int i, String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        int length = str.length();
        if (i > length) {
            i = length;
        }
        String strSubstring = str.substring(0, i);
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static Integer v(String str) {
        boolean z;
        int i;
        int i2;
        CharsKt.a(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char cCharAt = str.charAt(0);
        int i4 = -2147483647;
        if (CloseableKt.compare(cCharAt, 48) < 0) {
            i = 1;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                i4 = Integer.MIN_VALUE;
                z = true;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z = false;
            }
        } else {
            z = false;
            i = 0;
        }
        int i5 = -59652323;
        while (i < length) {
            int iDigit = Character.digit((int) str.charAt(i), 10);
            if (iDigit < 0) {
                return null;
            }
            if ((i3 < i5 && (i5 != -59652323 || i3 < (i5 = i4 / 10))) || (i2 = i3 * 10) < i4 + iDigit) {
                return null;
            }
            i3 = i2 - iDigit;
            i++;
        }
        return z ? Integer.valueOf(i3) : Integer.valueOf(-i3);
    }

    public static CharSequence w(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            char cCharAt = str.charAt(!z ? i : length);
            boolean z2 = Character.isWhitespace(cCharAt) || Character.isSpaceChar(cCharAt);
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1);
    }

    public static String x(final String str) {
        List listD;
        String str2;
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (StringsKt__StringsJVMKt.isBlank("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        final boolean z = false;
        requireNonNegativeLimit(0);
        final List listA = ArraysKt.a(new String[]{"\r\n", "\n", "\r"});
        TransformingSequence.AnonymousClass1 anonymousClass1 = new TransformingSequence.AnonymousClass1(new TransformingSequence(new DelimitedRangesSequence(str, 0, 0, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: com.shadow.kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CharSequence) obj, ((Number) obj2).intValue());
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x00e6 A[EDGE_INSN: B:61:0x00e6->B:56:0x00e6 BREAK  A[LOOP:0: B:27:0x006a->B:38:0x009d], EDGE_INSN: B:67:0x00e6->B:56:0x00e6 BREAK  A[LOOP:2: B:44:0x00b3->B:55:0x00e4]] */
            /* JADX WARN: Removed duplicated region for block: B:56:0x00e6 A[EDGE_INSN: B:61:0x00e6->B:56:0x00e6 BREAK  A[LOOP:0: B:27:0x006a->B:38:0x009d]] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
                Object next;
                com.shadow.kotlin.Pair pair;
                Object next2;
                CloseableKt.checkNotNullParameter(charSequence, "$this$$receiver");
                List<String> list = listA;
                boolean z2 = z;
                if (z2 || list.size() != 1) {
                    if (i < 0) {
                        i = 0;
                    }
                    IntRange intRange = new IntRange(i, charSequence.length(), 1);
                    if (charSequence instanceof String) {
                        int first = intRange.getFirst();
                        int last = intRange.getLast();
                        int step = intRange.getStep();
                        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
                            pair = null;
                        } else {
                            while (true) {
                                Iterator<T> it = list.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        next2 = null;
                                        break;
                                    }
                                    next2 = it.next();
                                    String str3 = (String) next2;
                                    if (StringsKt__StringsJVMKt.regionMatches(str3, 0, (String) charSequence, first, str3.length(), z2)) {
                                        break;
                                    }
                                }
                                String str4 = (String) next2;
                                if (str4 == null) {
                                    if (first == last) {
                                        break;
                                    }
                                    first += step;
                                } else {
                                    pair = new com.shadow.kotlin.Pair(Integer.valueOf(first), str4);
                                    break;
                                }
                            }
                            pair = null;
                        }
                    } else {
                        int first2 = intRange.getFirst();
                        int last2 = intRange.getLast();
                        int step2 = intRange.getStep();
                        if ((step2 > 0 && first2 <= last2) || (step2 < 0 && last2 <= first2)) {
                            while (true) {
                                Iterator<T> it2 = list.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        next = null;
                                        break;
                                    }
                                    next = it2.next();
                                    String str5 = (String) next;
                                    if (StringsKt.regionMatchesImpl(str5, 0, charSequence, first2, str5.length(), z2)) {
                                        break;
                                    }
                                }
                                String str6 = (String) next;
                                if (str6 == null) {
                                    if (first2 == last2) {
                                        break;
                                    }
                                    first2 += step2;
                                } else {
                                    pair = new com.shadow.kotlin.Pair(Integer.valueOf(first2), str6);
                                    break;
                                }
                            }
                            pair = null;
                        }
                    }
                } else {
                    int size = list.size();
                    if (size == 0) {
                        throw new NoSuchElementException("List is empty.");
                    }
                    if (size != 1) {
                        throw new IllegalArgumentException("List has more than one element.");
                    }
                    String str7 = list.get(0);
                    int i2 = StringsKt.i(charSequence, str7, i, false, 4);
                    if (i2 >= 0) {
                        pair = new com.shadow.kotlin.Pair(Integer.valueOf(i2), str7);
                    }
                }
                if (pair != null) {
                    return new com.shadow.kotlin.Pair(pair.getFirst(), Integer.valueOf(((String) pair.getSecond()).length()));
                }
                return null;
            }
        }), new kotlin.jvm.functions.Function1<kotlin.ranges.IntRange, String>() { // from class: com.shadow.kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final String invoke(IntRange intRange) {
                CloseableKt.checkNotNullParameter(intRange, "it");
                return StringsKt.substring(str, intRange);
            }
        }));
        if (anonymousClass1.hasNext()) {
            Object next = anonymousClass1.next();
            if (anonymousClass1.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (anonymousClass1.hasNext()) {
                    arrayList.add(anonymousClass1.next());
                }
                listD = arrayList;
            } else {
                listD = CollectionsKt.d(next);
            }
        } else {
            listD = EmptyList.INSTANCE;
        }
        int length = str.length();
        listD.size();
        StringsKt__IndentKt$getIndentFunction$1 stringsKt__IndentKt$getIndentFunction$1 = new kotlin.jvm.functions.Function1<String, String>() { // from class: com.shadow.kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            public final String invoke(String str3) {
                CloseableKt.checkNotNullParameter(str3, "line");
                return str3;
            }
        };
        int size = listD.size() - 1;
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (Object obj : listD) {
            int i2 = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            String str3 = (String) obj;
            String strSubstring = null;
            if ((i == 0 || i == size) && StringsKt__StringsJVMKt.isBlank(str3)) {
                str3 = null;
            } else {
                int length2 = str3.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        i3 = -1;
                        break;
                    }
                    char cCharAt = str3.charAt(i3);
                    if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                        break;
                    }
                    i3++;
                }
                if (i3 != -1 && q(str3, "|", i3, false)) {
                    strSubstring = str3.substring("|".length() + i3);
                    CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
                }
                if (strSubstring != null && (str2 = (String) stringsKt__IndentKt$getIndentFunction$1.invoke((StringsKt__IndentKt$getIndentFunction$1) strSubstring)) != null) {
                    str3 = str2;
                }
            }
            if (str3 != null) {
                arrayList2.add(str3);
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder(length);
        CollectionsKt.joinTo(arrayList2, sb, "\n", "", "", -1, "...", null);
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
