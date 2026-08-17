package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class WG extends YG {
    public static final /* synthetic */ boolean b = true;
    public final String a;

    public WG(String str) {
        if (b || str != null) {
            this.a = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.YG
    public final String a() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.YG
    public boolean b() {
        return false;
    }

    @Override // com.android.tools.r8.internal.YG
    public final boolean c() {
        return true;
    }

    @Override // com.android.tools.r8.internal.YG
    public boolean d() {
        return this.a.equals(XmlPullParser.NO_NAMESPACE);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((WG) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a;
    }
}
