package com.android.tools.r8;

import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import java.io.PrintStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F implements MarkerInfoConsumer {
    public final PrintStream a;

    public F(PrintStream printStream) {
        this.a = printStream;
    }

    @Override // com.android.tools.r8.MarkerInfoConsumer
    public final void acceptMarkerInfo(MarkerInfoConsumerData markerInfoConsumerData) {
        if (!markerInfoConsumerData.hasMarkers()) {
            Origin inputOrigin = markerInfoConsumerData.getInputOrigin();
            String strE = Wf0.e("no marker");
            this.a.print(inputOrigin.toString());
            this.a.print(": ");
            this.a.print(strE);
            this.a.println();
            return;
        }
        for (MarkerInfo markerInfo : markerInfoConsumerData.getMarkers()) {
            Origin inputOrigin2 = markerInfoConsumerData.getInputOrigin();
            String rawEncoding = markerInfo.getRawEncoding();
            this.a.print(inputOrigin2.toString());
            this.a.print(": ");
            this.a.print(rawEncoding);
            this.a.println();
        }
    }

    @Override // com.android.tools.r8.MarkerInfoConsumer
    public final void finished() {
    }
}
