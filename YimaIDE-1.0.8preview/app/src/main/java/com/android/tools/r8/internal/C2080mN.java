package com.android.tools.r8.internal;

import com.android.tools.r8.MarkerInfo;
import com.android.tools.r8.MarkerInfoConsumer;
import com.android.tools.r8.MarkerInfoConsumerData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2080mN implements MarkerInfoConsumer {
    public final List a;

    public C2080mN(ArrayList arrayList) {
        this.a = arrayList;
    }

    @Override // com.android.tools.r8.MarkerInfoConsumer
    public final void acceptMarkerInfo(MarkerInfoConsumerData markerInfoConsumerData) {
        if (markerInfoConsumerData.hasMarkers()) {
            Iterator<MarkerInfo> it = markerInfoConsumerData.getMarkers().iterator();
            while (it.hasNext()) {
                this.a.add(((C1994lN) it.next()).a);
            }
        }
    }

    @Override // com.android.tools.r8.MarkerInfoConsumer
    public final void finished() {
    }
}
