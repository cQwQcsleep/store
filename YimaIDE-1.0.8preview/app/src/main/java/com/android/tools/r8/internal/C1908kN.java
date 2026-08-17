package com.android.tools.r8.internal;

import com.android.tools.r8.MarkerInfoConsumerData;
import com.android.tools.r8.origin.Origin;
import java.util.Collection;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1908kN implements MarkerInfoConsumerData {
    public final Origin a;
    public final List b;

    public C1908kN(List list, Origin origin) {
        this.a = origin;
        this.b = list;
    }

    @Override // com.android.tools.r8.MarkerInfoConsumerData
    public final Origin getInputOrigin() {
        return this.a;
    }

    @Override // com.android.tools.r8.MarkerInfoConsumerData
    public final Collection getMarkers() {
        return this.b;
    }

    @Override // com.android.tools.r8.MarkerInfoConsumerData
    public final boolean hasMarkers() {
        return !this.b.isEmpty();
    }
}
