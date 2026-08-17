package com.android.tools.r8.origin;

import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class d extends Origin {
    public d() {
        super();
    }

    @Override // com.android.tools.r8.origin.Origin
    public final List b(int i) {
        return new ArrayList(i);
    }

    @Override // com.android.tools.r8.origin.Origin, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Origin origin) {
        return super.compareTo(origin);
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return XmlPullParser.NO_NAMESPACE;
    }
}
