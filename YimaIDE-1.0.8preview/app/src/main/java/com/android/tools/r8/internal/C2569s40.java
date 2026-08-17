package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2569s40 {
    public final InterfaceC2827v50 a;
    public final H50 b;
    public final E60 c;
    public final W50 d;
    public final Bg0 e;

    public C2569s40(InterfaceC2827v50 interfaceC2827v50, H50 h50, E60 e60, W50 w50) {
        KB.c(interfaceC2827v50, "resourcesRoot");
        KB.c(h50, "model");
        KB.c(e60, "entry");
        this.a = interfaceC2827v50;
        this.b = h50;
        this.c = e60;
        this.d = w50;
        this.e = new Bg0(new C2483r40(this));
    }

    /* JADX WARN: Code duplicated, block: B:57:0x010f A[PHI: r3 r5 r12 r13 r15
      0x010f: PHI (r3v19 java.lang.String) = 
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v13 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
      (r3v18 java.lang.String)
      (r3v11 java.lang.String)
      (r3v11 java.lang.String)
     binds: [B:102:0x01c5, B:95:0x01ab, B:92:0x0195, B:93:0x0197, B:82:0x016a, B:85:0x0171, B:86:0x0173, B:74:0x0154, B:78:0x0161, B:71:0x014b, B:67:0x013d, B:63:0x012d, B:58:0x0112, B:56:0x010d, B:54:0x0104, B:52:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r5v7 java.lang.String) = 
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v6 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
      (r5v2 java.lang.String)
     binds: [B:102:0x01c5, B:95:0x01ab, B:92:0x0195, B:93:0x0197, B:82:0x016a, B:85:0x0171, B:86:0x0173, B:74:0x0154, B:78:0x0161, B:71:0x014b, B:67:0x013d, B:63:0x012d, B:58:0x0112, B:56:0x010d, B:54:0x0104, B:52:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r12v3 int) = 
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v2 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
      (r12v1 int)
     binds: [B:102:0x01c5, B:95:0x01ab, B:92:0x0195, B:93:0x0197, B:82:0x016a, B:85:0x0171, B:86:0x0173, B:74:0x0154, B:78:0x0161, B:71:0x014b, B:67:0x013d, B:63:0x012d, B:58:0x0112, B:56:0x010d, B:54:0x0104, B:52:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r13v3 int) = 
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v2 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
      (r13v1 int)
     binds: [B:102:0x01c5, B:95:0x01ab, B:92:0x0195, B:93:0x0197, B:82:0x016a, B:85:0x0171, B:86:0x0173, B:74:0x0154, B:78:0x0161, B:71:0x014b, B:67:0x013d, B:63:0x012d, B:58:0x0112, B:56:0x010d, B:54:0x0104, B:52:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x010f: PHI (r15v13 int) = 
      (r15v1 int)
      (r15v1 int)
      (r15v1 int)
      (r15v8 int)
      (r15v1 int)
      (r15v1 int)
      (r15v9 int)
      (r15v10 int)
      (r15v1 int)
      (r15v1 int)
      (r15v11 int)
      (r15v1 int)
      (r15v1 int)
      (r15v12 int)
      (r15v1 int)
      (r15v1 int)
     binds: [B:102:0x01c5, B:95:0x01ab, B:92:0x0195, B:93:0x0197, B:82:0x016a, B:85:0x0171, B:86:0x0173, B:74:0x0154, B:78:0x0161, B:71:0x014b, B:67:0x013d, B:63:0x012d, B:58:0x0112, B:56:0x010d, B:54:0x0104, B:52:0x00fe] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x0173  */
    public final void a(L60 l60) {
        char c;
        int iIndexOf;
        int i;
        int i2;
        InterfaceC2827v50 interfaceC2827v50 = this.a;
        String strK = l60.k();
        KB.b(strK, "file.path");
        byte[] bArrA = interfaceC2827v50.a(AbstractC1679hg0.b(strK, "res/"));
        Bg0 bg0 = new Bg0(new C1372e40(bArrA));
        String strK2 = l60.k();
        KB.b(strK2, "file.path");
        String strA = AbstractC1679hg0.a(strK2, '.');
        int length = strA.length();
        int i3 = 0;
        while (i3 < length) {
            char cCharAt = strA.charAt(i3);
            if (cCharAt >= 'A' && cCharAt <= 'Z') {
                char[] charArray = strA.toCharArray();
                while (i3 < length) {
                    char c2 = charArray[i3];
                    if (c2 >= 'A' && c2 <= 'Z') {
                        charArray[i3] = (char) (c2 ^ ' ');
                    }
                    i3++;
                }
                strA = String.valueOf(charArray);
                break;
            }
            i3++;
        }
        KB.b(strA, "toLowerCase(file.path.substringAfterLast('.'))");
        int i4 = l60.f;
        int i5 = 3;
        if (i4 == 0) {
            c = 1;
        } else if (i4 == 1) {
            c = 2;
        } else if (i4 != 2) {
            c = i4 != 3 ? (char) 0 : (char) 4;
        } else {
            c = 3;
        }
        if (c == 0) {
            c = 5;
        }
        if (c == 4) {
            C1382e90 c1382e90 = (C1382e90) C1382e90.j.a(bArrA);
            KB.b(c1382e90, "parseFrom(bytes)");
            a(c1382e90);
            return;
        }
        if (!AbstractC1929ke.a((Object[]) new String[]{"html", "htm"}).contains(strA)) {
            if (strA.equals("css")) {
                ((C2887vm0) this.e.a()).a((String) bg0.a());
                return;
            } else if (strA.equals("js")) {
                ((C2887vm0) this.e.a()).b((String) bg0.a());
                return;
            } else {
                if (Qa0.a.contains(strA)) {
                    return;
                }
                a((String) bg0.a(), false);
                return;
            }
        }
        C2887vm0 c2887vm0 = (C2887vm0) this.e.a();
        String str = (String) bg0.a();
        c2887vm0.getClass();
        int length2 = str.length();
        String strTrim = null;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 1;
        int i11 = -1;
        String strSubstring = null;
        while (i6 < length2) {
            if (i6 != i11 || (i6 = i6 + 1) != length2) {
                i11 = i6;
                char cCharAt2 = str.charAt(i11);
                i5 = i5;
                switch (i10) {
                    case 1:
                        if (cCharAt2 == '<') {
                            i6 = i11 + 1;
                            i10 = 2;
                        } else {
                            i6 = i11 + 1;
                        }
                        break;
                    case 2:
                        if (cCharAt2 == '!') {
                            if (str.startsWith("!--", i11)) {
                                iIndexOf = str.indexOf("-->", i11 + 3);
                                if (iIndexOf == -1) {
                                    i6 = length2;
                                } else {
                                    i6 = iIndexOf + 3;
                                    i10 = 1;
                                }
                            } else if (str.startsWith("![CDATA[", i11)) {
                                iIndexOf = str.indexOf("]]>", i11 + 8);
                                if (iIndexOf == -1) {
                                    i6 = length2;
                                } else {
                                    i6 = iIndexOf + 3;
                                    i10 = 1;
                                }
                            } else {
                                i6 = i11;
                                i7 = i6;
                                i10 = 5;
                            }
                        } else if (cCharAt2 == '/') {
                            i6 = i11 + 1;
                            i10 = 12;
                        } else if (cCharAt2 == '?') {
                            int iIndexOf2 = str.indexOf(62, i11 + 2);
                            i6 = iIndexOf2 == -1 ? length2 : iIndexOf2 + 1;
                            i10 = 1;
                        } else {
                            i6 = i11;
                            i7 = i6;
                            i10 = 5;
                        }
                        break;
                    case XmlPullParser.END_TAG /* 3 */:
                        if (cCharAt2 == '>') {
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 1;
                        } else if (cCharAt2 == '=') {
                            strSubstring = str.substring(i9, i11);
                            i10 = 8;
                        } else if (Character.isWhitespace(cCharAt2)) {
                            i10 = 7;
                            strSubstring = str.substring(i9, i11);
                        }
                        i6 = i11 + 1;
                        break;
                    case 4:
                        if (!Character.isWhitespace(cCharAt2)) {
                            i7 = i11;
                            i10 = 5;
                        }
                        i6 = i11 + 1;
                        break;
                    case XmlPullParser.CDSECT /* 5 */:
                        if (Character.isWhitespace(cCharAt2)) {
                            strTrim = str.substring(i7, i11).trim();
                            i10 = 6;
                        } else if (cCharAt2 == '>') {
                            strTrim = str.substring(i7, i11).trim();
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 1;
                            i6 = i11 + 1;
                        } else if (cCharAt2 == '/') {
                            strTrim = str.substring(i7, i11).trim();
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 13;
                        }
                        i6 = i11 + 1;
                        break;
                    case XmlPullParser.ENTITY_REF /* 6 */:
                        if (cCharAt2 != '>') {
                            if (cCharAt2 != '/' && !Character.isWhitespace(cCharAt2)) {
                                i9 = i11;
                                i10 = i5;
                            }
                            i6 = i11 + 1;
                        } else {
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 1;
                            i6 = i11 + 1;
                        }
                        break;
                    case 7:
                        if (cCharAt2 == '=') {
                            i10 = 8;
                        } else if (cCharAt2 == '>') {
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 1;
                            i6 = i11 + 1;
                        } else if (!Character.isWhitespace(cCharAt2)) {
                            i9 = i11;
                            i10 = i5;
                        }
                        i6 = i11 + 1;
                        break;
                    case 8:
                        if (cCharAt2 == '\'') {
                            i = i11 + 1;
                            i2 = 10;
                        } else if (cCharAt2 != '\"') {
                            if (!Character.isWhitespace(cCharAt2)) {
                                i = i11 + 1;
                                i2 = 9;
                            }
                            i6 = i11 + 1;
                        } else {
                            i = i11 + 1;
                            i2 = 11;
                        }
                        i8 = i;
                        i10 = i2;
                        i6 = i11 + 1;
                        break;
                    case 9:
                        if (cCharAt2 != '>') {
                            if (Character.isWhitespace(cCharAt2)) {
                                c2887vm0.a(strSubstring, str.substring(i8, i11));
                                i10 = 6;
                            }
                            i6 = i11 + 1;
                        } else {
                            c2887vm0.a(strSubstring, str.substring(i8, i11));
                            c2887vm0.a(i11, str, strTrim);
                            i10 = 1;
                            i6 = i11 + 1;
                        }
                        break;
                    case XmlPullParser.DOCDECL /* 10 */:
                        if (cCharAt2 == '\'') {
                            c2887vm0.a(strSubstring, str.substring(i8, i11));
                            i10 = 6;
                        }
                        i6 = i11 + 1;
                        break;
                    case AndroidSdkVersion.HONEYCOMB /* 11 */:
                        if (cCharAt2 == '\"') {
                            c2887vm0.a(strSubstring, str.substring(i8, i11));
                            i10 = 6;
                        }
                        i6 = i11 + 1;
                        break;
                    case 12:
                        if (cCharAt2 == '>') {
                            i10 = 1;
                        }
                        i6 = i11 + 1;
                        break;
                    case 13:
                        if (cCharAt2 != '>') {
                            i6 = i11;
                        } else {
                            i6 = i11 + 1;
                            i10 = 1;
                        }
                        break;
                    default:
                        if (!C2887vm0.b) {
                            throw new AssertionError(i10);
                        }
                        i6 = i11;
                        break;
                }
            } else {
                return;
            }
        }
    }

    public final void a(J70 j70) {
        List listA;
        if (j70.k() != 0) {
            listA = AbstractC1844je.a(this.b.a().a(j70.k()));
        } else {
            String strM = j70.m();
            KB.b(strM, "reference.name");
            if (strM.length() > 0) {
                listA = this.b.a().a("@" + j70.m());
            } else {
                listA = C0984Ym.b;
            }
        }
        Iterator it = Wa0.a(Wa0.a(AbstractC1760ie.a(listA)), C1458f40.c).iterator();
        while (true) {
            C0987Yp c0987Yp = (C0987Yp) it;
            if (!c0987Yp.hasNext()) {
                return;
            }
            this.d.a((W50) c0987Yp.next());
        }
    }

    public final void a(C1382e90 c1382e90) {
        String strC;
        String strM;
        if (this.d.c == U50.XML) {
            String strN = c1382e90.n();
            KB.b(strN, "node.text");
            a(strN, true);
        }
        Y80 y80K = c1382e90.k();
        KB.b(y80K, "node.element");
        if (KB.a((Object) y80K.k(), (Object) "rawPathResId")) {
            List list = y80K.i;
            KB.b(list, "element.childList");
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((C1382e90) it.next()).n());
            }
            Iterator it2 = this.b.b.a(U50.RAW, AbstractC1679hg0.a(AbstractC1760ie.a(arrayList, XmlPullParser.NO_NAMESPACE, null, null, null, 62)).toString()).iterator();
            while (it2.hasNext()) {
                this.d.a((W50) it2.next());
            }
        }
        List<V80> list2 = c1382e90.k().h;
        KB.b(list2, "node.element.attributeList");
        for (V80 v80 : list2) {
            KB.b(v80, "it");
            Object obj = v80.f;
            if (obj instanceof String) {
                strC = (String) obj;
            } else {
                strC = ((U7) obj).c();
                v80.f = strC;
            }
            if (KB.a((Object) strC, (Object) "constraint_referenced_ids") && (strM = v80.m()) != null) {
                List listA = AbstractC1679hg0.a(strM, new String[]{","}, 0, 6);
                ArrayList arrayList2 = new ArrayList(AbstractC2015le.a((Iterable) listA));
                Iterator it3 = listA.iterator();
                while (it3.hasNext()) {
                    arrayList2.add(AbstractC1679hg0.a((String) it3.next()).toString());
                }
                Iterator it4 = arrayList2.iterator();
                while (it4.hasNext()) {
                    Iterator it5 = this.b.b.a(U50.ID, (String) it4.next()).iterator();
                    while (it5.hasNext()) {
                        Y50.a((W50) it5.next());
                    }
                }
            }
            if (v80.n()) {
                R60 r60K = v80.k();
                KB.b(r60K, "attribute.compiledItem");
                a(r60K);
            }
            if (this.d.c == U50.XML) {
                String strM2 = v80.m();
                KB.b(strM2, "attribute.value");
                a(strM2, true);
            }
        }
        List<C1382e90> list3 = c1382e90.k().i;
        KB.b(list3, "node.element.childList");
        for (C1382e90 c1382e91 : list3) {
            KB.b(c1382e91, "it");
            a(c1382e91);
        }
    }

    public final void a(String str, boolean z) {
        C2891vq c2891vq = new C2891vq(new C2977wq(new C1013Zp(new Yh0(new C1013Zp(new Yh0(new Xa0(new C1288d40(str, null)), C1970l40.c), true, C2056m40.c), C2141n40.c), true, C2227o40.c), new C2313p40(this), C1412eb0.j));
        while (c2891vq.a()) {
            W50 w50 = (W50) c2891vq.next();
            if (z) {
                Y50.a(w50);
            } else {
                this.d.a(w50);
            }
        }
    }

    public final void a(R60 r60) {
        try {
            int i = r60.e;
            if (i == 1) {
                J70 j70L = r60.l();
                KB.b(j70L, "item.ref");
                a(j70L);
            } else if (i == 5) {
                String strK = r60.k().k();
                KB.b(strK, "item.file.path");
                if (AbstractC1679hg0.a(strK, "res/")) {
                    L60 l60K = r60.k();
                    KB.b(l60K, "item.file");
                    a(l60K);
                }
            }
        } catch (IOException unused) {
            ((C1062aR) this.b.a).getClass();
        }
    }
}
