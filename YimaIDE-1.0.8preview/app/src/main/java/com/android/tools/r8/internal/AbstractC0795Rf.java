package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class AbstractC0795Rf {
    public static /* synthetic */ int a(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 1;
            case XmlPullParser.END_TAG /* 3 */:
                return 2;
            case 4:
                return 3;
            case XmlPullParser.CDSECT /* 5 */:
                return 4;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return -1;
            default:
                throw null;
        }
    }
}
