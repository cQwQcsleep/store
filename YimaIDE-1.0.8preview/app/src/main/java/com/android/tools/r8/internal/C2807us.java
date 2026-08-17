package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.us, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2807us {
    public static final C2807us b = new C2807us(a.t);
    public static final /* synthetic */ boolean c = true;
    public final a a;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.us$a */
    public enum a {
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        j,
        k,
        l,
        m,
        n,
        o,
        p,
        q,
        r,
        /* JADX INFO: Fake field, exist only in values array */
        EF0,
        /* JADX INFO: Fake field, exist only in values array */
        EF1,
        s,
        t;

        a() {
        }
    }

    public C2807us(a aVar) {
        this.a = aVar;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        switch (AbstractC2722ts.a[a().ordinal()]) {
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
                return "referenced in keep rule";
            case 4:
                return "satisfied with precondition";
            case XmlPullParser.CDSECT /* 5 */:
                return "instantiated in";
            case XmlPullParser.ENTITY_REF /* 6 */:
                return "invoked via super from";
            case 7:
                return "targeted by super from";
            case 8:
                return "invoked from";
            case 9:
                return "invoked from lambda created in";
            case XmlPullParser.DOCDECL /* 10 */:
                return "annotated on";
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return "referenced from";
            case 12:
                return "reflected from";
            case 13:
                return "reachable from";
            case 14:
                return "referenced in annotation";
            case 15:
                return "overriding method";
            case Fcntl.S_IWGRP /* 16 */:
                return "defined in library method overridden by";
            case 17:
                return "referenced by method handle";
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                return "companion class for";
            case AndroidSdkVersion.KITKAT /* 19 */:
                return "companion method for";
            case 20:
                return "referenced from xml";
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                return "kept for unknown reasons";
            default:
                if (c) {
                    return "kept for unknown reasons";
                }
                pe1.a("Unknown edge kind: ", a());
                return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof C2807us) && ((C2807us) obj).a == this.a;
        }
        return true;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "{edge-type:" + this.a.toString() + "}";
    }
}
