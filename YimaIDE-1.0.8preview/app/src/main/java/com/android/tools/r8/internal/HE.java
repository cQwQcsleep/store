package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HE {
    public final String a;
    public String b = XmlPullParser.NO_NAMESPACE;

    public HE(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return this.a + this.b;
    }
}
