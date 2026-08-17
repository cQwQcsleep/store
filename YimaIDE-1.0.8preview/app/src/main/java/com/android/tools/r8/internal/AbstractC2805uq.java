package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2805uq {
    public static final C2549rq A;
    public static final C2549rq B;
    public static final C2549rq C;
    public static final C2549rq D;
    public static final C2549rq E;
    public static final C2549rq F;
    public static final C2549rq G;
    public static final C2549rq H;
    public static final C2549rq I;
    public static final C2549rq J;
    public static final C2549rq K;
    public static final C2549rq L;
    public static final C2549rq M;
    public static final C2549rq N;
    public static final C2549rq O;
    public static final C2549rq a = new C2549rq(1);
    public static final C2549rq b = new C2549rq(0);
    public static final C2634sq c;
    public static final C2634sq d;
    public static final C2634sq e;
    public static final C2549rq f;
    public static final C2549rq g;
    public static final C2549rq h;
    public static final C2549rq i;
    public static final C2549rq j;
    public static final C2549rq k;
    public static final C2549rq l;
    public static final C2549rq m;
    public static final C2549rq n;
    public static final C2634sq o;
    public static final C2549rq p;
    public static final C2549rq q;
    public static final C2549rq r;
    public static final C2549rq s;
    public static final C2549rq t;
    public static final C2549rq u;
    public static final C2549rq v;
    public static final C2549rq w;
    public static final C2549rq x;
    public static final C2549rq y;
    public static final C2549rq z;

    static {
        C2634sq c2634sq = new C2634sq(1, (S00[]) S00.c.clone());
        c = c2634sq;
        ZZ[] zzArr = (ZZ[]) ZZ.c.clone();
        int i2 = c2634sq.b + 1;
        C2634sq c2634sq2 = new C2634sq(i2, zzArr);
        d = c2634sq2;
        BZ[] bzArr = (BZ[]) BZ.c.clone();
        int i3 = c2634sq2.b + i2;
        C2634sq c2634sq3 = new C2634sq(i3, bzArr);
        e = c2634sq3;
        int i4 = i3 + c2634sq3.b;
        f = new C2549rq(i4);
        g = new C2549rq(i4 + 1);
        h = new C2549rq(i4 + 2);
        i = new C2549rq(i4 + 3);
        j = new C2549rq(i4 + 4);
        k = new C2549rq(i4 + 5);
        l = new C2549rq(i4 + 6);
        int i5 = c2634sq.b;
        m = new C2549rq(i5 + 1);
        n = new C2549rq(i5 + 2);
        YZ[] yzArr = (YZ[]) YZ.c.clone();
        int i6 = c2634sq2.b + i2;
        C2634sq c2634sq4 = new C2634sq(i6, yzArr);
        o = c2634sq4;
        int i7 = i6 + c2634sq4.b;
        p = new C2549rq(i7);
        int i8 = i7 + 1;
        q = new C2549rq(i8);
        int i9 = i7 + 2;
        r = new C2549rq(i9);
        int i10 = i7 + 3;
        s = new C2549rq(i10);
        int i11 = i7 + 4;
        t = new C2549rq(i11);
        int i12 = i7 + 5;
        u = new C2549rq(i12);
        int i13 = i7 + 6;
        v = new C2549rq(i13);
        int i14 = i7 + 7;
        w = new C2549rq(i14);
        x = new C2549rq(i7);
        y = new C2549rq(i8);
        z = new C2549rq(i9);
        A = new C2549rq(i10);
        B = new C2549rq(i11);
        C = new C2549rq(i12);
        D = new C2549rq(i13);
        E = new C2549rq(i14);
        F = new C2549rq(i7 + 8);
        G = new C2549rq(1);
        H = new C2549rq(2);
        I = new C2549rq(3);
        int i15 = i2 + c2634sq2.b;
        J = new C2549rq(i15);
        K = new C2549rq(i15 + 1);
        L = new C2549rq(i15 + 2);
        M = new C2549rq(0);
        N = new C2549rq(1);
        O = new C2549rq(0);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0021  */
    /* JADX WARN: Code duplicated, block: B:18:0x002b  */
    public static /* synthetic */ void a(int i2) {
        Object[] objArr = new Object[3];
        if (i2 == 1) {
            objArr[0] = "modality";
        } else if (i2 == 2) {
            objArr[0] = "kind";
        } else if (i2 == 5) {
            objArr[0] = "modality";
        } else if (i2 == 6) {
            objArr[0] = "memberKind";
        } else if (i2 == 8) {
            objArr[0] = "modality";
        } else if (i2 == 9) {
            objArr[0] = "memberKind";
        } else if (i2 != 11) {
            objArr[0] = "visibility";
        } else {
            objArr[0] = "modality";
        }
        objArr[1] = "kotlinx/metadata/internal/metadata/deserialization/Flags";
        switch (i2) {
            case XmlPullParser.END_TAG /* 3 */:
                objArr[2] = "getConstructorFlags";
                break;
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
                objArr[2] = "getFunctionFlags";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "getPropertyFlags";
                break;
            case XmlPullParser.DOCDECL /* 10 */:
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                objArr[2] = "getAccessorFlags";
                break;
            default:
                objArr[2] = "getClassFlags";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }
}
