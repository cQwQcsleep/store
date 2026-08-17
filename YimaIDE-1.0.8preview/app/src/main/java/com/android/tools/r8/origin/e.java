package com.android.tools.r8.origin;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e extends Origin {
    public e() {
        super();
    }

    @Override // com.android.tools.r8.origin.Origin
    public final List b(int i) {
        ArrayList arrayList = new ArrayList(i + 1);
        arrayList.add("<unknown>");
        return arrayList;
    }

    @Override // com.android.tools.r8.origin.Origin, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Origin origin) {
        return super.compareTo(origin);
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "<unknown>";
    }
}
