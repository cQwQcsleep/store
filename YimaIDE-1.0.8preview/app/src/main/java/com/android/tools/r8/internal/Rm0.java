package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.f63;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Rm0 {
    public static Object a(AbstractC0663Md abstractC0663Md, Nm0 nm0, int i) {
        switch (nm0.ordinal()) {
            case 0:
                return Double.valueOf(abstractC0663Md.e());
            case 1:
                return Float.valueOf(abstractC0663Md.i());
            case 2:
                return Long.valueOf(abstractC0663Md.k());
            case XmlPullParser.END_TAG /* 3 */:
                return Long.valueOf(abstractC0663Md.u());
            case 4:
                return Integer.valueOf(abstractC0663Md.j());
            case XmlPullParser.CDSECT /* 5 */:
                return Long.valueOf(abstractC0663Md.h());
            case XmlPullParser.ENTITY_REF /* 6 */:
                return Integer.valueOf(abstractC0663Md.g());
            case 7:
                return Boolean.valueOf(abstractC0663Md.c());
            case 8:
                if (i != 1) {
                    return i != 2 ? abstractC0663Md.d() : abstractC0663Md.r();
                }
                return abstractC0663Md.q();
            case 9:
                w01.a("readPrimitiveField() cannot handle nested groups.");
                return null;
            case XmlPullParser.DOCDECL /* 10 */:
                w01.a("readPrimitiveField() cannot handle embedded messages.");
                return null;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return abstractC0663Md.d();
            case 12:
                return Integer.valueOf(abstractC0663Md.t());
            case 13:
                w01.a("readPrimitiveField() cannot handle enums.");
                return null;
            case 14:
                return Integer.valueOf(abstractC0663Md.m());
            case 15:
                return Long.valueOf(abstractC0663Md.n());
            case Fcntl.S_IWGRP /* 16 */:
                return Integer.valueOf(abstractC0663Md.o());
            case 17:
                return Long.valueOf(abstractC0663Md.p());
            default:
                f63.a("There is no way to get here, but the compiler thinks otherwise.");
                return null;
        }
    }
}
