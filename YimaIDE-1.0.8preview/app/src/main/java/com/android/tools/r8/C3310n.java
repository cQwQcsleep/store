package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3310n implements DexIndexedConsumer, ProgramResourceProvider {
    public final ArrayList a = new ArrayList();

    @Override // com.android.tools.r8.DexIndexedConsumer
    public final synchronized void accept(int i, ByteDataView byteDataView, Set set, DiagnosticsHandler diagnosticsHandler) {
        this.a.add(ProgramResource.fromBytes(Origin.unknown(), ProgramResource.Kind.DEX, byteDataView.copyByteData(), set));
    }

    @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        return this.a;
    }
}
