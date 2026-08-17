package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MarkerInfoConsumerData {
    Origin getInputOrigin();

    Collection<MarkerInfo> getMarkers();

    boolean hasMarkers();
}
