package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2841vF {
    public static final C2841vF b = new C2841vF(XmlPullParser.NO_NAMESPACE);
    public static final /* synthetic */ boolean c = true;
    public final String a;

    public C2841vF(String str) {
        if (c || str != null) {
            this.a = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2841vF.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C2841vF) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
