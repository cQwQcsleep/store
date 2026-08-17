package com.android.tools.r8;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class b0 extends DexIndexedConsumer.ForwardingConsumer {
    public final /* synthetic */ Map c;
    public final /* synthetic */ String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(DexIndexedConsumer dexIndexedConsumer, ConcurrentHashMap concurrentHashMap, String str) {
        super(dexIndexedConsumer);
        this.c = concurrentHashMap;
        this.d = str;
    }

    @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.DexIndexedConsumer
    public final void accept(int i, ByteDataView byteDataView, Set set, DiagnosticsHandler diagnosticsHandler) {
        this.c.put(this.d + "_classes" + i + ".dex", byteDataView.copyByteData());
        super.accept(i, byteDataView, (Set<String>) set, diagnosticsHandler);
    }
}
