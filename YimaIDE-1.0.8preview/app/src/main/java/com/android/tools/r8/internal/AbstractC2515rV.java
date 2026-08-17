package com.android.tools.r8.internal;

import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2515rV {
    public static final /* synthetic */ boolean a = true;

    public Object a() {
        throw new C2499rF("Unexpected attempt to get absent value");
    }

    public abstract boolean b();

    public Object a(Function function) {
        return XmlPullParser.NO_NAMESPACE;
    }
}
