package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2066m90;
import com.android.tools.r8.internal.C3031xX;
import com.android.tools.r8.internal.InterfaceC2676tL;
import com.android.tools.r8.internal.W50;
import com.android.tools.r8.internal.Y50;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3031xX {
    public static final Pattern e = Pattern.compile("%(\\d+\\$)?([-+#, 0(<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])");
    public final Cb0 a;
    public final R50 b;
    public final Set c;
    public final boolean d;

    public C3031xX(Cb0 cb0, R50 r50, HashSet hashSet, boolean z) {
        this.a = cb0;
        this.b = r50;
        this.c = hashSet;
        this.d = z;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0089  */
    public final Stream a(Set set, final String str) {
        char c;
        char c2;
        Stream streamEmpty;
        byte b;
        Long lValueOf;
        byte b2;
        int length = str.length();
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            z2 |= cCharAt == '/';
            z3 |= cCharAt == '%';
            z = (!z || cCharAt == ':' || cCharAt == '%' || cCharAt == '/') ? false : true;
        }
        Stream streamC = this.d ? c(set, str) : Stream.empty();
        Stream streamC2 = z ? c(str) : Stream.empty();
        Stream streamEmpty2 = (!z3 || z2) ? Stream.empty() : b(str);
        Stream streamB = z2 ? b(set, str) : Stream.empty();
        String strSubstring = str.substring(str.lastIndexOf(47) + 1);
        if (strSubstring.isEmpty() || !Character.isDigit(strSubstring.charAt(0))) {
            c = 1;
            c2 = 0;
            streamEmpty = Stream.empty();
        } else {
            if (!strSubstring.isEmpty()) {
                int i2 = strSubstring.charAt(0) == '-' ? 1 : 0;
                if (i2 != strSubstring.length()) {
                    int i3 = i2 + 1;
                    char cCharAt2 = strSubstring.charAt(i2);
                    if (cCharAt2 < 128) {
                        b = AbstractC2250oM.a[cCharAt2];
                    } else {
                        byte[] bArr = AbstractC2250oM.a;
                        b = -1;
                    }
                    if (b >= 0 && b < 10) {
                        c = 1;
                        c2 = 0;
                        long j = -b;
                        while (true) {
                            if (i3 >= strSubstring.length()) {
                                if (i2 == 0) {
                                    if (j != Long.MIN_VALUE) {
                                        lValueOf = Long.valueOf(-j);
                                        break;
                                    }
                                    break;
                                }
                                lValueOf = Long.valueOf(j);
                                break;
                            }
                            int i4 = i3 + 1;
                            char cCharAt3 = strSubstring.charAt(i3);
                            if (cCharAt3 < 128) {
                                b2 = AbstractC2250oM.a[cCharAt3];
                            } else {
                                byte[] bArr2 = AbstractC2250oM.a;
                                b2 = -1;
                            }
                            if (b2 >= 0 && b2 < 10 && j >= -922337203685477580L) {
                                long j2 = j * 10;
                                long j3 = b2;
                                if (j2 >= j3 - Long.MIN_VALUE) {
                                    j = j2 - j3;
                                    i3 = i4;
                                }
                            }
                        }
                    } else {
                        c = 1;
                        c2 = 0;
                    }
                    lValueOf = null;
                    break;
                }
                c = 1;
                c2 = 0;
                lValueOf = null;
                break;
            }
            c = 1;
            c2 = 0;
            lValueOf = null;
            break;
            Integer numValueOf = (lValueOf == null || lValueOf.longValue() != ((long) lValueOf.intValue())) ? null : Integer.valueOf(lValueOf.intValue());
            W50 w50 = numValueOf != null ? (W50) this.b.e.get(numValueOf) : null;
            streamEmpty = w50 != null ? Stream.of(w50) : Stream.empty();
        }
        Stream[] streamArr = new Stream[5];
        streamArr[c2] = streamC;
        streamArr[c] = streamC2;
        streamArr[2] = streamEmpty2;
        streamArr[3] = streamB;
        streamArr[4] = streamEmpty;
        return De0.a(streamArr).peek(new Consumer() { // from class: tri
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(str, (W50) obj);
            }
        });
    }

    public final Stream b(Set set, String str) {
        int iIndexOf = str.indexOf(47);
        final String strA = AbstractC2066m90.a(str.substring(iIndexOf + 1));
        if (strA.isEmpty() || !set.contains(strA)) {
            return Stream.empty();
        }
        if (iIndexOf <= 0) {
            return this.b.d.values().stream().filter(new Predicate() { // from class: uri
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((InterfaceC2676tL) obj).containsKey(strA);
                }
            }).flatMap(new Function() { // from class: vri
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((InterfaceC2676tL) obj).get((Object) strA).stream();
                }
            });
        }
        U50 u50 = (U50) U50.I.get(str.substring(str.indexOf(58) + 1, iIndexOf));
        return u50 != null ? this.b.a(u50, strA).stream() : Stream.empty();
    }

    public final Stream c(Set set, String str) {
        List listB = this.b.b(str);
        if (!listB.isEmpty()) {
            return listB.stream();
        }
        int iMax = Math.max(str.lastIndexOf(47), 0);
        int iIndexOf = str.indexOf(46, iMax);
        if (iIndexOf == -1) {
            iIndexOf = str.length();
        }
        final String strSubstring = str.substring(iMax, iIndexOf);
        return set.contains(strSubstring) ? this.b.d.values().stream().filter(new Predicate() { // from class: mri
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((InterfaceC2676tL) obj).containsKey(strSubstring);
            }
        }).flatMap(new Function() { // from class: nri
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InterfaceC2676tL) obj).get((Object) strSubstring).stream();
            }
        }) : Stream.empty();
    }

    public final Stream c(final String str) {
        List listUnmodifiableList = Collections.unmodifiableList(this.b.c);
        KB.b(listUnmodifiableList, "unmodifiableList(_resources)");
        return listUnmodifiableList.stream().filter(new Predicate() { // from class: lri
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((W50) obj).d.startsWith(AbstractC2066m90.a(str));
            }
        });
    }

    public final Stream b(String str) {
        try {
            final Pattern patternCompile = Pattern.compile(a(str));
            List listUnmodifiableList = Collections.unmodifiableList(this.b.c);
            KB.b(listUnmodifiableList, "unmodifiableList(_resources)");
            return listUnmodifiableList.stream().filter(new Predicate() { // from class: wri
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return patternCompile.matcher(((W50) obj).d).matches();
                }
            });
        } catch (PatternSyntaxException unused) {
            return Stream.empty();
        }
    }

    public static /* synthetic */ boolean a(int i, String str) {
        return str.length() >= i;
    }

    public final void a() {
        List listUnmodifiableList = Collections.unmodifiableList(this.b.c);
        KB.b(listUnmodifiableList, "unmodifiableList(_resources)");
        Stream map = listUnmodifiableList.stream().map(new Function() { // from class: ori
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((W50) obj).d;
            }
        });
        int i = AbstractC2554rv.c;
        final Set set = (Set) map.collect(AbstractC0845Td.a);
        final int iOrElse = set.stream().mapToInt(new ToIntFunction() { // from class: pri
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((String) obj).length();
            }
        }).min().orElse(Integer.MAX_VALUE);
        this.c.stream().filter(new Predicate() { // from class: qri
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C3031xX.a(iOrElse, (String) obj);
            }
        }).flatMap(new Function() { // from class: rri
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(set, (String) obj);
            }
        }).forEach(new Consumer() { // from class: sri
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Y50.a((W50) obj);
            }
        });
    }

    public static /* synthetic */ String a(W50 w50, String str) {
        return "Marking " + w50 + " used because it matches string pool constant " + str;
    }

    public final void a(final String str, final W50 w50) {
        Cb0 cb0 = this.a;
        InterfaceC1270cr interfaceC1270cr = new InterfaceC1270cr() { // from class: kri
            @Override // com.android.tools.r8.internal.InterfaceC1270cr
            public final Object a() {
                return C3031xX.a(w50, str);
            }
        };
        ((C1062aR) cb0).getClass();
        KB.c(interfaceC1270cr, "f");
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:53:0x00aa  */
    public static String a(String str) {
        String strConcat;
        boolean z;
        StringBuilder sb = new StringBuilder();
        Matcher matcher = e.matcher(str);
        int length = str.length();
        boolean z2 = false;
        int i = 0;
        boolean z3 = false;
        while (matcher.find(i)) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (iStart == 0 && iEnd == length) {
                return "-nomatch-";
            }
            if (iStart > i) {
                sb.append(Pattern.quote(str.substring(i, iStart)));
                while (true) {
                    if (i >= iStart) {
                        z = false;
                    } else if (Character.isLetter(str.charAt(i))) {
                        z = true;
                    } else {
                        i++;
                    }
                }
                z3 |= z;
            }
            String strGroup = matcher.group(6);
            if (matcher.group(5) == null && strGroup != null && strGroup.length() == 1) {
                char cCharAt = strGroup.charAt(0);
                if (cCharAt == '%') {
                    strConcat = "%";
                } else if (cCharAt == 'E') {
                    strConcat = "-?\\p{Digit}+[,.]\\p{Digit}+E\\+?\\p{Digit}+";
                } else if (cCharAt == 'X' || cCharAt == 'x') {
                    strConcat = "\\p{XDigit}+";
                } else if (cCharAt == 'G') {
                    strConcat = "-?[\\p{XDigit},.+eE]+";
                } else if (cCharAt == 'H') {
                    strConcat = "(null|\\p{XDigit}+)";
                } else if (cCharAt == 'n') {
                    strConcat = "\n";
                } else if (cCharAt != 'o') {
                    switch (cCharAt) {
                        case 'A':
                            strConcat = "0X[\\p{XDigit},.+P]+";
                            break;
                        case 'B':
                            strConcat = "(TRUE|FALSE)";
                            break;
                        default:
                            switch (cCharAt) {
                                case 'a':
                                    strConcat = "0x[\\p{XDigit},.+p]+";
                                    break;
                                case 'b':
                                    strConcat = "(true|false)";
                                    break;
                                case 'c':
                                    break;
                                case 'd':
                                    strConcat = "\\p{Digit}+";
                                    break;
                                case 'e':
                                    strConcat = "-?\\p{Digit}+[,.]\\p{Digit}+e\\+?\\p{Digit}+";
                                    break;
                                case 'f':
                                    strConcat = "-?[\\p{XDigit},.]+";
                                    break;
                                case 'g':
                                    strConcat = "-?[\\p{XDigit},.+eE]+";
                                    break;
                                case 'h':
                                    strConcat = "(null|\\p{XDigit}+)";
                                    break;
                                default:
                                    strConcat = ".*";
                                    break;
                            }
                        case 'C':
                            strConcat = ".";
                            break;
                    }
                } else {
                    strConcat = "\\p{Digit}+";
                }
                if (!".*".equals(strConcat) && matcher.group(3) != null) {
                    if ("0".equals(matcher.group(2))) {
                        strConcat = "0*".concat(strConcat);
                    } else {
                        strConcat = " ".concat(strConcat);
                    }
                }
                int length2 = sb.length();
                if (!".*".equals(strConcat) || length2 < 2 || sb.charAt(length2 - 1) != '*' || sb.charAt(length2 - 2) != '.') {
                    sb.append(strConcat);
                }
            }
            i = iEnd;
        }
        if (i < length) {
            sb.append(Pattern.quote(str.substring(i, length)));
            while (i < length) {
                if (Character.isLetter(str.charAt(i))) {
                    z2 = true;
                    z3 |= z2;
                } else {
                    i++;
                }
            }
            z3 |= z2;
        }
        return !z3 ? "-nomatch-" : sb.toString();
    }
}
