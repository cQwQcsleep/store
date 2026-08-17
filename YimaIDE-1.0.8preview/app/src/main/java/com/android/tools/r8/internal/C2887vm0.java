package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vm0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2887vm0 {
    public static final /* synthetic */ boolean b = true;
    public final InterfaceC2801um0 a;

    public C2887vm0(C2399q40 c2399q40) {
        this.a = c2399q40;
    }

    public final void a(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 1;
        int i4 = -1;
        while (i2 < length) {
            if (i2 == i4 && (i2 = i2 + 1) == length) {
                return;
            }
            i4 = i2;
            char cCharAt = str.charAt(i4);
            if (i3 == 1) {
                if (cCharAt == '/') {
                    i3 = 2;
                } else if (cCharAt == 'u' && str.startsWith("url(", i4) && i4 > 0) {
                    char cCharAt2 = str.charAt(i4 - 1);
                    if (Character.isWhitespace(cCharAt2) || cCharAt2 == ':') {
                        int iIndexOf = str.indexOf(41, i4);
                        int i5 = i4 + 4;
                        while (i5 < length && Character.isWhitespace(str.charAt(i5))) {
                            i5++;
                        }
                        if (iIndexOf != -1 && iIndexOf > (i = i5 + 1)) {
                            while (iIndexOf > i5 && Character.isWhitespace(str.charAt(iIndexOf - 1))) {
                                iIndexOf--;
                            }
                            if ((str.charAt(i5) == '\"' && str.charAt(iIndexOf - 1) == '\"') || (str.charAt(i5) == '\'' && str.charAt(iIndexOf - 1) == '\'')) {
                                iIndexOf--;
                                i5 = i;
                            }
                            InterfaceC2801um0 interfaceC2801um0 = this.a;
                            String strTrim = str.substring(i5, iIndexOf).trim();
                            C2399q40 c2399q40 = (C2399q40) interfaceC2801um0;
                            c2399q40.getClass();
                            KB.c(strTrim, "url");
                            c2399q40.b(strTrim);
                        }
                        i2 = iIndexOf + 1;
                    }
                }
                i2 = i4 + 1;
            } else if (i3 != 2) {
                if (!b) {
                    throw new AssertionError(i3);
                }
                i2 = i4;
            } else if (cCharAt == '*') {
                int iIndexOf2 = str.indexOf("*/", i4 + 1);
                i2 = iIndexOf2 == -1 ? length : iIndexOf2 + 2;
            } else {
                i2 = i4 + 1;
                i3 = 1;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x008e  */
    public final void b(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        int i5 = -1;
        while (i2 < length) {
            if (i2 != i5 || (i2 = i2 + 1) != length) {
                i5 = i2;
                char cCharAt = str.charAt(i5);
                switch (i3) {
                    case 1:
                        if (cCharAt == '/') {
                            i = 2;
                            i3 = i;
                        } else if (cCharAt == '\"') {
                            i4 = i5 + 1;
                            i3 = 3;
                        } else if (cCharAt == '\'') {
                            i4 = i5 + 1;
                            i3 = 5;
                        }
                        i2 = i5 + 1;
                        break;
                    case 2:
                        if (cCharAt == '*') {
                            int iIndexOf = str.indexOf("*/", i5 + 1);
                            if (iIndexOf == -1) {
                                i2 = length;
                            } else {
                                i2 = iIndexOf + 2;
                            }
                        } else if (cCharAt == '/') {
                            int iIndexOf2 = str.indexOf(10, i5 + 1);
                            if (iIndexOf2 == -1) {
                                i2 = length;
                            } else {
                                i2 = iIndexOf2 + 1;
                            }
                        } else {
                            i2 = i5 + 1;
                        }
                        i3 = 1;
                        break;
                    case XmlPullParser.END_TAG /* 3 */:
                        if (cCharAt == '\"') {
                            InterfaceC2801um0 interfaceC2801um0 = this.a;
                            String strSubstring = str.substring(i4, i5);
                            C2399q40 c2399q40 = (C2399q40) interfaceC2801um0;
                            c2399q40.getClass();
                            KB.c(strSubstring, "jsString");
                            c2399q40.a(strSubstring);
                            i3 = 1;
                        } else if (cCharAt == '\\') {
                            i = 4;
                            i3 = i;
                        }
                        i2 = i5 + 1;
                        break;
                    case 4:
                        i2 = i5 + 1;
                        i3 = 3;
                        break;
                    case XmlPullParser.CDSECT /* 5 */:
                        if (cCharAt == '\'') {
                            InterfaceC2801um0 interfaceC2801um1 = this.a;
                            String strSubstring2 = str.substring(i4, i5);
                            C2399q40 c2399q41 = (C2399q40) interfaceC2801um1;
                            c2399q41.getClass();
                            KB.c(strSubstring2, "jsString");
                            c2399q41.a(strSubstring2);
                            i3 = 1;
                        } else if (cCharAt == '\\') {
                            i = 6;
                            i3 = i;
                        }
                        i2 = i5 + 1;
                        break;
                    case XmlPullParser.ENTITY_REF /* 6 */:
                        i2 = i5 + 1;
                        i3 = 5;
                        break;
                    default:
                        if (!b) {
                            throw new AssertionError(i3);
                        }
                        i2 = i5;
                        break;
                        break;
                }
            } else {
                return;
            }
        }
    }

    public final void a(String str, String str2) {
        C2399q40 c2399q40 = (C2399q40) this.a;
        c2399q40.getClass();
        KB.c(str2, "value");
        if (KB.a((Object) str, (Object) "href") || KB.a((Object) str, (Object) "src")) {
            c2399q40.b(str2);
        }
    }

    public final void a(int i, String str, String str2) {
        int i2;
        int iIndexOf;
        if ("script".equals(str2)) {
            int i3 = i + 1;
            int iIndexOf2 = str.indexOf("</script>", i3);
            if (iIndexOf2 != -1) {
                b(str.substring(i3, iIndexOf2));
                return;
            }
            return;
        }
        if (!"style".equals(str2) || (iIndexOf = str.indexOf("</style>", (i2 = i + 1))) == -1) {
            return;
        }
        a(str.substring(i2, iIndexOf));
    }
}
