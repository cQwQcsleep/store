package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC2497rD {
    public static /* synthetic */ String a(int i) {
        switch (i) {
            case 1:
                return "BEGIN_ARRAY";
            case 2:
                return "END_ARRAY";
            case XmlPullParser.END_TAG /* 3 */:
                return "BEGIN_OBJECT";
            case 4:
                return "END_OBJECT";
            case XmlPullParser.CDSECT /* 5 */:
                return "NAME";
            case XmlPullParser.ENTITY_REF /* 6 */:
                return "STRING";
            case 7:
                return "NUMBER";
            case 8:
                return "BOOLEAN";
            case 9:
                return "NULL";
            case XmlPullParser.DOCDECL /* 10 */:
                return "END_DOCUMENT";
            default:
                return "null";
        }
    }
}
