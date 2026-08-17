package com.android.tools.r8.internal;

import java.io.File;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0857Tp {
    public static String a(String str) {
        str.getClass();
        String name = new File(str).getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? XmlPullParser.NO_NAMESPACE : name.substring(iLastIndexOf + 1);
    }
}
